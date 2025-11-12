package walaniam.senior;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This test verifies {@linkplain TemperatureService#readTemperature()} implementation.
 */
class TemperatureServiceTest {

    @ParameterizedTest
    @CsvSource({
        "100,100,100,500",
        "50,150,400,500",
        "400,50,150,500",
        "150,400,50,500",
        "100,200,200,500",
        "100,3000,100,500"
    })
    void shouldGetAtLeastTwoResponsesInExpectedTime(int analogDelay, int digitalDelay, int infraredDelay, long responseTimeoutMs) {

        final TemperatureService underTest = serviceWithMockSensors(analogDelay, digitalDelay, infraredDelay);

        await()
            .atMost(responseTimeoutMs, TimeUnit.MILLISECONDS)
            .untilAsserted(() -> {
                TemperatureService.TemperatureSnapshot snapshot = underTest.readTemperature();
                int notNullCount = getNotNullCount(snapshot);
                assertThat(notNullCount)
                    .isGreaterThanOrEqualTo(2)
                    .withFailMessage("Expected at least 2 non-null temperatures but got: " + snapshot);
            });
    }

    @ParameterizedTest
    @CsvSource({
        "550,550,100",
        "20,550,550"
    })
    void shouldThrowWhen500MsTimeoutExceeded(int analogDelay, int digitalDelay, int infraredDelay) {
        final TemperatureService underTest = serviceWithMockSensors(analogDelay, digitalDelay, infraredDelay);
        assertThrows(TimeoutException.class, underTest::readTemperature);
    }

    private static int getNotNullCount(TemperatureService.TemperatureSnapshot snapshot) {
        int notNullCount = 0;
        if (snapshot != null) {
            if (snapshot.analog != null) {
                notNullCount++;
            }
            if (snapshot.digital != null) {
                notNullCount++;
            }
            if (snapshot.infrared != null) {
                notNullCount++;
            }
        }
        return notNullCount;
    }

    private TemperatureService serviceWithMockSensors(int analogDelay, int digitalDelay, int infraredDelay) {
        return new TemperatureService(
            () -> {
                sleep(analogDelay);
                return new BigDecimal(65);
            },
            (scale) -> {
                sleep(digitalDelay);
                return new BigDecimal("64.71");
            },
            () -> {
                sleep(infraredDelay);
                return new BigDecimal("65.15");
            }
        );
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}