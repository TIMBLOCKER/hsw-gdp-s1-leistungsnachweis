package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcChecksumTest {

    /**
     * Test zum Prüfen, ob die eingabe korrekte Zeichen enthält
     */
    @Test
    void checkInputtest1() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertEquals("A2ddd", calcChecksum.checkInput("A2ddd"));
    }

    /**
     * Gegentest zum Prüfen, ob die eingabe korrekte Zeichen enthält
     */
    @Test
    void checkInputtest2() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertThrows(IllegalArgumentException.class, () -> {
            calcChecksum.checkInput("M0 in-");
        });
    }

    /**
     * Test zum Prüfen, ob das erste Zeichen der eingabe eine Zahl ist.
     */
    @Test
    void checkFirstDigittest1(){
        CalcChecksum calcChecksum = new CalcChecksum();
        assertThrows(IllegalArgumentException.class, () -> {
                    calcChecksum.checkFirstDigit("0Moin");
                });
    }

    /**
     * Gegentest zum Prüfen, ob das erste Zeichen der eingabe eine Zahl ist.
     */
    @Test
     void checkFirstDigittest2(){
        CalcChecksum calcChecksum = new CalcChecksum();
        assertEquals("A2ddd", calcChecksum.checkFirstDigit("A2ddd"));
    }

    //Todo: rathg fragen was das tut! Done!

    /**
     * Test ob Rechnung von Konsonanten und Vokalen richtig funktioniert.
     */
    @Test
    void onCalcChecksumtest1() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertEquals(2, calcChecksum.onCalcChecksum("addd"));
    }

    //Todo: rathg fragen was das tut! Done!

    /**
     * Test ob Rechnung von Konsonanten und Vokalen, ungeachtet der Groß-undKleinschreibung, richtig funktioniert.
     */
    @Test
    void onCalcChecksumtest2() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertEquals(1, calcChecksum.onCalcChecksum("aAdDd"));
    }

    /**
     * Test ob Rechnung von Konsonanten und Vokalen, ungeachtet Leerzeichen und Satzzeichen, richtig funktioniert.
     */
    @Test
    void onCalcChecksumtest3() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertEquals(1, calcChecksum.onCalcChecksum("aA,d Dd"));
    }

    /**
     * Test ob Rechnung von Konsonanten und Vokalen, mit mal-2 Multiplikator, richtig funktioniert.
     */
    @Test
    void onCalcChecksumtest4() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertEquals(4, calcChecksum.onCalcChecksum("aAd dDd2"));
    }

    /**
     * Test für das Umwandeln eines Longs in einen hexString
     */
    @Test
    void convertToHexTest1() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertEquals("1c", calcChecksum.convertToHex(28));
    }

    /**
     * Gegentest für das Umwandeln eines Longs in einen hexString
     */
    @Test
    void convertToHexTest2() {
        CalcChecksum calcChecksum = new CalcChecksum();
        assertThrows(IllegalArgumentException.class, () -> {
                    calcChecksum.convertToHex(-1);
                });
    }
}