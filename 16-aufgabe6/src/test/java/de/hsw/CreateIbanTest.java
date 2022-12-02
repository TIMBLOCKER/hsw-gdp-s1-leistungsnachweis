package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateIbanTest {


    //TODO: Javadoc nachziehen! Done!
    //TODO unittests nachziehen! Done!

    /**
     * Test prüft, ob Ziffern von 1-9, die an erster Stelle stehen, durchgelassen werden.
     */
    @Test
    void checkFirstDigitTest1() {
        CreateIban createIban = new CreateIban();

        assertEquals(12345678, createIban.checkFirstDigit(12345678));
    }

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn die eingabe nicht aus genau 8 Ziffern besteht.
     */
    @Test
    void checkFirstDigitTest2() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.checkFirstDigit(1234567);
                });
    }

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn die Ziffern 0 und 9, die an erster Stelle stehen.
     */
    @Test
    void checkFirstDigitTest3() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.checkFirstDigit(01234567);
                });
    }

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn die Ziffern 0 und 9, die an erster Stelle stehen.
     */
    @Test
    void checkFirstDigitTest4() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.checkFirstDigit(91234567);
                });
    }

    /**
     * Test überprüft, ob eine positive 8 stellige zahl druchgelassen wird.
     */
    @Test
    void checkBankCodeTest1() {
        CreateIban createIban = new CreateIban();

        assertEquals(12345678, createIban.checkBankCode(12345678));
    }

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn die Zahl nicht genau 8 Stellen hat.
     */
    @Test
    void checkBankCodeTest2() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.checkBankCode(1234567);
                });
    }

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn die Zahl negativ ist.
     */
    @Test
    void checkBankCodeTest3() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.checkBankCode(-1234567);
                });
    }

    /**
     * Test überprüft, ob eine positive 10 stellige zahl druchgelassen wird.
     */
    @Test
    void checkAccountNumberTest1() {
        CreateIban createIban = new CreateIban();

        assertEquals("1234567890", createIban.checkAccountNumber("1234567890"));
    }

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn die Zahl nicht genau 10 Ziffern hat.
     */
    @Test
    void checkAccountNumberTest2() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.checkAccountNumber("12345678901");
                });
    }

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn die Zahl negativ ist.
     */
    @Test
    void checkAccountNumberTest3() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.checkAccountNumber("-12345678901");
                });
    }

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn der String nicht ausschließlich aus Ziffern besteht.
     */
    @Test
    void checkAccountNumberTest4() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.checkAccountNumber("123456789A12");
                });
    }

    /**
     * Test überprüft, ob die Iban richtig erstellt wird.
     */
    @Test
    void buildIbanTest1() {
        CreateIban createIban = new CreateIban();

        assertEquals("DE30 5605 0180 1200 8785 91", createIban.buildIban(56050180, "1200878591", "131400"));
    }

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn an erster Stelle der bankCode variable die Ziffern 0 oder 9 stehen.
     */
    @Test
    void buildIbanTest2() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.buildIban(01234567, "1200878591", "131400");
                });
    }
    //ToDo: Tim fragen ob man das lassen kann weil die methoden checkBankcode und checkAccountNumber schon getestet werden.

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn die bankcode variable negativ ist.
     */
    @Test
    void buildIbanTest3() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.buildIban(-1234567, "1234567890", "131400");
                });
    }

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn die accountNumber variable Zeichen enthält die keine Ziffern sind.
     */
    @Test
    void buildIbanTest4() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.buildIban(1234567, "12345,67890", "131400");
                });
    }

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn die accountNumber variable negativ ist.
     */
    @Test
    void buildIbanTest5() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.buildIban(1234567, "-1234567890", "131400");
                });
    }

    /**
     * Test überprüft, ob eine Exception geworfen wird, wenn die countryCheckDigit variable nicht in den möglichen Länderkennziffern enthalten ist.
     */
    @Test
    void buildIbanTest6() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.buildIban(12345678, "1234567890", "131-400");
                });
    }
}