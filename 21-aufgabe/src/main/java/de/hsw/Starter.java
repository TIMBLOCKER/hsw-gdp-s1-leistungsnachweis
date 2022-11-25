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


    static Bank bank = new Bank(NAME, ADRESSE, BANKLEITZAHL);

    public static void main(String[] args) {

        boolean end = false;

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
            System.out.println("[3]. Konto transferieren");
            System.out.println("[4]. Konto auflösen");
            System.out.println("[5]. Kunden löschen");
            System.out.println("[6]. Geld einzahlen");
            System.out.println("[7]. Geld auszahlen");
            System.out.println("[8]. Überweisung tätigen");
            System.out.println("[9]. Bank beenden");
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
                    kontoAufloesen();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    end = true;
                    break;
                default:
                    System.out.println("\033[3mFehler: Bitte eine Zahl aus dem Menü eingeben!\033[0m");
            }


        }


        System.out.println("Auf Wiedersehen!");
    }

    public static void kontoAufloesen() {
        System.out.println("Welches Konto möchten Sie auflösen?");
        getKontenListe();
        System.out.println("\nBitte IBAN eingeben:");
        String iban  = promiseStringFromConsole();
        Konto konto = bank.deleteKontofromIBAN(iban);
        geldAuszahlen(konto.getSaldo());
    }

    public static void geldAuszahlen(double amount) {
        System.out.println("Das verbleibende Saldo beträgt: " + amount + "€ und wird nun ausgezahlt:");
        for (int i = 0; i < amount; i++) {
            String moneyOutput = String.join("", Collections.nCopies(i, "[€] "));
            System.out.print(moneyOutput + "\r");
            waitThread();
        }

    }

    public static void getKontenListe() {
        ArrayList<Kunde> kunden = bank.getKunden();
        for (Kunde kunde : kunden) {
            for (Konto k : kunde.getKonten()) {
                System.out.println(k.getType() + " | " + kunde.getName() + ", " + kunde.getVorname() + " | " + k.getIban() + " | Saldo: " + k.getSaldo());
            }
        }
    }

    public static void getKundenListe() {
        ArrayList<Kunde> kunden = bank.getKunden();
        for (Kunde kunde : kunden) {
            System.out.println("[" + kunden.indexOf(kunde) + ".] " + kunde.getName() + ", " + kunde.getVorname() + " → " + kunde.getAdresse());
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
        ArrayList<Kunde> kunden = bank.getKunden();
        if(kunden.size() > 0) {
        System.out.println("\nZu welchem Kunden möchten Sie dieses Konto hinzufügen?");
        getKundenListe();
        int kundennummer = promiseIntFromConsole();
        System.out.println("Bitte IBAN eingeben (mit Leerzeichen):");
        String iban = promiseStringFromConsole();
            bank.assignKonto(kunden.get(kundennummer), iban);
            System.out.println("Konto zu Kunden hinzugefügt:" + iban);
        } else {
            System.out.println("Bitte zuerst Kunden anlegen");
        }
    }

    public static void showProgressBar() {
        for (int i = 0; i < 101; i++) {
            int progress = i / 10;
            String progressBarSteps = String.join("", Collections.nCopies(progress, "="));
            String progressBarSpaces = String.join("", Collections.nCopies(10 - progress, " "));
            String progressStart = i + "% [";
            System.out.print(progressStart + progressBarSteps + ">" + progressBarSpaces + "]\r");
            waitThread();
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

    public static void waitThread() {
        Random random = new Random();
        try {
            Thread.sleep(random.nextLong(10L, 100L));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
