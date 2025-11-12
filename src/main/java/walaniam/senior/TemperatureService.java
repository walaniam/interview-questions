package walaniam.senior;

import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

/**
 * Service for providing {@linkplain TemperatureSnapshot}.
 * Have a look at {@linkplain #readTemperature()} documentation for expected behaviour.
 * Run TemperatureServiceTest to verify implementation.
 */
public class TemperatureService {

    private final AnalogThermometerSensor analogSensor;
    private final DigitalThermometerSensor digitalSensor;
    private final InfraredSensor infraredSensor;

    TemperatureService(AnalogThermometerSensor analogSensor, DigitalThermometerSensor digitalSensor,
                       InfraredSensor infraredSensor) {
        this.analogSensor = analogSensor;
        this.digitalSensor = digitalSensor;
        this.infraredSensor = infraredSensor;
    }

    /**
     * This method calls three underlying services to get temperature snapshot.
     *
     * <b>Requirements</b>
     * Execution of this method is considered valid when:
     * <ul>
     *     <li>in max of 500ms returns TemperatureSnapshot having at least two internal responses</li>
     *     <li>throws TimeoutException when not able to collect at least two internal responses in 500ms</li>
     * </ul>
     * <b>Examples</b> of valid executions:
     * <ul>
     *     <li>in 500ms analog + digital</li>
     *     <li>in 500ms analog + infrared</li>
     *     <li>in 500ms digital + infrared</li>
     *     <li>in 500ms analog + digital + infrared</li>
     *     <li>when above 500ms TimeoutException</li>
     * </ul>
     */
    TemperatureSnapshot readTemperature() throws TimeoutException {
        BigDecimal analog = analogSensor.currentTemperatureCelsius();
        BigDecimal digital = digitalSensor.currentTemperature(TemperatureScale.CELCIUS);
        BigDecimal infrared = infraredSensor.currentTemperatureCelsius();
        return new TemperatureSnapshot(analog, digital, infrared);
    }

    static class TemperatureSnapshot {
        final BigDecimal analog;
        final BigDecimal digital;
        final BigDecimal infrared;

        TemperatureSnapshot(BigDecimal analog, BigDecimal digital, BigDecimal infrared) {
            this.analog = analog;
            this.digital = digital;
            this.infrared = infrared;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("TemperatureSnapshot{");
            sb.append("analog=").append(analog);
            sb.append(", digital=").append(digital);
            sb.append(", infrared=").append(infrared);
            sb.append('}');
            return sb.toString();
        }
    }

    interface AnalogThermometerSensor {
        BigDecimal currentTemperatureCelsius();
    }

    interface DigitalThermometerSensor {
        BigDecimal currentTemperature(TemperatureScale scale);
    }

    enum TemperatureScale {
        CELCIUS, KALVIN
    }

    interface InfraredSensor {
        BigDecimal currentTemperatureCelsius();
    }
}
