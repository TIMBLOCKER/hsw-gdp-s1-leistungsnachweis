package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromerkennenTest {

    @Test
    void nurelaubtezeichenersetzentest1() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();

        assertEquals("ab", palindromerkennen.zeichen_ersetzen(" A: b"));
    }

    @Test
    void nurelaubtezeichenersetzentest2() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    palindromerkennen.zeichen_ersetzen(" A_ B");
                });
    }

    @Test
    void strumdrehenTest1() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();

        assertEquals("ba", palindromerkennen.strumdrehen("ab"));
    }

    @Test
    void strumdrehenTest2() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();

        assertNotEquals("ab", palindromerkennen.strumdrehen("ab"));
    }

    @Test
    void strumdrehenTest3() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    palindromerkennen.strumdrehen("lel _");
                });
    }
}