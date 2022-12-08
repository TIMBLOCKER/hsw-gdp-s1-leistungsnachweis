package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KundeTest {

    @Test
    void addKontoTest1() {
        Kunde kunde = new Kunde();
        Tagesgeld konto = new Tagesgeld("30660348", 100);
        assertTrue(kunde.addKonto(konto));
    }

    @Test
    void addKontoTest2() {
        Kunde kunde = new Kunde();
        Giro konto = new Giro("30660348", 100);
        assertTrue(kunde.addKonto(konto));
    }

    @Test
    void addKontoTest3() {
        Kunde kunde = new Kunde();
        Konto konto = new Konto("30660348", 100);
        assertFalse(kunde.addKonto(konto));
    }

    @Test
    void deleteKontoTest1() {
        Kunde kunde = new Kunde();
        Tagesgeld konto = new Tagesgeld("30660348", 100);
        assertEquals(konto, kunde.deleteKonto(konto));
    }

    @Test
    void deleteKontoTest2() {
        Kunde kunde = new Kunde();
        Giro konto = new Giro("30660348", 100);
        assertEquals(konto, kunde.deleteKonto(konto));
    }

    @Test
    void deleteKontoTest3() {
        Kunde kunde = new Kunde();
        Konto konto = new Konto("30660348", 100);
        assertNotEquals(konto, kunde.deleteKonto(konto));
    }
}