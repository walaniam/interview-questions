# Junior — `MyHashMap`

## Task

`MyHashMap<K, V>` in this package declares that it implements
`java.util.Map<K, V>`, but the methods are only stubs. Make it a working
`Map` implementation.

## What "working" means

- The class must behave as a `java.util.Map<K, V>` for every method declared
  by that interface.
- The accompanying test defines the exact expected behaviour — treat it as
  the specification.

## Constraints

- `MyHashMap` must be a real implementation of the data structure. It must
  not delegate its storage to `java.util.HashMap` or any other existing
  `Map` implementation.

## Files

- Class under test: [`MyHashMap.java`](MyHashMap.java)
- Test (contract):
  [`src/test/java/walaniam/junior/MyHashMapTest.java`](../../../../../test/java/walaniam/junior/MyHashMapTest.java)

## Run the tests

```bash
mvn -Dtest=MyHashMapTest test
```
