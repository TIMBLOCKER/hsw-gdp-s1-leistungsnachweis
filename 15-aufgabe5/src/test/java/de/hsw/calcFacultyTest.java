package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class calcFacultyTest {

    //todo einmal tests durchlaufen lassen wg bigint

    /**
     * filterInput soll nur ganzzahlige Eingaben im Positiven durchlassen.
     * Hier wird geprüft, dass eine Kommazahl abgewiesen wird.
     */
    @Test
    void filterTest1() {
        CalcFaculty calcFaculty = new CalcFaculty();
        assertThrows(IllegalArgumentException.class, () -> {
                    calcFaculty.filterInput("1.6");
                });
    }

    /**
     * filterInput soll nur ganzzahlige Eingaben im Positiven durchlassen.
     * Hier wird geprüft, dass eine negative Zahl abgewiesen wird.
     */
    @Test
    void filterTest2() {
        CalcFaculty calcFaculty = new CalcFaculty();
        assertThrows(IllegalArgumentException.class, () -> {
                    calcFaculty.filterInput("-1");
                });
    }
    /**
     * filterInput soll nur ganzzahlige Eingaben im Positiven durchlassen.
     * Hier wird geprüft, dass eine korrekte Eingabe durchgelassen wird.
     */
    @Test
    void filterTest3() {
        CalcFaculty calcFaculty = new CalcFaculty();
        assertEquals(3, calcFaculty.filterInput("3"));
    }
    /**
     * filterInput soll nur ganzzahlige Eingaben im Positiven durchlassen.
     * Hier wird geprüft, dass Buchstaben abgewiesen werden.
     */
    @Test
    void filterTest4() {
        CalcFaculty calcFaculty = new CalcFaculty();
        assertThrows(IllegalArgumentException.class, () -> {
                    calcFaculty.filterInput("ABC");
                });
    }

    /**
     * Hier wird geprüft, dass die Methode wirklich die richtige Fakultät berechnet.
     */
    @Test
    void fakberechnentest1() {
        CalcFaculty calcFaculty = new CalcFaculty();
        assertEquals(6, calcFaculty.facultyCalc(3));
    }
}