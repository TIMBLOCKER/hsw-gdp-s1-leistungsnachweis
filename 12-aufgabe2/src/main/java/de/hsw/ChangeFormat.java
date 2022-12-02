package de.hsw;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChangeFormat {


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
     * Handling der Konsoleneingabe
     * @return String der in der Konsole eingegeben wurde.
     */
    public static String promiseStringFromConsole() {
        Scanner mainScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("↓");
                String input = mainScanner.nextLine();
                if (input.matches("[a-zA-Z_\\\\.\\-: 0-9AÖÜüöa]+")){
                    return input;
                }else {
                    System.out.println("\n\033[41m\033[30mFehler: Bitte einen String eingeben!\033[0m");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n\033[41m\033[30mFehler: Bitte einen String eingeben!\033[0m");
                mainScanner.nextLine();
            }
        }
    }

    /**
     * Diese Methode benennt eine Datei in dem Projektordner um.
     * @param handoverChangeForm String eingabe zum Ändern des Namens.
     * @param handoverchangeTo Namen in den das File geändert werden soll.
     * @throws IllegalArgumentException wenn die zu verändernde File nicht existiert.
     */
    public void changeName(String handoverChangeForm, String handoverchangeTo){
        String changeFrom = filter(handoverChangeForm);
        String changeTo = filter(handoverchangeTo);
        File oldFile = new File(changeFrom);
        if (oldFile.exists()){
            File newFile = new File(changeTo);
            System.out.println(oldFile.renameTo(newFile));
        }else {
            throw new IllegalArgumentException("\n\033[41m\033[30mDiese Datei existiert nicht!\033[0m");
        }

    }

    /**
     * Diese Methode überprüft einen String auf illegale Zeichen.
     * @param handover String eingabe zum Überprüfen des Strings.
     * @return Den eingegebenen String, wenn er den vorgegebenen zeichen entspricht.
     * @throws IllegalArgumentException wenn der String nicht den erlaubten Zeichen entspricht.
     */
    public String filter(String handover){
        if (handover.matches("[a-zA-Z_\\\\.\\-: 0-9AÖÜüöa]+")){
            return handover;
        }else {
            throw new IllegalArgumentException("Die Eingabe besteht nicht aus erlaubten Zeichen!");
        }

    }
}
