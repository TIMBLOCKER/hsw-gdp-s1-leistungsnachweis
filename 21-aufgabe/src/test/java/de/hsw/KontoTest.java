package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KontoTest {

    @Test
    void generateIBANDETest1() {
        Konto konto = new Konto(1L);

        assertEquals("DE90 1000 0000 5376 4896 73", konto.generateIBANDE("10000000"));
    }

    @Test
    void generateIBANDETest2() {
        Konto konto = new Konto(1L);

        assertThrows(IllegalArgumentException.class,
                () -> konto.generateIBANDE("1000000"));

        assertThrows(IllegalArgumentException.class,
                () -> konto.generateIBANDE("100000000"));

        assertThrows(NumberFormatException.class,
                () -> konto.generateIBANDE("1000000A"));

        assertThrows(IllegalArgumentException.class,
                () -> konto.generateIBANDE("-10000000"));

        assertThrows(IllegalArgumentException.class,
                () -> konto.generateIBANDE("01000000"));

        assertThrows(IllegalArgumentException.class,
                () -> konto.generateIBANDE("91000000"));
    }

    @Test
    void generateChecksumTest1() {
        Konto konto = new Konto();

        assertEquals(30, konto.generateChecksum("1200878591","56050180"));
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

        assertThrows(IllegalArgumentException.class,
                () -> konto.generateChecksum("12008785911", "10000000"));

        assertThrows(IllegalArgumentException.class,
                () -> konto.generateChecksum("1200878591", "100000000"));
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

        assertThrows(IllegalArgumentException.class,
                () -> konto.assembleIban("12008785911", "10000000", "95"));

        assertThrows(IllegalArgumentException.class,
                () -> konto.assembleIban("1200878591", "100000000", "95"));

        assertThrows(IllegalArgumentException.class,
                () -> konto.assembleIban("1200878591", "10000000", "955"));
    }

    @Test
    void filterktnTest1() {
        Konto konto = new Konto();

        assertEquals("1200878591", konto.filterktn("1200878591"));
    }

    @Test
    void filterktnTest2() {
        Konto konto = new Konto();

        assertThrows(IllegalArgumentException.class,
                () -> konto.filterktn("-12008785911"));

        assertThrows(IllegalArgumentException.class,
                () -> konto.filterktn("120087859110"));

        assertThrows(NumberFormatException.class,
                () -> konto.filterktn("1200878591a"));
    }

    @Test
    void filterblzTest1() {
        Konto konto = new Konto();

        assertEquals("56050180", konto.filterblz("56050180"));
    }

    @Test
    void filterblzTest2() {
        Konto konto = new Konto();

        assertThrows(IllegalArgumentException.class,
                () -> konto.filterblz("-56050180"));

        assertThrows(IllegalArgumentException.class,
                () -> konto.filterblz("560501800"));

        assertThrows(NumberFormatException.class,
                () -> konto.filterblz("5605018A"));
    }

    @Test
    void filterpzTest1() {
        Konto konto = new Konto();

        assertEquals("30", konto.filterpz("30"));
    }

    @Test
    void filterpzTest2() {
        Konto konto = new Konto();

        assertThrows(IllegalArgumentException.class,
                () -> konto.filterpz("-30"));

        assertThrows(IllegalArgumentException.class,
                () -> konto.filterpz("300"));

        assertThrows(NumberFormatException.class,
                () -> konto.filterpz("3A"));
    }
}