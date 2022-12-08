package de.hsw;

import de.hsw.jaxbUtils.Bankdaten;
import de.hsw.jaxbUtils.ConvertBank;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Starter  {

    private static Bank bank;

    /**
     * In der Main-Methode Startet der User das Programm und gibt die gewünschten Funktionen ein
     */
    public static void main(String[] args) {
        ConvertBank convertBank = new ConvertBank();
        boolean end = false;

        try {
            Bankdaten bankdaten = loadBankdaten();
            bank = convertBank.bankdatenToBank(bankdaten);
        } catch (JAXBException | IllegalArgumentException e) {
            System.out.println("\033[3mFehler: Die Bank konnte nicht geladen werden!\033[0m"); //Bei einer Exception wird diese Nachricht ausgegeben
            System.out.println(e.getMessage());
            e.printStackTrace();
            bank = new Bank();
        }

        System.out.println("Willkommen bei der");
        System.out.println("""
                 /$$   /$$  /$$$$$$  /$$      /$$       /$$$$$$$                      /$$     \s
                | $$  | $$ /$$__  $$| $$  /$ | $$      | $$__  $$                    | $$     \s
                | $$  | $$| $$  \\__/| $$ /$$$| $$      | $$  \\ $$  /$$$$$$  /$$$$$$$ | $$   /$$
                | $$$$$$$$|  $$$$$$ | $$/$$ $$ $$      | $$$$$$$  |____  $$| $$__  $$| $$  /$$/
                | $$__  $$ \\____  $$| $$$$_  $$$$      | $$__  $$  /$$$$$$$| $$  \\ $$| $$$$$$/\s
                | $$  | $$ /$$  \\ $$| $$$/ \\  $$$      | $$  \\ $$ /$$__  $$| $$  | $$| $$_  $$\s
                | $$  | $$|  $$$$$$/| $$/   \\  $$      | $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$
                |__/  |__/ \\______/ |__/     \\__/      |_______/  \\_______/|__/  |__/|__/  \\__/
                """);
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
            try {
                switch (promiseIntFromConsole()) { //Switch Case um gewünschte Funktion auszuführen
                    case 1 -> addKundeToBank();
                    case 2 -> kontoEroeffnen();
                    case 3 -> kontoAufloesen();
                    case 4 -> kundeLoeschen();
                    case 5 -> geldEinzahlen();
                    case 6 -> geldAuszahlen();
                    case 7 -> geldTransfer();
                    case 8 -> end = true;
                    default -> System.out.println("\033[3mFehler: Bitte eine Zahl aus dem Menü eingeben!\033[0m");
                }
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

        }
        System.out.println("Auf Wiedersehen!");
        try {
            saveBankdaten(convertBank.bankToBankdaten(bank));
        } catch (JAXBException e) { //Abfangen einer Exception
            System.out.println("\033[3mFehler: Die Bank konnte nicht gespeichert werden!\033[0m");
            throw new RuntimeException(e);
        }
    }

    /**
     * Methode zur Abfrage für einen Transfer von Geld
     * TODO
     */
    public static void geldTransfer(){ //Methode zur Abfrage für einen Transfer von Geld
        if (bank.getKontenSize()>= 2) {
            System.out.println("Von welchem Konto soll die Überweisung erfolgen?");
            System.out.println("Bitte IBAN eingeben:"); //Abfrage auf das Konto des Versenders
            String ibanfrom = promiseStringFromConsole();
            System.out.println("Auf welches Konto soll die Überweisung erfolgen?");
            System.out.println("Bitte IBAN eingeben:"); //Abfrage auf das Konto des Empfängers.
            String ibanto = promiseStringFromConsole();
            System.out.println("Bitte Betrag eingeben:"); //Eingabe des Transferbetrages
            double amount = promiseDoubleFromConsole();
            if (amount > 0) { //If Statement, um zu überprüfen, ob der eingegebene Betrag positiv ist
                try {
                    if (bank.transferMoney(ibanfrom, ibanto, amount)) {
                        System.out.println("Es wurden " + amount + "€ von " + ibanfrom + " auf das Konto " + ibanto + " überwiesen.");
                        System.out.println("Neues Saldo des Debitors: " + bank.getKontofromIBAN(ibanfrom).getSaldo() + "€"); //Zeigt Saldo des Geldversenders
                        System.out.println("Neues Saldo des Creditors: " + bank.getKontofromIBAN(ibanto).getSaldo() + "€"); //Zeigt Saldo des Geldempfängers
                    }
                }catch (IndexOutOfBoundsException e){
                    System.out.println("\033[3mFehler: Das Debitor- oder Creditorkonto konnte nicht gefunden werden!\033[0m");
                }
            } else {
                System.out.println("\033[3mFehler: Der Eingezahlte Betrag muss positiv sein!\033[0m");
            }
        }else{
            System.out.println("\033[3mFehler: Für eine Überweisung müssen min. 2 Konten existieren!\033[0m");
        }
    }

    /**
     * Methode zur Abfrage einer Auszahlung
     */
    public static void geldAuszahlen(){ //Methode zur Abfrage zur Auszahlung
        if (bank.getKontenSize()>0) {
            System.out.println("Von welchem Konto möchten Sie Geld auszahlen?");
            System.out.println("Bitte IBAN eingeben:");
            String iban = promiseStringFromConsole();
            System.out.println("Wie viel Geld möchten Sie vom Konto: " + iban + " auszahlen? (ohne Währungszeichen)");
            double amount = promiseDoubleFromConsole();
            if (amount > 0) { //if Statement, damit der eingegebene Betrag nicht negativ ist
                    if (bank.outputMoney(iban, amount)) {
                        geldAuszahlen(amount);
                    }else {
                        System.out.println("Das Konto konnte nicht gefunden werden oder der Saldo war nicht ausreichend");
                    }
            } else {
                System.out.println("\033[3mFehler: Der Eingezahlte Betrag muss positiv sein!\033[0m");
            }
        }else{
            System.out.println("\033[3mFehler: Es muss ein Konto zum Auszahlen existieren!\033[0m");
        }
    }

    /**
     * Methode zur Abfrage einer Einzahlung
     */
    public static void geldEinzahlen(){
        if (bank.getKontenSize()>0) {
            System.out.println("Auf welches Konto möchten Sie Geld einzahlen?");
            System.out.println("Bitte IBAN eingeben:");
            String iban = promiseStringFromConsole();
            System.out.println("Wie viel Geld soll auf das Konto: " + iban + " eingezahlt werden?");
            double amount = promiseDoubleFromConsole();
            if (amount > 0) {
                try {
                    if(bank.addMoney(iban, amount)){
                        System.out.println("Es wurden " + amount + "€ eingezahlt.");
                    }else{
                        System.out.println("\033[3mFehler: Das Konto geld konnte nicht eingezahlt werden!\033[0m");
                    }
                } catch (NullPointerException e) {
                    System.out.println("\033[3mFehler: Das Konto wurde nicht gefunden!\033[0m");
                }
            } else {
                System.out.println("\033[3mFehler: Der Eingezahlte Betrag muss positiv sein!\033[0m");
            }
        }else{
            System.out.println("\033[3mFehler: Es muss ein Konto zum Einzahlen vorhanden sein!\033[0m");
        }
    }

    /**
     * Methode zum Löschen eines Kunden
     */
    public static void kundeLoeschen(){
        if (bank.getKunden().size()>0) {
            System.out.println("Welcher Kunde soll gelöscht werden?");
            getKundenListe();
            System.out.println("Bitte Kundennummer eingeben:");
            int position = promiseIntFromConsole();
            try {
                Kunde kunde = bank.deleteKundeAtPosition(position);
                System.out.println("Der Kunde: " + kunde.getName() + ", " + kunde.getVorname() + " wurde gelöscht...");
            }catch (IndexOutOfBoundsException e){
                System.out.println("\033[3mFehler: Diesee Kundennummer ist nicht im System!\033[0m");
            }
        }else{
            System.out.println("\033[3mFehler: Kein Kunde vorhanden!\033[0m");
        }
    }

    /**
     * Methode zum Auflösens eines Kontos
     */
    public static void kontoAufloesen() {
        if (bank.getKontenSize()>0) {
            System.out.println("Welches Konto möchten Sie auflösen?");
            getKontenListe();
            System.out.println("\nBitte IBAN eingeben:");
            String iban = promiseStringFromConsole();
            try {
                Konto konto = bank.unAssignKonto(bank.getKundefromIBAN(iban), iban);
                geldAufloesen(konto.getSaldo());
            }catch (IndexOutOfBoundsException e){
                System.out.println("\033[3mFehler: Das Konto konnte nicht gefunden werden!\033[0m");
            }
        }else{
            System.out.println("\033[3mFehler: Kein Konto vorhanden!\033[0m");
        }
    }

    /**
     * @param amount Saldo des Kontos
     * Methode zur Anzeige des ausgezahlten Betrages
     */
    public static void geldAuszahlen(double amount) {
        System.out.println("Es werden nun " + amount + "€ ausgezahlt:");
        for (int i = 0; i < amount; i++) {
            String moneyOutput = String.join("", Collections.nCopies(i, "[€] ")); //delimiter splittet den String
            System.out.print(moneyOutput + "\r"); //Anzeige des Betrages
            waitThread(); //waitThread hält den Thread für eine Zeit an
        }

    }

    /**
     * @param amount Saldo des Kontos
     * Methode zur Auszahlung des verbleibenden Betrages bei einer Kontoauflösung
     */
    public static void geldAufloesen(double amount) {
        if (amount >= 1) {
            System.out.println("Das verbleibende Saldo beträgt " + amount + "€ und wird nun ausgezahlt:");
            for (int i = 0; i < amount; i++) {
                String moneyOutput = String.join("", Collections.nCopies(i, "[€] "));
                System.out.print(moneyOutput + "\r");
                waitThread(); //waitThread hält den Thread für eine kurze Zeit an
            }
        }else{
            System.out.println("Das Konto war leer und wurde aufgelöst!");
        }
    }

    /**
     * Methode mittels Get zum Anzeigen der Konten in einer ArrayList
     */
    public static void getKontenListe() {
        ArrayList<Kunde> kunden = bank.getKunden();
        for (Kunde kunde : kunden) {
            for (Konto k : kunde.getKonten()) {
                System.out.println(k.getType() + " | " + kunde.getName() + ", " + kunde.getVorname() + " | " + k.getIban() + " | Saldo: " + k.getSaldo());
            }
        }
    }

    /**
     * Methode mittels Get zum Anzeigen der Konten in einer ArrayList
     */
    public static void getKundenListe() {
        ArrayList<Kunde> kunden = bank.getKunden();
        for (Kunde kunde : kunden) {
            System.out.println("[" + kunden.indexOf(kunde) + ".] " + kunde.getName() + ", " + kunde.getVorname() + " → " + kunde.getAdresse());
        }
    }

    /**
     * Methode zur Abfrage zum Anlegen eines Kunden
     */
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

    /**
     * Methode zur Abfrage und Anzeige zur Erstellung der Konten
     */
    public static void kontoEroeffnen() {
        if (bank.getKunden().size()>0) {
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
        }else{
            System.out.println("\033[3mFehler: Es muss ein Kunde zum Eröffnen eines Kontos existieren!\033[0m");
        }
    }

    /**
     * @param konto Konto
     * Methode zur zuordnung eines Kontos zu einem Kunden
     */
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
                    if (iban.equals(konto.getIban())){
                        if (kunde != null) {
                            bank.addKonto(konto);
                            if(bank.assignKonto(kunde, iban)) {
                                System.out.println("Konto zu Kunden hinzugefügt: " + iban);
                            } }else{
                            System.out.println("Fehler: Konto nicht im System gefunden.");
                        }
                    }else {
                        throw new IllegalArgumentException("Bitte passende Iban eingeben!");
                    }
            }else{
                System.out.println("Bitte korrekte Kundennummer eingeben");
                bank.deleteKonto(konto);
            }
        } else {
            System.out.println("Bitte zuerst Kunden anlegen");
            bank.deleteKonto(konto);
        }}

    /**
     * Methode zur Anzeige des Ladebalkens
     */
    public static void showProgressBar() {
        for (int i = 0; i < 101; i++) { //wird mittels for-loop und Collections erzeugt
            int progress = i / 10;
            String progressBarSteps = String.join("", Collections.nCopies(progress, "="));
            String progressBarSpaces = String.join("", Collections.nCopies(10 - progress, " "));
            String progressStart = i + "% [";
            System.out.print(progressStart + progressBarSteps + ">" + progressBarSpaces + "]\r");
            waitThread(); //waitThread hält den Thread für eine kurze Zeit an
        }
    }

    /**
     * Methode zur generierung eines Scanners --> Eingabe soll Double sein
     * @return Rückgabe eines weiteren Scanners
     */
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

    /**
     * Methode zur generierung eines Scanners --> Eingabe soll Int sein
     * @return Rückgabe eines weiteren Scanners
     */
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

    /**
     * Methode zur generierung eines Scanners --> Eingabe soll ein String sein
     * @return Rückgabe eines weiteren Scanners
     */
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

    /**
     * Methode zur Eingabe eines Datums in verbindung mit LocalDate
     * @return Rückgabe des LocalDates
     */
    public static LocalDate promiseLocalDateFromConsole() {
        Scanner mainScanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("[Format: dd.mm.yyyy]↓");
                String eingabe = mainScanner.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.GERMANY);
                return LocalDate.parse(eingabe, formatter);
            } catch (InputMismatchException | DateTimeParseException e) { //Exception bei einer falschen Eingabe
                System.out.println("\033[3mFehler: Bitte ein valides Datum eingeben!\033[0m");
                mainScanner.nextLine();

            }
        }
    }

    /**
     * @return Rückrabe der Bankdaten an Klasse Bankdaten
     * Methode um zu prüfen ob Bankdaten vorhanden sind, um diese anzuzeigen
     * @throws JAXBException Sollten diese Daten nicht vorhanden sein,
     */
    public static Bankdaten loadBankdaten() throws JAXBException {
        File f = new File("bankdaten.xml");
        if(f.exists() && !f.isDirectory()) {
            Unmarshaller unmarshaller = JAXBContext.newInstance(Bankdaten.class).createUnmarshaller();
            System.out.println("Bank laden...");
            showProgressBar();
            System.out.println("Die Bank wurde geladen!");
            return (Bankdaten) unmarshaller.unmarshal(f);
        }
        System.out.println("\033[3mFehler: Die Bank konnte nicht geladen werden!\033[0m");
        throw new JAXBException("Fehler: Die Bank konnte nicht geladen werden! (Keine Datei gefunden)"); //Exception die geworfen wird, wenn keine Bankdaten vorhanden sind
    }

    /**
     * @param bankdaten Bankdaten --> Konto, Kundenattribute etc..
     * Methode um Bankdaten in der XML Datei einzupflegen
     * @throws JAXBException Exception, falls keine Daten vorhanden sind --> Daten konnten nicht gefunden werden
     */
    public static void saveBankdaten(Bankdaten bankdaten) throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance(Bankdaten.class).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(bankdaten, new File("bankdaten.xml"));
    }

    /**
     * Methode um den Thread für eine bestimmte Zeit anzuhalten (Millisekunden)
     */
    public static void waitThread() {
        Random random = new Random();
        try {
            Thread.sleep(random.nextLong(10L, 100L));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
