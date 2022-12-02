package de.hsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangeFormatTest {

    /**
     * Überprüft, ob ein Exception geworfen wird, wenn die handoverChangeForm variable illegale Zeichen enthält.
     */
    @Test
    void changeNameTest1() {
        ChangeFormat changeFormat = new ChangeFormat();
        assertThrows(IllegalArgumentException.class, () -> changeFormat.changeName("moin!", "moin"));
    }

    /**
     * Überprüft, ob ein Exception geworfen wird, wenn die handoverChangeTo variable illegale Zeichen enthält.
     */
    @Test
    void changeNameTest2() {
        ChangeFormat changeFormat = new ChangeFormat();
        assertThrows(IllegalArgumentException.class, () -> changeFormat.changeName("moin", "moin!"));
    }

    /**
     * Überprüft, ob die Methode bei zulässigem String den eingegebenen String zurückgibt.
     */
    @Test
    void filterChangeFromTest1() {
        ChangeFormat changeFormat = new ChangeFormat();
        assertEquals("Ab_0", changeFormat.filterChangeFrom("Ab_0"));
    }

    /**
     * Überprüft, ob ein Exception geworfen wird, wenn die handover variable illegale Zeichen enthält.
     */
    @Test
    void filterChangeFromTest2() {
        ChangeFormat changeFormat = new ChangeFormat();
        assertThrows(IllegalArgumentException.class, () -> changeFormat.filterChangeFrom("moin!"));
    }

    /**
     * Überprüft, ob die Methode bei zulässigem String den eingegebenen String zurückgibt.
     */
    @Test
    void filterChangeToTest1() {
        ChangeFormat changeFormat = new ChangeFormat();
        assertEquals("css", changeFormat.filterChangeTo("css"));
    }

    /**
     * Überprüft, ob ein Exception geworfen wird, wenn die handover variable illegale Zeichen enthält.
     */
    @Test
    void filterChangeToTest2() {
        ChangeFormat changeFormat = new ChangeFormat();
        assertThrows(IllegalArgumentException.class, () -> changeFormat.filterChangeTo("moin!"));
    }
}