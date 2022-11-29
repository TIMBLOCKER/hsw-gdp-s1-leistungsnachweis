package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChecksummeberechnenTest {

    @Test
    void ueberpruefeersteziffertest1(){
        Checksummeberechnen checksummeberechnen = new Checksummeberechnen();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    checksummeberechnen.ueberpruefeersteziffer("0Moin");
                });
    }

    @Test
    void ueberpruefeersteziffertest2(){
        Checksummeberechnen checksummeberechnen = new Checksummeberechnen();

        assertEquals("A2ddd", checksummeberechnen.ueberpruefeersteziffer("A2ddd"));
    }

    @Test
    void ueberpruefenaufzeichentest1() {
        Checksummeberechnen checksummeberechnen = new Checksummeberechnen();

        assertEquals("A2ddd", checksummeberechnen.ueberpruefenaufzeichen("A2ddd"));
    }

    @Test
    void ueberpruefenaufzeichentest2() {
        Checksummeberechnen checksummeberechnen = new Checksummeberechnen();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    checksummeberechnen.ueberpruefenaufzeichen("M0 in-");
                });
    }

    @Test
    void stringhochzaehlentest1() {
        Checksummeberechnen checksummeberechnen = new Checksummeberechnen();

        assertEquals(1, checksummeberechnen.checksummenberechnung("a2ddd"));
    }

    @Test
    void stringhochzaehlentest2() {

        Checksummeberechnen checksummeberechnen = new Checksummeberechnen();

        assertEquals(4, checksummeberechnen.checksummenberechnung("Dd 2"));
    }

    @Test
    void deziinhexaTest1() {

        Checksummeberechnen checksummeberechnen = new Checksummeberechnen();

        assertEquals("1c", checksummeberechnen.deziinhexa(28));
    }

    @Test
    void deziinhexaTest2() {

        Checksummeberechnen checksummeberechnen = new Checksummeberechnen();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    checksummeberechnen.deziinhexa(-1);
                });
    }
}