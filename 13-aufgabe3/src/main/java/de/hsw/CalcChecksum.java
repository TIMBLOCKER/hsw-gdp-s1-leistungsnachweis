package de.hsw;

import java.util.Scanner;

public class CalcChecksum {


    /**
     * Startermethode
     */
    public void checksumStarter(){
        System.out.println("Bitte gib deinen String an: ");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine();
        String s = "", l = "";
        try {
            s = checkInput(eingabe);
            l = checkFirstDigit(s);

            long checksumme = onCalcChecksum(l);

            String hexa = longtohex(checksumme);
            System.out.println("Die Checksumme ist: " + hexa);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }}


    /**
     * @param eingabe Man gibt der Methode einen String mit
     * @return Man erhält die eingabe nur dann zurück, wenn am Beginn des Strings keine Ziffer steht
     * @throws IllegalArgumentException Falls das doch der Fall ist, wird diese geworfen
     */
    public String checkFirstDigit(String eingabe) throws IllegalArgumentException{
        String firstChar = eingabe.charAt(0) + "";
        if (firstChar.matches("-?\\d+")){
            throw new IllegalArgumentException("Eine Zahl steht an der Front");
        } else {
            return eingabe;
        }}

    /**
     * @param eingabe
     * @return
     */
    //Todo: rathg fragen was das macht
    public long onCalcChecksum(String eingabe){
        long checksumme = 0;
        eingabe = eingabe.replace(" ", "");
        for (int i = 0; i < eingabe.length(); i++) {
            char c = eingabe.charAt(i);
            if (String.valueOf(c).matches("^(?i:[bcdfghjklmnpqrstvwxyz]+)$")){
                checksumme++;
            }
            if (String.valueOf(c).matches("^(?i:[aeiou]+)$")){
                checksumme--;
            }
            if (String.valueOf(c).matches("^(?i:[0123456789]+)$")){
                checksumme = checksumme * 2;
            }}
            return checksumme;
    }

    /**
     * @param eingabe Man gibt der Methode einen String mit
     * @return Man erhält die eingabe nur dann zurück, wenn keine unerwarteten Zeichen im String sind
     * @throws IllegalArgumentException Falls das doch der Fall ist, wird diese geworfen
     */
    public String checkInput(String eingabe) throws IllegalArgumentException{
        if (eingabe.matches("^(?i:[abcdefghijklmnopqrstuvwxyz 0123456789]+)$")){
            return eingabe;
        }
        throw new IllegalArgumentException("Dein String enthält illegale Zeichen!");
    }


    /**
     * @param input Zahl die in Hex String konvertiert werden soll
     * @return Gibt eine Zahl zurück die
     * @throws IllegalArgumentException Wenn die Checksumme negativ ist wird der Fehler geworfen
     */
    public String longtohex(long input)throws IllegalArgumentException{
        if (input < 0){
            throw new IllegalArgumentException("Die Checksumme wäre Negativ! Deine negative Checksumme lautet: " + input);
        }else {
            String hex = Integer.toHexString((int) input);
            return hex;
        }}
}
