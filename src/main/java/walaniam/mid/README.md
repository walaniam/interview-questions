# Mid — `OrderStatistics`

## Task

Implement `OrderStatistics.summarize(OrderTable orderTable)` so it returns
the correct `OrderSummaryTable` for the given input.

The Javadoc on `summarize` describes what the result should contain and how
it should be ordered. The test in this package defines the exact expected
behaviour — treat it as the specification.

## What you get

- Input row type: [`OrderTable`](OrderTable.java)
- Status enum: [`OrderStatus`](OrderStatus.java)
- Output row type: [`OrderSummaryTable`](OrderSummaryTable.java)

These types and their existing (package-private) constructors and accessors
are part of the fixed API — do not change their visibility or shape.

## Files

- Class under test: [`OrderStatistics.java`](OrderStatistics.java)
- Test (contract):
  [`src/test/java/walaniam/mid/OrderStatisticsTest.java`](../../../../../test/java/walaniam/mid/OrderStatisticsTest.java)

## Run the tests

```bash
mvn -Dtest=OrderStatisticsTest test
```
