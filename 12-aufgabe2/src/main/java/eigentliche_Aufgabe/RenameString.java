package eigentliche_Aufgabe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RenameString {

    //TODO eingabe errorhandling Eingabe weg! Done!


    /**
     * Startermethode
     */
    public void renameStringStarter(){
        System.out.println("Welche Datei soll umbenannt werden? Bsp (C:\\Users\\rathg\\Desktop\\helloWorld.txt)");
        String oldfile = promiseStringFromConsole();

        System.out.println("In welches Format soll die Datei umgewandelt werden? Bsp. (html)");
        String newfile = promiseStringFromConsole();
        try {
            String output = changeStringEnding(oldfile, newfile);
            System.out.println(output);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param oldFileName Eingabe des alten Strings
     * @param newFileName Eingabe des neuen Strings
     * Handling der Übergebenen Strings
     * Zusammensetzen des neuen Filenames
     * @return Umbenannter String des neuen Dateinamens
     */
    public String changeStringEnding(String oldFileName, String newFileName){
        if (oldFileName.matches("[a-zA-Z_\\\\.\\-: 0-9AÖÜüöa]+") && newFileName.matches("[a-z]+")){
            String s = oldFileName.substring(0, oldFileName.lastIndexOf('.'));
            s += "." + newFileName;
            return s;
        }else {
            throw new IllegalArgumentException("Der eingegebene Dateiname entspricht nicht den Anforderungen");
        }
    }

    /**
     * Handling der Konsoleneingabe und Eingabefilter
     * @return String der in der Konsole eingegeben wurde.
     */
    public static String promiseStringFromConsole() {
        Scanner mainScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("↓");
                String eingabe = mainScanner.nextLine();
                if (eingabe.matches("[a-zA-Z_\\\\.\\-: 0-9AÖÜüöa]+")){
                    return eingabe;
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte einen korrekten String eingeben!\033[0m");
                mainScanner.nextLine();
            }
        }
    }
}
