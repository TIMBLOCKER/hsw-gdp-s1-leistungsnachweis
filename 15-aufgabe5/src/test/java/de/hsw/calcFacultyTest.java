package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class calcFacultyTest {

    @Test
    void filterTest1() {
        CalcFaculty calcFaculty = new CalcFaculty();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    calcFaculty.filterInput("1.6");
                });
    }

    @Test
    void filterTest2() {
        CalcFaculty calcFaculty = new CalcFaculty();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    calcFaculty.filterInput("-1");
                });
    }

    @Test
    void filterTest3() {
        CalcFaculty calcFaculty = new CalcFaculty();

        assertEquals(3, calcFaculty.filterInput("3"));
    }

    @Test
    void filterTest4() {
        CalcFaculty calcFaculty = new CalcFaculty();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    calcFaculty.filterInput("ABC");
                });
    }

    @Test
    void fakberechnentest1() {
        CalcFaculty calcFaculty = new CalcFaculty();

        assertEquals(6, calcFaculty.facultyCalc(3));
    }
}