package de.hsw;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class Starter {




    private static Bank bank;

    public static void main(String[] args) {

        try {
            bank = loadBank();
            System.out.println("Bank laden...");
            showProgressBar();
            System.out.println("Die Bank wurde geladen!");
        } catch (JAXBException | IllegalArgumentException e) {
            System.out.println("\033[3mFehler: Die Bank konnte nicht geladen werden!\033[0m");
            System.out.println(e.getMessage());
            bank = new Bank();
        }

        if (bank == null) {
            bank = new Bank();

            System.out.println("\033[3mDie Bank wurde neu instanziiert.\033[0m");
        }


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
            System.out.println("[3]. Konto auflösen");
            System.out.println("[4]. Kunden löschen");
            System.out.println("[5]. Geld einzahlen");
            System.out.println("[6]. Geld auszahlen");
            System.out.println("[7]. Überweisung tätigen");
            System.out.println("[8]. Bank beenden");
            switch (promiseIntFromConsole()) {
                case 1:
                    addKundeToBank();
                    break;
                case 2:
                    kontoEroeffnen();
                    break;
                case 3:
                    kontoAufloesen();
                    break;
                case 4:
                    kundeLoeschen();
                    break;
                case 5:
                    geldEinzahlen();
                    break;
                case 6:
                    geldAuszahlen();
                    break;
                case 7:
                    geldTransfer();
                    break;
                case 8:
                    end = true;
                    break;
                default:
                    System.out.println("\033[3mFehler: Bitte eine Zahl aus dem Menü eingeben!\033[0m");
            }
        }
        System.out.println("Auf Wiedersehen!");
        try {
            saveBank(bank);
        } catch (JAXBException e) {
            System.out.println("\033[3mFehler: Die Bank konnte nicht gespeichert werden!\033[0m");
            throw new RuntimeException(e);
        }
    }

    public static void geldTransfer(){
        System.out.println("Von welchem Konto soll die Überweisung erfolgen?");
        System.out.println("Bitte IBAN eingeben:");
        String ibanfrom = promiseStringFromConsole();
        System.out.println("Auf welches Konto soll die Überweisung erfolgen?" );
        System.out.println("Bitte IBAN eingeben:");
        String ibanto = promiseStringFromConsole();
        System.out.println("Bitte Betrag eingeben:");
        double amount = promiseDoubleFromConsole();
        if (amount > 0){
            bank.transferMoney(ibanfrom, ibanto, amount);
            System.out.println("Es wurden " + amount + "€ von " + ibanfrom + " auf das Konto " + ibanto + " überwiesen.");
            System.out.println("Neues Saldo des Debitors: " + bank.getKontofromIBAN(ibanfrom).getSaldo() + "€");
            System.out.println("Neues Saldo des Creditors: " + bank.getKontofromIBAN(ibanto).getSaldo() + "€");
        }else{
            System.out.println("\033[3mFehler: Der Eingezahlte Betrag muss positiv sein!\033[0m");
        }
    }


    public static void geldAuszahlen(){
        System.out.println("Von welchem Konto möchten Sie Geld auszahlen?");
        System.out.println("Bitte IBAN eingeben:");
        String iban = promiseStringFromConsole();
        System.out.println("Wie viel Geld möchten Sie vom Konto: " + iban + " auszahlen? (ohne Währungszeichen)");
        double amount = promiseDoubleFromConsole();
        if (amount > 0){
            bank.outputMoney(iban, amount);
            geldAuszahlen(amount);
        }else{
            System.out.println("\033[3mFehler: Der Eingezahlte Betrag muss positiv sein!\033[0m");
        }
    }


    public static void geldEinzahlen(){
        System.out.println("Auf welches Konto möchten Sie Geld einzahlen?");
        System.out.println("Bitte IBAN eingeben:");
        String iban = promiseStringFromConsole();
        System.out.println("Wie viel Geld soll auf das Konto: " + iban + " eingezahlt werden?");
        double amount = promiseDoubleFromConsole();
        if (amount > 0){
            try {
                bank.addMoney(iban, amount);
                System.out.println("Es wurden " + amount + "€ eingezahlt.");
            }catch (NullPointerException e){
                System.out.println("\033[3mFehler: Das Konto wurde nicht gefunden!\033[0m");
            }
        }else{
            System.out.println("\033[3mFehler: Der Eingezahlte Betrag muss positiv sein!\033[0m");
        }
    }


    public static void kundeLoeschen(){
        System.out.println("Welcher Kunde soll gelöscht werden?");
        getKundenListe();
        System.out.println("Bitte Kundennummer eingeben:");
        int position = promiseIntFromConsole();
        Kunde kunde = bank.deleteKundeAtPosition(position);
        System.out.println("Der Kunde: " + kunde.getName() + ", " + kunde.getVorname() + " wurde gelöscht...");
    }

    public static void kontoAufloesen() {
        System.out.println("Welches Konto möchten Sie auflösen?");
        getKontenListe();
        System.out.println("\nBitte IBAN eingeben:");
        String iban = promiseStringFromConsole();
        Konto konto = bank.unAssignKonto(bank.getKundefromIBAN(iban), iban);
        geldAufloesen(konto.getSaldo());
    }

    public static void geldAuszahlen(double amount) {
        System.out.println("Es werdem nun " + amount + "€ ausgezahlt:");
        for (int i = 0; i < amount; i++) {
            String moneyOutput = String.join("", Collections.nCopies(i, "[€] "));
            System.out.print(moneyOutput + "\r");
            waitThread();
        }

    }

    public static void geldAufloesen(double amount) {
        System.out.println("Das verbleibende Saldo beträgt " + amount + "€ und wird nun ausgezahlt:");
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

        Kunde neuKunde = bank.addKunde(nachname, vorname, adresse, geburtsdatum);
        System.out.println("Kunde wurde hinzugefügt: " + neuKunde.getName() + ", " + neuKunde.getName() + " → "+ neuKunde.getAdresse());
    }

    public static void kontoEroeffnen() {
        System.out.println("Welchen Kontotypmöchten Sie eröffnen? (Tagesgeld - TG oder Giro - GI)");
        String eingabe = promiseStringFromConsole();
        if (eingabe.equalsIgnoreCase("TG")) {
            Tagesgeld tagesgeld = new Tagesgeld();

            System.out.println("Es wird ein Tagesgeldkonto für Sie eröffnet:");
            showProgressBar();
            System.out.println("Die Iban lautet: " + tagesgeld.getIban());
            System.out.println("Auf das Tagesgeldkonto erhalten Sie " + tagesgeld.getZinsen() + "% Zinsen p.a.");
            kontoZuKunde(tagesgeld);
        } else if (eingabe.equalsIgnoreCase("GI")) {
            Giro giro = new Giro();

            System.out.println("Es wird ein Girokonto für Sie eröffnet:");
            showProgressBar();
            System.out.println("Die Iban lautet: " + giro.getIban());
            System.out.println("Das tägliche Auszahlungslimit beträgt " + giro.getMaxAuszahlung() + "€.");
            System.out.println("Außerdem dürfen Sie ihr Konto nur " + giro.getMaxDispo() + "€ überziehen.");
            kontoZuKunde(giro);
        } else {
            System.out.println("Kontotyp nicht erkannt. Bitte erneut versuchen.");
        }
    }

    public static void kontoZuKunde(Konto konto){
        ArrayList<Kunde> kunden = bank.getKunden();
        if (kunden.size() > 0) {
            System.out.println("\nZu welchem Kunden möchten Sie dieses Konto hinzufügen?");
            getKundenListe();
            int kundennummer = promiseIntFromConsole();
            if (kundennummer <kunden.size() && kundennummer >= 0) {
                System.out.println("Bitte IBAN eingeben (mit Leerzeichen): ");
                String iban = promiseStringFromConsole();
                Kunde kunde = kunden.get(kundennummer);
                if (kunde != null) {
                    bank.assignKonto(kunde, iban);
                    System.out.println("Konto zu Kunden hinzugefügt: " + iban);
                }else{
                    System.out.println("Konto nicht im System gefunden.");
                }
            }else{
                System.out.println("Bitte korrekte Kundennummer eingeben");
                bank.deleteKonto(konto);
            }
        } else {
            System.out.println("Bitte zuerst Kunden anlegen");
            bank.deleteKonto(konto);
        }}

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

    public static double promiseDoubleFromConsole() {
        Scanner mainScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("↓");
                return mainScanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte eine Zahl eingeben!\033[0m");
                mainScanner.nextLine();
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
                mainScanner.nextLine();
            }
        }
    }

    public static String promiseStringFromConsole() {
        Scanner mainScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("↓");
                return mainScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\033[3mFehler: Bitte einen String eingeben!\033[0m");
                mainScanner.nextLine();
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
            } catch (InputMismatchException | DateTimeParseException e) {
                System.out.println("\033[3mFehler: Bitte ein valides Datum eingeben!\033[0m");
                mainScanner.nextLine();

            }
        }
    }

    public static Bank loadBank() throws JAXBException {
        Unmarshaller unmarshaller = JAXBContext.newInstance(Bank.class).createUnmarshaller();
        return (Bank) unmarshaller.unmarshal(new File("bank.xml"));
    }

    public static void saveBank(Bank bank) throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance(Bank.class).createMarshaller();
        marshaller.marshal(bank, new File("bank.xml"));
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
