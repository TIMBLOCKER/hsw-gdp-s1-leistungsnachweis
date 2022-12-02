package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcFacultyTest {



    /**
     * Hier wird geprüft, dass die Methode für die Eingabe 0, als Fakultät eine 1 ausgibt.
     */
    @Test
    void facultyCalcTest1() {
        CalcFaculty calcFaculty = new CalcFaculty();
        assertEquals("1", calcFaculty.facultyCalc(0));
    }

    /**
     * Hier wird geprüft, dass die Methode wirklich die richtige Fakultät berechnet.
     */
    @Test
    void facultyCalcTest2() {
        CalcFaculty calcFaculty = new CalcFaculty();
        assertEquals("6", calcFaculty.facultyCalc(3));
    }

    /**
     * Hier wird geprüft, dass die Methode für negative Werte, eine Exception wirft.
     */
    @Test
    void facultyCalcTest3() {
        CalcFaculty calcFaculty = new CalcFaculty();
        assertThrows(IllegalArgumentException.class, () -> {
            calcFaculty.facultyCalc(-1);
        });
    }
}