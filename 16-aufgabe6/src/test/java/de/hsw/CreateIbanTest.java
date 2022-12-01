package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateIbanTest {


    //TODO: Javadoc nachziehen
    //TODO unittests nachziehen

    @Test
    void checkFirstDigitTest1() {
        CreateIban createIban = new CreateIban();

        assertEquals(12345678, createIban.checkFirstDigit(12345678));
    }

    @Test
    void checkFirstDigitTest2() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.checkFirstDigit(1234567);
                });
    }

    @Test
    void checkFirstDigitTest3() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.checkFirstDigit(01234567);
                });
    }

    @Test
    void checkBankCodeTest1() {
        CreateIban createIban = new CreateIban();

        assertEquals(12345678, createIban.checkBankCode(12345678));
    }

    @Test
    void checkBankCodeTest2() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.checkBankCode(1234567);
                });
    }
    @Test
    void checkAccountNumberTest1() {
        CreateIban createIban = new CreateIban();

        assertEquals(1234567890, createIban.checkAccountNumber(1234567890));
    }
    @Test
    void checkAccountNumberTest2() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.checkAccountNumber(01234567);
                });
    }
    @Test
    void buildIbanTest1() {
        CreateIban createIban = new CreateIban();

        assertEquals("DE30 5605 0180 1200 8785 91", createIban.buildIban(56050180, 1200878591));
    }

    @Test
    void buildIbanTest2() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.buildIban(01234567, 1200878591);
                });
    }

    @Test
    void buildIbanTest3() {
        CreateIban createIban = new CreateIban();

        assertThrows(IllegalArgumentException.class,
                () -> {
                    createIban.buildIban(56050180, 120087859);
                });
    }



}