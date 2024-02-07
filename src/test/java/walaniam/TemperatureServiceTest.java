package walaniam;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

class TemperatureServiceTest {

    @ParameterizedTest
    @CsvSource({
        "100,100,100,500",
        "50,150,400,500",
        "400,50,150,500",
        "150,400,50,500",
        "100,200,200,500"
    })
    void shouldGetAtLeastTwoResponsesInExpectedTime(int analogDelay, int digitalDelay, int infraredDelay, long responseTimeoutMs) {

        final TemperatureService underTest = buildService(analogDelay, digitalDelay, infraredDelay);

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

    private TemperatureService buildService(int analogDelay, int digitalDelay, int infraredDelay) {
        return new TemperatureService(
            () -> {
                Thread.sleep(analogDelay);
                return new BigDecimal(65);
            },
            (scale) -> {
                Thread.sleep(digitalDelay);
                return new BigDecimal("64.71");
            },
            () -> {
                Thread.sleep(infraredDelay);
                return new BigDecimal("65.15");
            }
        );
    }
}