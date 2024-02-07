package walaniam;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

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
            .pollInterval(10, TimeUnit.MILLISECONDS)
            .atMost(responseTimeoutMs, TimeUnit.MILLISECONDS)
            .until(() -> {
                TemperatureService.TemperatureSnapshot response = underTest.readTemperature();
                int notNullCount = 0;
                if (response != null) {
                    if (response.analog != null) {
                        notNullCount++;
                    }
                    if (response.digital != null) {
                        notNullCount++;
                    }
                    if (response.infrared != null) {
                        notNullCount++;
                    }
                }
                return notNullCount >= 2;
            });
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