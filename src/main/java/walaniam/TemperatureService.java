package walaniam;

import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

public class TemperatureService {

    private final AnalogThermometerSensor analogSensor;
    private final DigitalThermometerSensor digitalSensor;
    private final InfraredSensor infraredSensor;

    public TemperatureService(AnalogThermometerSensor analogSensor, DigitalThermometerSensor digitalSensor,
                              InfraredSensor infraredSensor) {
        this.analogSensor = analogSensor;
        this.digitalSensor = digitalSensor;
        this.infraredSensor = infraredSensor;
    }

    /**
     * This method calls three underlying services to get temperature snapshot. It should return within 500ms, otherwise
     * there's no point in waiting for the response and {@link TimeoutException} should be thrown.
     * <p>
     * TemperatureSnapshot should contain at least two internal responses but more is better.
     * E.g. analog + digital or analog + infrared or digital + infrared is a minimum.
     */
    TemperatureSnapshot readTemperature() throws TimeoutException {
        BigDecimal analog = analogSensor.currentTemperatureCelcius();
        BigDecimal digital = digitalSensor.currentTemperature(TemperatureScale.CELCIUS);
        BigDecimal infrared = infraredSensor.currentTemperatureCelcius();
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
        BigDecimal currentTemperatureCelcius();
    }

    interface DigitalThermometerSensor {
        BigDecimal currentTemperature(TemperatureScale scale);
    }

    enum TemperatureScale {
        CELCIUS, KALVIN
    }

    interface InfraredSensor {
        BigDecimal currentTemperatureCelcius();
    }
}
