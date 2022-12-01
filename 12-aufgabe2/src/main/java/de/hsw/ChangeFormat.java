package de.hsw;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChangeFormat {

    //TODO: Wenn nötig Unitest! Kein Plan wie!

    /**
     * Startermethode
     */
    public void dateiFormatStarter(){
        System.out.println("Welche Datei soll umbenannt werden? Bsp (engel.html)");
        String oldName = promiseStringFromConsole();
        System.out.println("In was / welches Format soll die Datei umbenannt/umgewandelt werden? Bsp. (engel.css)");
        String newName = promiseStringFromConsole();
        try {
            changeName(oldName, newName);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Diese Methode benennt eine Datei in dem Projektordner um
     * @param changeFrom String eingabe zum Ändern des Namens
     * @param changeTo Namen in den das File geändert werden soll
     */
    public void changeName(String changeFrom, String changeTo){
        File oldFile = new File(changeFrom);
        if (oldFile.exists()){
            File newFile = new File(changeTo);
            System.out.println(oldFile.renameTo(newFile));
        }else {
            throw new IllegalArgumentException("Diese Datei existiert nicht!");
        }

    }

    /**
     * Handling der Konsoleneingabe
     * @return String der in der Konsole eingegeben wurde.
     */
    public static String promiseStringFromConsole() {
        Scanner mainScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("↓");
                return mainScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte einen String eingeben!\033[0m");
                mainScanner.nextLine();
            }
        }
    }
}
