package eigentliche_Aufgabe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RenameString {

    /**
     * Startermethode
     */
    public void renameStringStarter(){
        System.out.println("Welche Datei soll umbenannt werden? Bsp (C:\\Users\\rathg\\Desktop\\helloWorld.txt)");
        String altedatei = promiseStringFromConsole(); // Alten Dateinamen speichern.

        System.out.println("In welches Format soll die Datei umgewandelt werden? Bsp. (.html)");
        String neuedatei = promiseStringFromConsole(); // Neue Endung speichern.

        String ausgabe = changeString(altedatei, neuedatei); // Fuktion Start; übergabe von Eingabe-Variablen.
        System.out.println(ausgabe); // Ausgabe Dateipfad mit neuer Endung.
    }

    /**
     * @param input Die Methode erhält einen String
     * @return und gibt den Teil vor dem ersten Punkt zurück.
     */
    public String cutString(String input) {
        return input.substring(0, input.lastIndexOf('.')); // Rückgabe, des bis zum letzten Punkt abgeschnittenen Strings
    }

    /**
     * @param fileNameOld Eingabe des alten Strings
     * @param fileNameNew Eingabe des neuen Strings
     * @return Umbenannter String des neuen Dateinamens
     */
    public String changeString(String fileNameOld, String fileNameNew){
        String s = cutString(fileNameOld); // Rückgabe in String s speichern
        s += fileNameNew; // Neue Endung an Abgeschnittenen String hängen
        return s;
    }

    /**
     * Handling der Konsoleneingabe
     * @return String der in der Konsole eingegeben wurde.
     */
    public String eingabe(){
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.next();
        if (eingabe.matches("[a-zA-Z]+\\.[a-zA-Z]+")) {
            return eingabe;
        }
         return "";
    }

    public static String promiseStringFromConsole() {
        Scanner mainScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("↓");
                return mainScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte einen korrekten String eingeben!\033[0m");
                mainScanner.nextLine();
            }
        }
    }
}
