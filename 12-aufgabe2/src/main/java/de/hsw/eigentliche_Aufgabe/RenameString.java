package de.hsw.eigentliche_Aufgabe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RenameString {




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
            System.out.println("\n\033[42m\033[30m" + output + "\033[0m");
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
            String result = oldFileName.substring(0, oldFileName.lastIndexOf('.'));
            result += "." + newFileName;
            return result;
        }else {
            throw new IllegalArgumentException("\n\033[41m\033[30mDer eingegebene Dateiname entspricht nicht den Anforderungen\033[0m");
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
                System.out.println("\n\033[41m\033[30mFehler: Bitte einen korrekten String eingeben!\033[0m");
                mainScanner.nextLine();
            }
        }
    }
}
