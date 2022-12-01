package de.hsw;

import java.math.BigInteger;
import java.util.InputMismatchException;
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

            long[] kontonummer = checkAccountNumber(input());
            String iban = buildIban(bankleitzahl, kontonummer[0], kontonummer[1]);
            output(bankleitzahl, kontonummer[0], kontonummer[1], iban);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handling der Konsoleneingabe und Eingabefilter
     * @return String der in der Konsole eingegeben wurde.
     */
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

    public long[] checkAccountNumber(long uebergabe)throws IllegalArgumentException {
        if (uebergabe >= 0) {
            String.format("%1$010d", uebergabe);
            String kontonummer = String.valueOf(uebergabe);
            if (kontonummer.length() == 10) {
                int i = 0;
                while (kontonummer.substring(i).equals("0")){
                    i++;
                }
                long[] kontonummer1 = {uebergabe, Long.valueOf(i)};
                return kontonummer1;
            } else {
                throw new IllegalArgumentException("Die Kontonummer konnte nicht erstellt werden!");
            }
        }else {
            throw new IllegalArgumentException();
        }
    }

//todo camelCase
    public String buildIban(int b, long k1, long k2)  {
        String laenderkennziffer = "131400";
        String nullen = "";
        for (int i = 0; i < k2; i++) {
            nullen += 0;
        }
        String k = nullen + k1;
        String bankleitzahl = String.valueOf(checkBankCode(b));
        String kontonummer = String.valueOf(checkAccountNumber(Long.parseLong(k)));

        String zsmfuegung = bankleitzahl + kontonummer + laenderkennziffer;
        BigInteger vormodulo = new BigInteger(zsmfuegung);
        BigInteger faktor = new BigInteger("97");
        long div = vormodulo.remainder(faktor).longValue();
        String pruefziffer = String.valueOf(98 - div);

        String iban = "DE" + pruefziffer + bankleitzahl + kontonummer;
        return iban.subSequence(0, 4) + " " + iban.subSequence(4, 8) + " " + iban.substring(8, 12) + " " + iban.substring(12, 16) + " " + iban.substring(16, 20) + " " + iban.substring(20, 22);
    }


    //todo ausgabe formatieren
    private void output(int bankleitzahl, long k1, long k2, String iban){
        System.out.println("\nDie eingegebene BLZ lautet: " + bankleitzahl);
        System.out.println("\nDie eingegebene Kontonummer lautet: " + String.valueOf(k1) + k2);
        System.out.println("\nDie generierte IBAN lautet: " + iban);
    }

}
