package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateIbanTest {


    //TODO: Javadoc nachziehen
    //TODO unittests nachziehen

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
    void bankleitzahlTest1() {
        createIban createIban = new createIban();

        assertEquals("12345678", createIban.bankleitzahleingabe("12345678"));
    }

    @Test
    void bankleitzahlTest2() {
        createIban createIban = new createIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.bankleitzahleingabe("01234567");
                });
    }
    @Test
    void kontonummerTest1() {
        createIban createIban = new createIban();

        assertEquals("1234567890", createIban.kontonummereingabe("1234567890"));
    }
    @Test
    void kontonummerTest2() {
        createIban createIban = new createIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.kontonummereingabe("01234567");
                });
    }
    @Test
    void ibanbauenTest1() {
        createIban createIban = new createIban();

        assertEquals("1234567890", createIban.ibanbauen("56050180", "1200878591", "131400"));
    }

}