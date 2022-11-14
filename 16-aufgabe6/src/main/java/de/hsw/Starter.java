package de.hsw;
public class Starter {
    public static void main(String[] args) {
        Code code = new Code();

        String bankleitzahl = String.valueOf(code.eingabefilter("Geben Sie bitte die Bankleitzahl ein"));
        if (bankleitzahl.length() == 8) {

            System.out.println("");

            String kontonummer = String.valueOf(code.eingabefilter("Geben Sie bitte die Kontonummer ein"));

            System.out.println("");

            if (kontonummer.length() == 10){
                String pruefziffer = String.valueOf(code.pruefziffer(kontonummer, bankleitzahl, "131400"));

                System.out.println("Die eingegebene BLZ lautet: " + bankleitzahl);
                System.out.println("");
                System.out.println("Die eingegebene Kontonummer lautet: " + kontonummer);
                System.out.println("");
                System.out.println("Die generierte IBAN lautet: " + code.ibanzsmfuegen(bankleitzahl, kontonummer, pruefziffer));
            }else {
                System.out.println("Deine Eingabe ist fehlerhaft!");
            }


        }else {
            System.out.println("Deine Eingabe ist fehlerhaft!");
        }
    }
}