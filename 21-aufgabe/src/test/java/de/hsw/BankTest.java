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

    public static Bank launchtestbank(){
        Bank banktest = new Bank();
        Kunde testkundetagesgeld = banktest.addKunde("Mustermann", "Max", "Mustermannstraße 1", LocalDate.of(1999, 7,25));
        Kunde testkundegiro = banktest.addKunde("Musterfrau", "Sabine", "Musterfraustraße 1", LocalDate.of(2000, 2,2));
        Tagesgeld tagesgeld = new Tagesgeld("DE30 5605 0180 1200 8785 91",100);
        Giro giro = new Giro("DE88 1200 8785 9156 0501 80", 1000);
        banktest.addKonto(tagesgeld);
        banktest.addKonto(giro);
        banktest.assignKonto(testkundetagesgeld, "DE30 5605 0180 1200 8785 91");
        banktest.assignKonto(testkundegiro, "DE88 1200 8785 9156 0501 80");
        return banktest;
    }

    public static Kunde testkundetagesgeld(){
        Bank banktest = new Bank();
        Kunde testkundetagesgeld = banktest.addKunde("Mustermann", "Max", "Mustermannstraße 1", LocalDate.of(1999, 7,25));
        Tagesgeld tagesgeld = new Tagesgeld("DE30 5605 0180 1200 8785 91",100);
        banktest.addKonto(tagesgeld);
        banktest.assignKonto(testkundetagesgeld, "DE30 5605 0180 1200 8785 91");
        return testkundetagesgeld;
    }

    public static Kunde testkundegiro(){
        Bank banktest = new Bank();
        Kunde testkundegiro = banktest.addKunde("Musterfrau", "Sabine", "Musterfraustraße 1", LocalDate.of(2000, 2,2));
        Giro giro = new Giro("DE88 1200 8785 9156 0501 80", 1000);
        banktest.addKonto(giro);
        banktest.assignKonto(testkundegiro, "DE88 1200 8785 9156 0501 80");
        return testkundegiro;
    }

    @Test
    void getKundefromIBANTest1() {
        Kunde testkunde1 = testkundetagesgeld();

        assertEquals(testkunde1.toString(), launchtestbank().getKundefromIBAN("DE30 5605 0180 1200 8785 91").toString());
    }

    @Test
    void getKundefromIBANTest2() {
        Kunde testkunde2 = testkundegiro();

        assertNotEquals(testkunde2.toString(), launchtestbank().getKundefromIBAN("DE30 5605 0180 1200 8785 91").toString());
    }



    @Test
    void getKontofromIBAN() {
        Konto testKonto = new Tagesgeld("DE30 5605 0180 1200 8785 91",100);

        assertEquals(testKonto.toString(), launchtestbank().getKontofromIBAN("DE30 5605 0180 1200 8785 91").toString());
    }


    @Test
    void assignKonto() {
        Bank banktest = new Bank();
        Kunde testkundegiro = banktest.addKunde("Musterfrau", "Sabine", "Musterfraustraße 1", LocalDate.of(2000, 2,2));
        Giro giro = new Giro("DE88 1200 8785 9156 0501 80", 1000);
        banktest.addKonto(giro);
        assertTrue(banktest.assignKonto(testkundegiro, "DE88 1200 8785 9156 0501 80"));
    }

    @Test
    void unAssignKonto() {
        Bank banktest = new Bank();
        Kunde testkundegiro = banktest.addKunde("Musterfrau", "Sabine", "Musterfraustraße 1", LocalDate.of(2000, 2,2));
        Giro giro = new Giro("DE88 1200 8785 9156 0501 80", 1000);
        banktest.addKonto(giro);
        banktest.assignKonto(testkundegiro, "DE88 1200 8785 9156 0501 80");
        assertEquals(giro, banktest.unAssignKonto(testkundegiro, "DE88 1200 8785 9156 0501 80"));
    }

    @Test
    void transferMoneyTest1() {
        Bank banktest = new Bank();
        Giro giro1 = new Giro("DE88 1200 8785 9156 0501 80", 1000);
        banktest.addKonto(giro1);
        Giro giro2 = new Giro("DE88 1200 8785 9156 0501 81", 1000);
        banktest.addKonto(giro2);

        assertTrue(banktest.transferMoney("DE88 1200 8785 9156 0501 80", "DE88 1200 8785 9156 0501 81",100));

    }

    @Test
    void transferMoneyTest2() {
        Bank banktest = new Bank();
        Giro giro1 = new Giro("DE88 1200 8785 9156 0501 80", 0);
        banktest.addKonto(giro1);
        Giro giro2 = new Giro("DE88 1200 8785 9156 1501 81", 0);
        banktest.addKonto(giro2);

        assertFalse(banktest.transferMoney("DE88 1200 8785 9156 0501 80", "DE88 1200 8785 9156 0501 81",100));
    }

    @Test
    void transferMoneyTest3() {
        Bank banktest = new Bank();
        Giro giro1 = new Giro("DE88 1200 8785 9156 0501 80", 10);
        banktest.addKonto(giro1);
        Giro giro2 = new Giro("DE88 1200 8785 9156 1501 81", 0);
        banktest.addKonto(giro2);

        assertFalse(banktest.transferMoney("DE88 1200 8785 9156 0501 80", "DE88 1200 8785 9156 0501 81",10000));
    }

    @Test
    void addMoneyTest1() {
        Bank banktest = new Bank();
        Giro giro1 = new Giro("DE88 1200 8785 9156 0501 80", 0);
        banktest.addKonto(giro1);
        assertTrue(banktest.addMoney("DE88 1200 8785 9156 0501 80", 10000));
    }

    @Test
    void addMoneyTest2() {
        Bank banktest = new Bank();
        Giro giro1 = new Giro("DE88 1200 8785 9156 0501 80", 0);

        assertFalse(banktest.addMoney("DE88 1200 8785 9156 0501 80", 10000));
    }

    @Test
    void outputMoneyTest1() {
        Bank banktest = new Bank();
        Giro giro1 = new Giro("DE88 1200 8785 9156 0501 80", 1000000);
        banktest.addKonto(giro1);

        assertTrue(banktest.outputMoney("DE88 1200 8785 9156 0501 80", 1000));
    }

    @Test
    void outputMoneyTest2() {
        Bank banktest = new Bank();
        Giro giro1 = new Giro("DE88 1200 8785 9156 0501 80", 1000000);

        assertFalse(banktest.outputMoney("DE88 1200 8785 9156 0501 80", 1000));
    }

    @Test
    void outputMoneyTest3() {
        Bank banktest = new Bank();
        Giro giro1 = new Giro("DE88 1200 8785 9156 0501 80", 0);

        assertFalse(banktest.outputMoney("DE88 1200 8785 9156 0501 80", 1000));
    }

    @Test
    void outputMoneyTest4() {
        Bank banktest = new Bank();
        Giro giro1 = new Giro("DE88 1200 8785 9156 0501 80", 0);

        assertFalse(banktest.outputMoney("DE88 1200 8785 9156 0501 80", 10000));
    }


}