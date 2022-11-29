package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FakultatberechnenTest {

    @Test
    void filterTest1() {
        Fakultatberechnen fakultatberechnen = new Fakultatberechnen();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    fakultatberechnen.filter("1.6");
                });
    }

    @Test
    void filterTest2() {
        Fakultatberechnen fakultatberechnen = new Fakultatberechnen();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    fakultatberechnen.filter("-1");
                });
    }

    @Test
    void filterTest3() {
        Fakultatberechnen fakultatberechnen = new Fakultatberechnen();

        assertEquals(3, fakultatberechnen.filter("3"));
    }

    @Test
    void filterTest4() {
        Fakultatberechnen fakultatberechnen = new Fakultatberechnen();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    fakultatberechnen.filter("ABC");
                });
    }

    @Test
    void fakberechnentest1() {
        Fakultatberechnen fakultatberechnen = new Fakultatberechnen();

        assertEquals(6, fakultatberechnen.fakberechnen(3));
    }
}