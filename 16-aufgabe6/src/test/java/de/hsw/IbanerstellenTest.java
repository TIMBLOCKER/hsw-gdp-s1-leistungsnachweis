package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class createIbanTest {

    @Test
    void eingabe() {

    }

    @Test
    void erstezifferueberpruefenTest1() {
        createIban createIban = new createIban();

        assertEquals("12345678", createIban.erstezifferueberpruefen("12345678"));
    }

    @Test
    void erstezifferueberpruefenTest2() {
        createIban createIban = new createIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.erstezifferueberpruefen("1234567");
                });
    }

    @Test
    void erstezifferueberpruefenTest3() {
        createIban createIban = new createIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.erstezifferueberpruefen("01234567");
                });
    }

    @Test
    void bankleitzahl() {

    }

    @Test
    void kontonummer() {
    }

    @Test
    void ibanbauen() {
    }

}