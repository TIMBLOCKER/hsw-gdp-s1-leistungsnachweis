package de.hsw;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

//Todo Klassen werden immer groß geschrieben
//todo camelCase
public class CreateIban{
    public void startIban(){
        try {
            System.out.println("Bitte geben Sie ihre Bankleitzahl ein.");
            int eingabe = input();
            int ersteziffertrue = checkFirstDigit(eingabe);
            int bankleitzahl = checkBankCode(ersteziffertrue);
            System.out.println("\nBitte geben Sie Ihre Kontonummer ein.");
            String kontonummertrue = String.format("%1$010d", input());
            int kontonummer = checkAccountNumber(kontonummertrue);
            String iban = buildIban(bankleitzahl, kontonummer);
            output(bankleitzahl, kontonummer, iban);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Todo change to promise Methode -> Bank
    public int input()throws InputMismatchException {
        Scanner mainScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("↓");
                return mainScanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte eine Zahl eingeben!\033[0m");
                mainScanner.nextLine();
            }
        }
    }

    public int checkFirstDigit(int uebergabe)throws IllegalArgumentException{
        //TODO: erst hier in String umwandeln
        String eingabe = String.valueOf(uebergabe);
        if (eingabe.length() != 8){
            throw new IllegalArgumentException("Ihre Eingabe ist nicht genau 8 Stellen lang!");
        }else if (eingabe.matches("[1-8][0-9]{7}")){
            return uebergabe;
        }else{
            throw new IllegalArgumentException("Die erste Zahl darf keine 0 oder 9 sein!");
        }
    }

    public int checkBankCode(int uebergabe)throws IllegalArgumentException{
        String bankleitzahl = String.valueOf(uebergabe);
        if (bankleitzahl.length() == 8){
            return uebergabe;
        }else{
            throw new IllegalArgumentException("Die Bankleitzahl muss 8 Stellen haben!");
        }
    }

    public int checkAccountNumber(int uebergabe)throws IllegalArgumentException{
        String kontonummer = String.valueOf(uebergabe);
        if (kontonummer.length() == 10){
            return uebergabe;
        }else{
            throw new IllegalArgumentException("Die Kontonummer konnte nicht erstellt werden!");
        }
    }
//todo camelCase
    public String buildIban(int b, int k)  {
        String laenderkennziffer = "131400";
        k = 120087859;
        String bankleitzahl = String.valueOf(checkBankCode(b));
        String kontonummer = String.valueOf(checkAccountNumber(k));

        String zsmfuegung = bankleitzahl + kontonummer + laenderkennziffer;
        BigInteger vormodulo = new BigInteger(zsmfuegung);
        BigInteger faktor = new BigInteger("97");
        long div = vormodulo.remainder(faktor).longValue();
        String pruefziffer = String.valueOf(98 - div);

        String iban = "DE" + pruefziffer + bankleitzahl + kontonummer;
        return iban.subSequence(0, 4) + " " + iban.subSequence(4, 8) + " " + iban.substring(8, 12) + " " + iban.substring(12, 16) + " " + iban.substring(16, 20) + " " + iban.substring(20, 22);
    }


    //todo ausgabe formatieren
    private void output(int bankleitzahl, int kontonummer, String iban){
        System.out.println("\nDie eingegebene BLZ lautet: " + bankleitzahl);
        System.out.println("\nDie eingegebene Kontonummer lautet: " + kontonummer);
        System.out.println("\nDie generierte IBAN lautet: " + iban);
    }

}
