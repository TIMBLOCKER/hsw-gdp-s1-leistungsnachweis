package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcChecksumTest {

    /**
     * Test zum Prüfen, ob das erste Zeichen der eingabe eine Zahl ist.
     */
    @Test
    void ueberpruefeersteziffertest1(){
        CalcChecksum calcChecksum = new CalcChecksum();
        assertThrows(IllegalArgumentException.class, () -> {
                    calcChecksum.checkFirstDigit("0Moin");
                });
    }

    /**
     * Gegentest zum Prüfen, ob das erste Zeichen der eingabe eine Zahl ist.
     */
    @Test
     void ueberpruefeersteziffertest2(){
        CalcChecksum calcChecksum = new CalcChecksum();
        assertEquals("A2ddd", calcChecksum.checkFirstDigit("A2ddd"));
    }

    /**
     * Test zum Prüfen, ob die eingabe korrekte Zeichen enthält
     */
    @Test
    void ueberpruefenaufzeichentest1() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertEquals("A2ddd", calcChecksum.checkInput("A2ddd"));
    }

    /**
     * Gegentest zum Prüfen, ob die eingabe korrekte Zeichen enthält
     */
    @Test
    void ueberpruefenaufzeichentest2() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertThrows(IllegalArgumentException.class, () -> {
                    calcChecksum.checkInput("M0 in-");
                });
    }

    //Todo: rathg fragen was das tut
    @Test
    void stringhochzaehlentest1() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertEquals(1, calcChecksum.onCalcChecksum("a2ddd"));
    }

    //Todo: rathg fragen was das tut
    @Test
    void stringhochzaehlentest2() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertEquals(4, calcChecksum.onCalcChecksum("Dd 2"));
    }

    /**
     * Test für das Umwandeln eines Longs in einen hexString
     */
    @Test
    void deziinhexaTest1() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertEquals("1c", calcChecksum.longtohex(28));
    }

    /**
     * Gegentest für das Umwandeln eines Longs in einen hexString
     */
    @Test
    void deziinhexaTest2() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertThrows(IllegalArgumentException.class, () -> {
                    calcChecksum.longtohex(-1);
                });
    }
}