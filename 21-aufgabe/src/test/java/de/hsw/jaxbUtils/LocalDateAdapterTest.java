package de.hsw.jaxbUtils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateAdapterTest {

    @Test
    void unmarshalTest1() throws Exception {
        LocalDateAdapter localDateAdapter = new LocalDateAdapter();

        assertEquals(LocalDate.of(2000, 2,2).toString(), localDateAdapter.unmarshal("2000-02-02").toString());
    }

    @Test
    void unmarshalTest2() {
        LocalDateAdapter localDateAdapter = new LocalDateAdapter();

        assertThrows(IllegalArgumentException.class,
                () -> localDateAdapter.unmarshal("2000/02/02"));

        assertThrows(IllegalArgumentException.class,
                () -> localDateAdapter.unmarshal("200O0-02-02"));
    }

    @Test
    void marshalTest1() {
        LocalDateAdapter localDateAdapter = new LocalDateAdapter();

        assertEquals("2000-02-02", LocalDate.of(2000, 2,2));
    }

    //ToDo: Filter
    @Test
    void marshalTest2() {
        LocalDateAdapter localDateAdapter = new LocalDateAdapter();

        assertThrows(DateTimeParseException.class,
                () -> localDateAdapter.marshal(LocalDate.of(200, 2,2)));

        assertThrows(DateTimeParseException.class,
                () -> localDateAdapter.marshal(LocalDate.of(20000, 2,2)));
    }
}