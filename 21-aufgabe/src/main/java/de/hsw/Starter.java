package de.hsw;

import java.math.BigInteger;
import java.security.CodeSigner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;


public class Starter {

    public static final String NAME = "HSW Bank";
    public static final String ADRESSE = "Am Stockhof 2";
    public static final int BANKLEITZAHL = 10000000;

    public static boolean end = false;

    static Bank bank = new Bank(NAME, ADRESSE, BANKLEITZAHL);

    public static void main(String[] args) {


        System.out.println("Wilkommen bei der");
        System.out.println("\n" +
                " /$$   /$$  /$$$$$$  /$$      /$$       /$$$$$$$                      /$$      \n" +
                "| $$  | $$ /$$__  $$| $$  /$ | $$      | $$__  $$                    | $$      \n" +
                "| $$  | $$| $$  \\__/| $$ /$$$| $$      | $$  \\ $$  /$$$$$$  /$$$$$$$ | $$   /$$\n" +
                "| $$$$$$$$|  $$$$$$ | $$/$$ $$ $$      | $$$$$$$  |____  $$| $$__  $$| $$  /$$/\n" +
                "| $$__  $$ \\____  $$| $$$$_  $$$$      | $$__  $$  /$$$$$$$| $$  \\ $$| $$$$$$/ \n" +
                "| $$  | $$ /$$  \\ $$| $$$/ \\  $$$      | $$  \\ $$ /$$__  $$| $$  | $$| $$_  $$ \n" +
                "| $$  | $$|  $$$$$$/| $$/   \\  $$      | $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$\n" +
                "|__/  |__/ \\______/ |__/     \\__/      |_______/  \\_______/|__/  |__/|__/  \\__/\n");

        while (!end) {

            System.out.println("--- Menü: Was möchten Sie tun? ---\n");
            System.out.println("[1]. Kunden hinzufügen");
            System.out.println("[2]. Konto eröffnen");
            System.out.println("[3]. Kunden löschen");
            System.out.println("[4]. Konto auflösen");
            System.out.println("[5]. Geld einzahlen");
            System.out.println("[6]. Geld auszahlen");
            System.out.println("[7]. Überweisung tätigen");

            switch (promiseIntFromConsole()) {
                case 1:
                    addKundeToBank();
                    break;
                case 2:
                    kontoEroeffnen();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:

                default:
                    System.out.println("\033[3mFehler: Bitte eine Zahl aus dem Menü eingeben!\033[0m");
            }

            end = true;

        }


        System.out.println("Auf Wiedersehen!");
    }

    public static void getKundenListe(){
        ArrayList<Kunde> kunden = bank.getKunden();
        for (Kunde kunde: kunden) {
            System.out.println("["+kunden.indexOf(kunde)+".] "+ kunde.getName() + ", " +  kunde.getVorname()+ " → " + kunde.getAdresse());
        }
    }

    public static void addKundeToBank() {
        System.out.println("Bitte die Kundendaten eingeben:");
        System.out.println("Vorname:");
        String vorname = promiseStringFromConsole();
        System.out.println("Nachname:");
        String nachname = promiseStringFromConsole();
        System.out.println("Adresse:");
        String adresse = promiseStringFromConsole();
        System.out.println("Geburtsdatum:");
        LocalDate geburtsdatum = promiseLocalDateFromConsole();

        bank.addKunde(nachname, vorname, adresse, geburtsdatum);
    }

    public static void kontoEroeffnen() {
        System.out.println("Welchen Kontotypmöchten Sie eröffnen? (Tagesgeld - TG oder Giro - GI)");
        String eingabe = promiseStringFromConsole();
        if (eingabe.equalsIgnoreCase("TG")) {
            Tagesgeld tagesgeld = new Tagesgeld();

            System.out.println("Es wird für Sie ein Tagesgeldkonto eröffnet:");
            showProgressBar();
            System.out.println("Die Iban lautet: " + tagesgeld.getIban());
            System.out.println("Auf das Tagesgeldkonto erhalten Sie " + tagesgeld.getZinsen() + "% Zinsen p.a.");
        } else if (eingabe.equalsIgnoreCase("GI")) {
            Giro giro = new Giro();

            System.out.println("Es wird für Sie ein Girokonto eröffnet:");
            showProgressBar();
            System.out.println("Die Iban lautet: " + giro.getIban());
            System.out.println("Das tägliche Auszahlungslimit beträgt " + giro.getMaxAuszahlung() + "€.");
            System.out.println("Außerdem dürfen Sie ihr Konto nur " + giro.getMaxDispo() + "€ überziehen.");
        } else {
            System.out.println("Kontotyp nicht erkannt. Bitte erneut versuchen.");
        }

        System.out.println("\nZu welchem Kunden möchten Sie dieses Konto hinzufügen?");
        getKundenListe();
        int kundennummer = promiseIntFromConsole();
        System.out.println("Bitte IBAN eingeben (mit Leerzeichen):");
        String iban = promiseStringFromConsole();
        bank.assignKonto(bank.getKunden().get(kundennummer), iban);
        System.out.println("Konto zu Kunden hinzugefügt:" + iban);
    }

    public static void showProgressBar() {
        for (int i = 0; i < 101; i++) {
            int progress = i / 10;
            String progressBarSteps = String.join("", Collections.nCopies(progress, "="));
            String progressBarSpaces = String.join("", Collections.nCopies(10 - progress, " "));
            String progressStart = i + "% [";
            System.out.print(progressStart + progressBarSteps + ">" + progressBarSpaces + "]\r");

            // System.out.print("  " + i + "% [" + String.join(""+Collections.nCopies(progress, "="), Collections.nCopies(10-progress, " ")) + "]\r");
            Random random = new Random();
            try {
                Thread.sleep(random.nextLong(10L, 100L));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int promiseIntFromConsole() {
        Scanner mainScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("↓");
                return mainScanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte eine Zahl eingeben!\033[0m");
            }
        }
    }

    public static String promiseStringFromConsole() {
        Scanner mainScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("↓");
                return mainScanner.next();
            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte einen String eingeben!\033[0m");
            }
        }
    }

    public static LocalDate promiseLocalDateFromConsole() {
        Scanner mainScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("[Format: dd.mm.yyyy]↓");
                String eingabe = mainScanner.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.GERMANY);
                LocalDate localDate = LocalDate.parse(eingabe, formatter);
                return localDate;
            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte einen String eingeben!\033[0m");
            }
        }
    }

}
