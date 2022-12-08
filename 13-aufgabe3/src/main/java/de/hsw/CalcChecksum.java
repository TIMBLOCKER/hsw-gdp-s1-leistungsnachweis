package de.hsw;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CalcChecksum {


    /**
     * Startermethode
     */
    public void checksumStarter(){
        System.out.println("Von welchem String möchtest du die Checksumme berechnen?");
        String input = promiseStringFromConsole();
        String checkedInput, checkedFirstDigit;
        try {
            checkedInput = checkInput(input);
            checkedFirstDigit = checkFirstDigit(checkedInput);
            long checkSum = onCalcChecksum(checkedFirstDigit);
            String hexNumber = convertToHex(checkSum).toUpperCase();
            System.out.println("\n\033[42m\033[30mDie Checksumme ist: " + hexNumber + "\033[0m");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handling der Konsoleneingabe und Eingabefilter
     * @return String der in der Konsole eingegeben wurde.
     */
    public String promiseStringFromConsole() {
        Scanner mainScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("↓");
                return mainScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\n\033[41m\033[30mFehler: Bitte einen korrekten String eingeben!\033[0m");
                mainScanner.nextLine();
            }
        }
    }

    /**
     * Diese Methode überprüft den String auf illegale Zeichen.
     * @param eingabe Man gibt der Methode einen String mit.
     * @return Man erhält die eingabe nur dann zurück, wenn keine unerwarteten Zeichen im String sind.
     * @throws IllegalArgumentException Falls das doch der Fall ist, wird diese geworfen.
     */
    public String checkInput(String eingabe) throws IllegalArgumentException{
        if (eingabe.matches("^(?i:[a-z 0-9,.!?:\";]+)$")){
            return eingabe;
        }
        throw new IllegalArgumentException("\n\033[41m\033[30mDein String enthält illegale Zeichen!\033[0m");
    }

    /**
     * Diese Methode überprüft, ob das erste Zeichen eines String eine Ziffer ist.
     * @param eingabe Man gibt der Methode einen String mit.
     * @return Man erhält die eingabe nur dann zurück, wenn am Beginn des Strings keine Ziffer steht.
     * @throws IllegalArgumentException Falls das doch der Fall ist, wird diese geworfen.
     */
    public String checkFirstDigit(String eingabe) throws IllegalArgumentException{
        String firstChar = eingabe.charAt(0) + "";
        if (firstChar.matches("-?\\d+")){
            throw new IllegalArgumentException("\n\033[41m\033[30mEine Zahl steht an der Front\033[0m");
        } else {
            return eingabe;
        }}



    /**
     * @param handover
     * Folgendes wird für jedes Zeichen im String wiederholt.
     *  Erhöht für jeden Konsonanten die Checksumme um 1.
     *  Verringert für jeden Vokal die Checksumme um 1.
     *  Verdoppelt die Checksumme für jede Zahl.
     * @return checksum
     */
    public long onCalcChecksum(String handover){
        String checkedInput = checkInput(handover);
        String input = checkFirstDigit(checkedInput);
        long checksum = 0;
        input = input.replace(" ", "");
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (String.valueOf(c).matches("^(?i:[bcdfghjklmnpqrstvwxyz])")){
                checksum++;
            }
            if (String.valueOf(c).matches("^(?i:[aeiou])")){
                checksum--;
            }
            if (String.valueOf(c).matches("[0-9]")){
                checksum = checksum * 2;
            }}
            return checksum;
    }

    /**
     * Diese Methode wandelt die eingegebene Zahl in das hexadezimal-System um
     * @param input Zahl die in Hex String konvertiert werden soll
     * @return Gibt eine Zahl zurück, die in das Hexadezimal-System konvertiert wurde.
     * @throws IllegalArgumentException Wenn die Checksumme negativ ist, wird der Fehler geworfen
     */
    public String convertToHex(long input)throws IllegalArgumentException{
        if (input < 0){
            throw new IllegalArgumentException("\n\033[41m\033[30mDie Checksumme wäre Negativ! Deine negative Checksumme lautet: " + input + "\033[0m");
        }else {
            input = -1;
            return Integer.toHexString((int) input);
        }
    }
}
