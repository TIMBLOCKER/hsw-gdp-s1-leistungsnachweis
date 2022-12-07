package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KontoTest {

    @Test
    void generateIBANDE() {
        Konto konto = new Konto();


    }

    @Test
    void generateChecksumTest1() {
        Konto konto = new Konto();

        assertEquals(95, konto.generateChecksum("1200878591","56050180"));
    }

    @Test
    void generateChecksumTest2() {
        Konto konto = new Konto();

        assertThrows(NumberFormatException.class,
                () -> konto.generateChecksum("120087859i", "10000000"));

        assertThrows(NumberFormatException.class,
                () -> konto.generateChecksum("1200878591", "1000000a"));
    }

    //TODO: Filter zu lang oder falsches Format
    @Test
    void generateChecksumTest3() {
        Konto konto = new Konto();

        assertThrows(NumberFormatException.class,
                () -> konto.generateChecksum("12008785911", "10000000"));

        assertThrows(NumberFormatException.class,
                () -> konto.generateChecksum("1200878591", "10000000"));
    }


    @Test
    void assembleIbanTest1() {
        Konto konto = new Konto();

        assertEquals("DE95 1000 0000 1200 8785 91", konto.assembleIban("1200878591", "10000000", "95"));
    }


    //ToDo: Filter Buchstaben
    @Test
    void assembleIbanTest2() {
        Konto konto = new Konto();

        assertThrows(NumberFormatException.class,
                () -> konto.assembleIban("120087859i", "10000000", "95"));

        assertThrows(NumberFormatException.class,
                () -> konto.assembleIban("1200878591", "1000000i", "95"));

        assertThrows(NumberFormatException.class,
                () -> konto.assembleIban("1200878591", "10000000", "9i"));
    }

    //ToDo: Filter Zahlengröße
    @Test
    void assembleIbanTest3() {
        Konto konto = new Konto();

        assertThrows(NumberFormatException.class,
                () -> konto.assembleIban("12008785911", "10000000", "95"));

        assertThrows(NumberFormatException.class,
                () -> konto.assembleIban("1200878591", "100000000", "95"));

        assertThrows(NumberFormatException.class,
                () -> konto.assembleIban("1200878591", "10000000", "955"));
    }
}