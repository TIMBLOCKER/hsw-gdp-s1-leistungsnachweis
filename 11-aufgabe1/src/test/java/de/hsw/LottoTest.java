package de.hsw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;
import java.util.Collections;

public class LottoTest {


    /**
     * testSuperZahl testet die Random Superzahl, die zwischen 1 und 9 liegt.
     */
    @Test
    void testSuperZahl() {
        Lottozahlen lottozahlen = new Lottozahlen(1L);
        Long seed = 1L;
        assertEquals(4, lottozahlen.superZahl());
    }


    /**
     * testLottoZahlenUndZusatzZahl testet die 6 generierten LottoZahlen inklusive Zusatzzahl (7.Zahl).
     */
    @Test
    void testLottoZahlenUndZusatzZahl() {
        Integer[] testList = new Integer[] {3, 8, 10, 15, 29, 41, 47}; //expected Output in einer Collection gespeichert
        int seed = 1; //Verwendeter Seed -> 1
        int anzahl = 1; //Testet nur für eine Spielrunde
        Lottozahlen lottoZahlen = new Lottozahlen(seed);
        Set<Integer> expected = new TreeSet<>();
        Collections.addAll(expected, testList);
        assertEquals(expected, lottoZahlen.lottoscheinZahlen(anzahl)); //lottoscheinZahlen werden mit den erwarteten Zahlen (testlist) abgeglichen
    }
}
