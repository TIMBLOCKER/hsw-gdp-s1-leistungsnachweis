package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromerkennenTest {

    /**
     * Test zur Bereinigung eines Strings.
     */
    @Test
    void replacePunctuationtest1() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();
        assertEquals("ab", palindromerkennen.replacePunctuation(" A: b"));
    }

    /**
     * Test zur Funktionalität des Filters.
     */
    @Test
    void replacePunctuationtest2() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();
        assertThrows(IllegalArgumentException.class, () -> {
                    palindromerkennen.replacePunctuation(" A_ B");
                });
    }

    /**
     * Test zum Umdrehen eines Strings.
     */
    @Test
    void flipStringTest1() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();
        assertEquals("ba", palindromerkennen.flipString("ab"));
    }

    /**
     * Gegentest zum Umdrehen eines Strings.
     */
    @Test
    void flipStringTest2() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();
        assertNotEquals("ab", palindromerkennen.flipString("ab"));
    }

    /**
     * Test zum Umdrehen eines Strings. (Exception)
     */
    @Test
    void flipStringTest3() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();
        assertThrows(IllegalArgumentException.class, () -> {
                    palindromerkennen.flipString("lel _");
                });
    }
}