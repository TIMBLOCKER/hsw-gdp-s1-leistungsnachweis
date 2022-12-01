package de.hsw;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Palindromerkennen {

    /**
     * Startermethode
     */
    public void palindromStarter(){
        System.out.println("Gib ein welchen String du auf ein Palindrom testen willst");
        try {
            String input = promiseStringFromConsole();
            String punctuationfree = replacePunctuation(input);
            String inverted = flipString(punctuationfree);
            if(isPalindrom(punctuationfree, inverted)){
                System.out.println("\n\033[42m\033[30mIhre Eingabe entspricht einem Palindrom!\033[0m");
            }else {
                System.out.println("\n\033[41m\033[30mIhre Eingabe ist kein Palindrom!\033[0m");
            }
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

    //Todo: Variablennamen sprechend! Done!
    //Todo: rathg fragen, was das tut! Done!

    /**
     * @param handover Diese Methode nimmt einen Eingabestring, entfernt alle Satz- und Leerzeichen und ersetzt alle Großbuchstaben mit den jeweiligen kleinen.
     * @return  Gibt den String, nur aus kleinen Buchstaben bestehend, zurück.
     * @throws IllegalArgumentException Wenn die eingabe inkorrekt ist, wird dieser Fehler geschmissen.
     */
    public String replacePunctuation(String handover) throws IllegalArgumentException{
        if (handover.matches("(?i:[abcdefghijklmnopqrstuvwxyz,.!?;\": ]+)")) {
            handover = handover.replaceAll("[^a-zA-Z]", "");
            handover = handover.replace(" ", "");
            handover = handover.toLowerCase();
            return handover;
        } else {
            throw new IllegalArgumentException("\n\033[41m\033[30mInvalide Eingabe\033[0m");
        }
    }

    /**
     * @param handover Diese Methode nimmt einen Eingabestring und dreht ihn um.
     * @return  gibt diesen umgedreht zurück.
     * @throws IllegalArgumentException Wenn die eingabe inkorrekt ist, wird dieser Fehler geschmissen.
     */
    public String flipString(String handover)throws IllegalArgumentException{
        if (handover.matches("^([abcdefghijklmnopqrstuvwxyz]+)$")) {
            String inverted = "";
            for ( int j = handover.length()-1; j >= 0; j-- ){
                inverted += handover.charAt(j);
            }
            return inverted;
        }else {
            throw new IllegalArgumentException("\n\033[41m\033[30mInvalide String\033[0m");
        }
    }

    //Todo: Return type einbinden!Done!

    /**
     * @param baseString Diese Methode nimmt zwei Strings und vergleicht diese.
     * @param invertedString Die Strings sind die Originaleingabe und der umgedrehte String.
     *           Wenn beide gleich sind, handelt es sich um ein Palindrom.
     * @return Dies wird zurückgegeben und dem Nutzer in die Konsole gedruckt.
     */
    private boolean isPalindrom(String baseString, String invertedString){
        return baseString.equals(invertedString);
    }
}
