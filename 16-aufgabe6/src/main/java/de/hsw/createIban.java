package de.hsw;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

//Todo Klassen werden immer groß geschrieben
//todo camelCase
public class createIban{
    public void Ibanstarten(){
        try {
            String laenderkennziffer = "131400";
            System.out.println("Bitte geben Sie ihre Bankleitzahl ein.");
            String eingabe = String.valueOf(eingabe());
            String ersteziffertrue = erstezifferueberpruefen(eingabe);
            String bankleitzahl = bankleitzahleingabe(ersteziffertrue);

            System.out.println("\nBitte geben Sie Ihre Kontonummer ein.");
            String zehnstellentrue = String.format("%1$010d", eingabe());
            String kontonummer = kontonummereingabe(zehnstellentrue);
            String iban = ibanbauen(bankleitzahl, kontonummer, laenderkennziffer);
            ausgabe(bankleitzahl, kontonummer, iban);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Todo change to promise Methode -> Bank
    public int eingabe()throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nIhre Eingabe ist keine Zahl! Bitte geben Sie eine Zahl ein");
                scanner.nextLine();
            }
        }
    }

    public String erstezifferueberpruefen(String eingabe)throws IllegalArgumentException{
        if (eingabe.length() != 8){
            throw new IllegalArgumentException("Ihre Eingabe ist nicht genau 8 Stellen lang!");
        }else if (eingabe.matches("[1-8][0-9]{7}")){
            return eingabe;
        }else{
            throw new IllegalArgumentException("Die erste Zahl darf keine 0 oder 9 sein!");
        }
    }

    public String bankleitzahleingabe(String bankleitzahl)throws IllegalArgumentException{
        if (bankleitzahl.length() == 8){
            return bankleitzahl;
        }else{
            throw new IllegalArgumentException("Die Bankleitzahl muss 8 Stellen haben!");
        }
    }

    public String kontonummereingabe(String kontonummer)throws IllegalArgumentException{
        if (kontonummer.length() == 10){
            return kontonummer;
        }else{
            throw new IllegalArgumentException("Die Kontonummer konnte nicht erstellt werden!");
        }
    }
//todo camelCase
    public String ibanbauen(String bankleitzahl, String kontonummer, String laenderkennziffer)  {

        String zsmfuegung = bankleitzahl + kontonummer + laenderkennziffer;
        BigInteger vormodulo = new BigInteger(zsmfuegung);
        BigInteger faktor = new BigInteger("97");
        try {
            long div = vormodulo.remainder(faktor).longValue();
        }catch (Exception e){

        }
            //todo error
        String pruefziffer = String.valueOf(98 - div);

        String iban = "DE" + pruefziffer + bankleitzahl + kontonummer;
        return iban.subSequence(0, 4) + " " + iban.subSequence(4, 8) + " " + iban.substring(8, 12) + " " + iban.substring(12, 16) + " " + iban.substring(16, 20) + " " + iban.substring(20, 22);
    }


    //todo ausgabe formatieren
    private void ausgabe(String bankleitzahl, String kontonummer, String iban){
        System.out.println("\nDie eingegebene BLZ lautet: " + bankleitzahl);
        System.out.println("\nDie eingegebene Kontonummer lautet: " + kontonummer);
        System.out.println("\nDie generierte IBAN lautet: " + iban);
    }

}
