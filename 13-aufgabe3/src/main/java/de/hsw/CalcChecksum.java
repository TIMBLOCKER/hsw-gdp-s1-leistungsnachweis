package de.hsw;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CalcChecksum {
    //TODO: Variablennamen sprechend machen! Done!

    /**
     * Startermethode
     */
    public void checksumStarter(){
        System.out.println("Bitte gib deinen String an: ");
        String input = promiseStringFromConsole();
        String checkedInput, checkedFirstDigit;
        try {
            checkedInput = checkInput(input);
            checkedFirstDigit = checkFirstDigit(checkedInput);
            long checkSum = onCalcChecksum(checkedFirstDigit);
            String hexNumber = convertToHex(checkSum);
            System.out.println("Die Checksumme ist: " + hexNumber);

        }catch (Exception e){
            System.out.println(e.getMessage());
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
                return mainScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte einen korrekten String eingeben!\033[0m");
                mainScanner.nextLine();
            }
        }
    }

    /**
     * @param eingabe Man gibt der Methode einen String mit.
     * @return Man erhält die eingabe nur dann zurück, wenn keine unerwarteten Zeichen im String sind.
     * @throws IllegalArgumentException Falls das doch der Fall ist, wird diese geworfen.
     */
    public String checkInput(String eingabe) throws IllegalArgumentException{
        if (eingabe.matches("^(?i:[a-z 0-9,.!?:\";]+)$")){
            return eingabe;
        }
        throw new IllegalArgumentException("Dein String enthält illegale Zeichen!");
    }

    /**
     * @param eingabe Man gibt der Methode einen String mit.
     * @return Man erhält die eingabe nur dann zurück, wenn am Beginn des Strings keine Ziffer steht.
     * @throws IllegalArgumentException Falls das doch der Fall ist, wird diese geworfen.
     */
    public String checkFirstDigit(String eingabe) throws IllegalArgumentException{
        String firstChar = eingabe.charAt(0) + "";
        if (firstChar.matches("-?\\d+")){
            throw new IllegalArgumentException("Eine Zahl steht an der Front");
        } else {
            return eingabe;
        }}

    //Todo: rathg fragen was das macht! Done!

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
     * @param input Zahl die in Hex String konvertiert werden soll
     * @return Gibt eine Zahl zurück, die in das Hexadezimal-System konvertiert wurde.
     * @throws IllegalArgumentException Wenn die Checksumme negativ ist, wird der Fehler geworfen
     */
    public String convertToHex(long input)throws IllegalArgumentException{
        if (input < 0){
            throw new IllegalArgumentException("Die Checksumme wäre Negativ! Deine negative Checksumme lautet: " + input);
        }else {
            return Integer.toHexString((int) input);
        }
    }
}
