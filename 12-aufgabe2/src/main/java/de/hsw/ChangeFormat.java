package de.hsw;

import java.io.File;
import java.util.Scanner;

public class ChangeFormat {


    /**
     * Startermethode
     */
    public void dateiFormatStarter(){
        System.out.println("Welche Datei soll umbenannt werden? Bsp (engel.pdf)");
        String altedatei = eingabe();
        System.out.println("In was / welches Format soll die Datei umbenannt/umgewandelt werden? Bsp. (hallo.html)");
        String neuedatei = eingabe();

        changeName(altedatei, neuedatei);
    }


    /**
     * Diese Methode benennt eine Datei in dem Projektordner um
     * @param changeFrom String eingabe zum Ändern des Namens
     * @param changeTo Namen in den das File geändert werden soll
     */
    public void changeName(String changeFrom, String changeTo){
        File fileold = new File(changeFrom); // Zu bennenende File unter angegebenem Namen finden.
        File filenew = new File(changeTo); // Neuen File-Namen festlegen.

        if (fileold.exists()) { // Wenn zu bennende File existiert ->
            System.out.println(fileold.renameTo(filenew)); // Umbenennen der File in neuen Namen.
        }else {
            System.out.println("File does not exist"); // Falls File nicht existent gib ... aus.
        }
    }

    //TOdo eingabe mit error handling

    /**
     * Handling der Konsoleneingabe
     * @return String der in der Konsole eingegeben wurde.
     */
    public String eingabe(){
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.next();
        return eingabe;
    }

}
