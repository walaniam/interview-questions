package walaniam.junior;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MyHashMapTest {

    @Test
    void shouldAddAndFindElements() {

        final Map<String, String> underTest = new MyHashMap<>();

        underTest.put("key1", "value1");
        underTest.put("key2", "value2");
        underTest.put("key3", "value3");

        assertThat(underTest).hasSize(3);
        assertThat(underTest).extractingByKey("key1").isEqualTo("value1");
        assertThat(underTest).extractingByKey("key2").isEqualTo("value2");
        assertThat(underTest).extractingByKey("key3").isEqualTo("value3");

        String oldForKey1 = underTest.put("key1", "newValue1");
        assertThat(oldForKey1).isEqualTo("value1");
        assertThat(underTest).extractingByKey("key1").isEqualTo("newValue1");

        String removedForKey2 = underTest.remove("key2");
        assertThat(removedForKey2).isEqualTo("value2");

        assertThat(underTest.keySet()).containsExactlyInAnyOrder("key1", "key3");
        assertThat(underTest).hasSize(2);
    }

}