package de.hsw;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CreateIban{
    public void startIban(){
        try {
            System.out.println("Bitte geben Sie ihre Bankleitzahl ein.");
            int input = input();
            int checkedFirstDigit = checkFirstDigit(input);
            long bankcode = checkBankCode(checkedFirstDigit);
            System.out.println("\nBitte geben Sie Ihre Kontonummer ein.");
            String countryCheckDigit = "131400";
            String tenDigtits = String.format("%1$010d", input());
            String accountNumber = checkAccountNumber(tenDigtits);
            String iban = buildIban(bankcode, accountNumber, countryCheckDigit);
            output(bankcode, accountNumber, iban);
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
                System.out.println("\n\033[41m\033[30mFehler: Bitte eine Zahl eingeben!\033[0m");
                mainScanner.nextLine();
            }
        }
    }

    public int checkFirstDigit(int uebergabe)throws IllegalArgumentException{

        String eingabe = String.valueOf(uebergabe);
        if (eingabe.length() != 8){
            throw new IllegalArgumentException("\n\033[41m\033[30mIhre Eingabe ist nicht genau 8 Stellen lang!\033[0m");
        }else if (eingabe.matches("[1-8][0-9]{7}")){
            return uebergabe;
        }else{
            throw new IllegalArgumentException("\n\033[41m\033[30mDie erste Zahl darf keine 0 oder 9 sein!\033[0m");
        }
    }

    public long checkBankCode(long handover)throws IllegalArgumentException{
        if (handover>=0){
            String bankcode = String.valueOf(handover);
            if (bankcode.length() == 8){
                return handover;
            }else{
                throw new IllegalArgumentException("\n\033[41m\033[30mDie Bankleitzahl muss 8 Stellen haben!\033[0m");
            }
        }else {
            throw new IllegalArgumentException("\n\033[41m\033[30mBitte nur positive Bankleitzahlen\033[0m");
        }
    }

    public String checkAccountNumber(String accountNumber)throws IllegalArgumentException {
        long accountNumberIntoLong = Long.parseLong(accountNumber);
        if (accountNumberIntoLong >= 0) {
            if (accountNumber.length() == 10) {
                return accountNumber;
            } else {
                throw new IllegalArgumentException("\n\033[41m\033[30mDie Kontonummer konnte nicht erstellt werden!\033[0m");
            }
        }else {
            throw new IllegalArgumentException("\n\033[41m\033[30mDie Eingabe darf nicht negativ sein!\033[0m");
        }
    }


    public String buildIban(long bankcodeHandover, String accountNumberHandover, String countryCheckDigit)  {
        String countryCode = "";
        String bankcode = String.valueOf(checkBankCode(bankcodeHandover));
        String accountNumber = String.valueOf(checkAccountNumber(accountNumberHandover));

        String merge = bankcode + accountNumber + countryCheckDigit;
        BigInteger beforeModulo = new BigInteger(merge);
        BigInteger factor = new BigInteger("97");
        long div = beforeModulo.remainder(factor).longValue();
        String checkDigit = String.valueOf(98 - div);
        switch (countryCheckDigit){//Hier switch für die einfache Erweiterbarkeit in der Zukunft
            case "131400":
                countryCode = "DE";
                break;
            default:
                throw new IllegalArgumentException("\n\033[41m\033[30mBitte eine gültige Länderkennziffer eingeben!\033[0m");
        }

        String iban = countryCode + checkDigit + bankcode + accountNumber;
        return iban.subSequence(0, 4) + " " + iban.subSequence(4, 8) + " " + iban.substring(8, 12) + " " + iban.substring(12, 16) + " " + iban.substring(16, 20) + " " + iban.substring(20, 22);
    }



    private void output(long bankleitzahl, String kontonummer, String iban){
        System.out.println("\n\033[42m\033[30mDie eingegebene BLZ lautet: " + bankleitzahl + "\033[0m");
        System.out.println("\n\033[42m\033[30mDie eingegebene Kontonummer lautet: " + kontonummer + "\033[0m");
        System.out.println("\n\033[42m\033[30mDie generierte IBAN lautet: " + iban + "\033[0m");
    }
}
