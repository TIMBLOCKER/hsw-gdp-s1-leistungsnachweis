package de.hsw;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChangeFormat {


    /**
     * Startermethode
     */
    public void dateiFormatStarter(){
        System.out.println("Welche Datei soll umbenannt werden? Bsp.: engel.html");
        String oldName = promiseStringFromConsole();
        System.out.println("In welches Format soll die Datei umgewandelt werden? Bsp.: css");
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
    public String promiseStringFromConsole() {
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
     * @param handoverChangeTo Namen in den das File geändert werden soll.
     * @throws IllegalArgumentException wenn die zu verändernde File nicht existiert.
     */
    public void changeName(String handoverChangeForm, String handoverChangeTo){
        String changeFrom = filterChangeFrom(handoverChangeForm);
        String changeTo = filterChangeTo(handoverChangeTo);
        String toLastDot = changeFrom.substring(0, changeFrom.lastIndexOf('.'));
        File oldFile = new File(changeFrom);
        if (oldFile.exists()){
            File newFile = new File(toLastDot + "." + changeTo);
            System.out.println("\n\033[42m\033[30m" + oldFile.renameTo(newFile)+ "\033[0m");
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
    public String filterChangeFrom(String handover){
        if (handover.matches("[a-zA-Z_\\\\.\\-: 0-9AÖÜüöa]+")){
            return handover;
        }else {
            throw new IllegalArgumentException("\n\033[41m\033[30mDie Eingabe besteht nicht aus erlaubten Zeichen!\033[0m");
        }
    }

    /**
     * Diese Methode überprüft einen String auf illegale Zeichen.
     * @param handover String eingabe zum Überprüfen des Strings.
     * @return Den eingegebenen String, wenn er den vorgegebenen zeichen entspricht.
     * @throws IllegalArgumentException wenn der String nicht den erlaubten Zeichen entspricht.
     */
    public String filterChangeTo(String handover){
        if (handover.matches("[a-z]+")){
            return handover;
        }else {
            throw new IllegalArgumentException("\n\033[41m\033[30mDie Eingabe darf nur aus kleinen Buchstaben bestehen!\033[0m");
        }
    }
}
