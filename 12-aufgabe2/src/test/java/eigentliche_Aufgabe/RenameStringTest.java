package eigentliche_Aufgabe;

import de.hsw.eigentliche_Aufgabe.RenameString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RenameStringTest {



    /**
     * Test zum Überprüfen ob der Filename richtig zusammengesetzt wurde
     */
    @Test
    void changeStringEndingTest1() {
        RenameString renameString = new RenameString();

        assertEquals("C:\\Users\\rathg\\Desktop\\helloWorld.html", renameString.changeStringEnding("C:\\Users\\rathg\\Desktop\\helloWorld.txt", "html"));
    }

    /**
     * Test zum Überprüfen, ob die Exception, bei falscher Übergabe der ersten Variable geworfen wird.
     */
    @Test
    void changeStringEndingTest2() {
        RenameString renameString = new RenameString();

        assertThrows(IllegalArgumentException.class, () -> {
            renameString.changeStringEnding("C:\\Users\\rathg\\Desktop\\helloW!rld.txt", "html");
        });
    }

    /**
     * Test zum Überprüfen, ob die Exception, bei falscher Übergabe der zweiten Variable geworfen wird.
     */
    @Test
    void changeStringEndingTest3() {
        RenameString renameString = new RenameString();

        assertThrows(IllegalArgumentException.class, () -> {
            renameString.changeStringEnding("C:\\Users\\rathg\\Desktop\\helloWorld.txt", ".html");
        });
    }
}