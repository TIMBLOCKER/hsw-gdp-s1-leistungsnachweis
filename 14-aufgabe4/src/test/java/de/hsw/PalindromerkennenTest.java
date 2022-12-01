package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromerkennenTest {

    /**
     * Test zur Bereinigung eines Strings
     */
    @Test
    void nurelaubtezeichenersetzentest1() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();
        assertEquals("ab", palindromerkennen.replaceZeichen(" A: b"));
    }

    /**
     * Gegentest zur Bereinigung eines Strings
     */
    @Test
    void nurelaubtezeichenersetzentest2() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();
        assertThrows(IllegalArgumentException.class, () -> {
                    palindromerkennen.replaceZeichen(" A_ B");
                });
    }

    /**
     * Test zum Umdrehen eines Strings
     */
    @Test
    void strumdrehenTest1() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();
        assertEquals("ba", palindromerkennen.flipString("ab"));
    }

    /**
     * Gegentest zum Umdrehen eines Strings
     */
    @Test
    void strumdrehenTest2() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();
        assertNotEquals("ab", palindromerkennen.flipString("ab"));
    }

    /**
     * Test zum Umdrehen eines Strings (Exception)
     */
    @Test
    void strumdrehenTest3() {
        Palindromerkennen palindromerkennen = new Palindromerkennen();
        assertThrows(IllegalArgumentException.class, () -> {
                    palindromerkennen.flipString("lel _");
                });
    }
}