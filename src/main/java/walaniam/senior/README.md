# Senior — `TemperatureService`

## Task

`TemperatureService.readTemperature()` is expected to produce a
`TemperatureSnapshot` from three underlying sensors: analog, digital and
infrared.

There is an existing implementation of `readTemperature()`. Run the test
first, observe what happens, and decide whether the current implementation
satisfies the expected behaviour. If it does not, change it so it does.

## Expected behaviour

The Javadoc on `readTemperature()` states the contract the method must
satisfy. The test in this package encodes it precisely — treat the test as
the specification.

## Fixed API (do not change)

The following types nested inside `TemperatureService` are part of the
contract used by the test:

- `TemperatureSnapshot`
- `AnalogThermometerSensor`, `DigitalThermometerSensor`, `InfraredSensor`
- `TemperatureScale`

Do not change their names, visibility, or shape.

## Files

- Class under test: [`TemperatureService.java`](TemperatureService.java)
- Test (contract):
  [`src/test/java/walaniam/senior/TemperatureServiceTest.java`](../../../../../test/java/walaniam/senior/TemperatureServiceTest.java)

## Run the tests

```bash
mvn -Dtest=TemperatureServiceTest test
```
