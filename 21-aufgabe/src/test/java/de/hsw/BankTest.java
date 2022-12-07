package de.hsw;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    void addKunde() {
        Bank bank = new Bank();

        Kunde kunde = new Kunde("Test", "Test", "Teststraße 3", LocalDate.of(1999, 7,25));

        assertEquals(kunde.toString(), bank.addKunde("Test", "Test", "Teststraße 3", LocalDate.of(1999, 7,25)).toString());
    }

    /*@Test
    void deleteKunde() {

    }*/

    @Test
    void deleteKundeAtPosition() {
    }

    @Test
    void addKonto() {
    }

    /*@Test
    void deleteKontofromIBAN() {
    }*/

    @Test
    void getKundefromIBAN() {
        Bank bank = new Bank();
        Kunde kunde = new Kunde("Test", "Test", "Teststraße 3", LocalDate.of(1999, 7,25));

        assertEquals(kunde, );
    }

    @Test
    void getKontofromIBAN() {
    }

    @Test
    void deleteKonto() {
    }

    @Test
    void assignKonto() {
    }

    @Test
    void unAssignKonto() {
    }

    @Test
    void transferMoney() {
    }

    @Test
    void addMoney() {
    }

    @Test
    void outputMoney() {
    }
}