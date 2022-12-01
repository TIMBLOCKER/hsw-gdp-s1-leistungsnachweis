package de.hsw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

public class StarterTest {

        //Todo: Javadoc nachziehen und Tests optimieren, Var in CamelCase --> halloDat


    @Test
    void testSuperZahlMin() {
        Lottozahlen lottoZahlen = new Lottozahlen(1L);
        Long seed = 1L;
        assertEquals(4, lottoZahlen.superZahl());

    }

    @Test
    void testSuperZahlMax() {
        Lottozahlen lottozahlen = new Lottozahlen(1L);
        Long seed = 1L;
        assertEquals(4, lottozahlen.superZahl());
    }


    @Test
    void testLottoZahlenUndZusatzZahl() { //Testet die 6 generierten LottoZahlen inklusive Zusatzzahl (7.Zahl)
        int seed = 1;
        Set<Integer> set = new TreeSet<>();
        int anzahl = 1;
        Lottozahlen lottoZahlen = new Lottozahlen(seed);
        Set<Integer> expected = new TreeSet<>();
        expected.add(3); //weniger Zeilen damit Code cleaner erscheint
        expected.add(8);
        expected.add(10);
        expected.add(15);
        expected.add(29);
        expected.add(41);
        expected.add(47);
        assertEquals(expected, lottoZahlen.lottoscheinZahlen(set, anzahl));
    }
}
