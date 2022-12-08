package de.hsw;

import de.hsw.jaxbUtils.Bankdaten;
import de.hsw.jaxbUtils.Kontendaten;
import de.hsw.jaxbUtils.Kundendaten;
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
    private static Bankdaten bankdaten;

   // In der Main Methode Startet der User das Programm und gibt die gewünschten Funktionen ein
    public static void main(String[] args) {

        try {
            bankdaten = loadBankdaten();
            bank = bankdatenToBank(bankdaten);
        } catch (JAXBException | IllegalArgumentException e) {
            System.out.println("\033[3mFehler: Die Bank konnte nicht geladen werden!\033[0m"); //Bei einer Exception wird diese Nachricht ausgegeben
            System.out.println(e.getMessage());
            e.printStackTrace();
            bank = new Bank();
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
            switch (promiseIntFromConsole()) { //Switch Case um gewünschte Funktion auszuführen
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
            saveBankdaten(bankToBankdaten(bank));
        } catch (JAXBException e) { //Abfangen einer Exception
            System.out.println("\033[3mFehler: Die Bank konnte nicht gespeichert werden!\033[0m");
            throw new RuntimeException(e);
        }
    }


    /**
     *
     */
    public static void geldTransfer(){ //Methode zur Abfrage für einen Transfer von Geld
        System.out.println("Von welchem Konto soll die Überweisung erfolgen?");
        System.out.println("Bitte IBAN eingeben:"); //Abfrage auf das Konto des Versenders
        String ibanfrom = promiseStringFromConsole();
        System.out.println("Auf welches Konto soll die Überweisung erfolgen?" );
        System.out.println("Bitte IBAN eingeben:"); //Abfrage auf das Konto des Empfängers.
        String ibanto = promiseStringFromConsole();
        System.out.println("Bitte Betrag eingeben:"); //Eingabe des Transferbetrages
        double amount = promiseDoubleFromConsole();
        if (amount > 0){ //If Statement, um zu überprüfen, ob der eingegebene Betrag positiv ist
            bank.transferMoney(ibanfrom, ibanto, amount);
            System.out.println("Es wurden " + amount + "€ von " + ibanfrom + " auf das Konto " + ibanto + " überwiesen.");
            System.out.println("Neues Saldo des Debitors: " + bank.getKontofromIBAN(ibanfrom).getSaldo() + "€"); //Zeigt Saldo des Geldversenders
            System.out.println("Neues Saldo des Creditors: " + bank.getKontofromIBAN(ibanto).getSaldo() + "€"); //Zeigt Saldo des Geldempfängers
        }else{
            System.out.println("\033[3mFehler: Der Eingezahlte Betrag muss positiv sein!\033[0m");
        }
    }



    public static void geldAuszahlen(){ //Methode zur Abfrage zur Auszahlung
        System.out.println("Von welchem Konto möchten Sie Geld auszahlen?");
        System.out.println("Bitte IBAN eingeben:");
        String iban = promiseStringFromConsole();
        System.out.println("Wie viel Geld möchten Sie vom Konto: " + iban + " auszahlen? (ohne Währungszeichen)");
        double amount = promiseDoubleFromConsole();
        if (amount > 0){ //if Statement, damit der eingegebene Betrag nicht negativ ist
            bank.outputMoney(iban, amount);
            geldAuszahlen(amount);
        }else{
            System.out.println("\033[3mFehler: Der Eingezahlte Betrag muss positiv sein!\033[0m");
        }
    }


    public static void geldEinzahlen(){ //Methode zur Abfrage einer Einzahlung
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

    public static void geldAuszahlen(double amount) { //Methode zur Anzeige des ausgezahlten Betrages
        System.out.println("Es werden nun " + amount + "€ ausgezahlt:");
        for (int i = 0; i < amount; i++) {
            String moneyOutput = String.join("", Collections.nCopies(i, "[€] ")); //delimiter splittet den String
            System.out.print(moneyOutput + "\r"); //Anzeige des Betrages
            waitThread(); //waitThread hält den Thread für eine Zeit an
        }

    }

    public static void geldAufloesen(double amount) { //Methode um den verbleibenden Betrag auszuzahlen
        System.out.println("Das verbleibende Saldo beträgt " + amount + "€ und wird nun ausgezahlt:");
        for (int i = 0; i < amount; i++) {
            String moneyOutput = String.join("", Collections.nCopies(i, "[€] "));
            System.out.print(moneyOutput + "\r");
            waitThread(); //waitThread hält den Thread für eine kurze Zeit an
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
                    bank.addKonto(konto);
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

    public static void showProgressBar() { //Methode zur Anzeige des Ladebalkens
        for (int i = 0; i < 101; i++) { //wird mittels for-loop und Collections erzeugt
            int progress = i / 10;
            String progressBarSteps = String.join("", Collections.nCopies(progress, "="));
            String progressBarSpaces = String.join("", Collections.nCopies(10 - progress, " "));
            String progressStart = i + "% [";
            System.out.print(progressStart + progressBarSteps + ">" + progressBarSpaces + "]\r");
            waitThread(); //waitThread hält den Thread für eine kurze Zeit an
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

    public static String promiseStringFromConsole() { //Methode zur eingabe eines Strings über die Konsole
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
                LocalDate localDate = LocalDate.parse(eingabe, formatter);
                return localDate;
            } catch (InputMismatchException | DateTimeParseException e) { //Exception bei einer falschen Eingabe
                System.out.println("\033[3mFehler: Bitte ein valides Datum eingeben!\033[0m");
                mainScanner.nextLine();

            }
        }
    }

    /**
     * Methode, um die Bankdaten zu laden
     * @return Rückgabe der neuen Bankdaten
     * @throws JAXBException
     */
    public static Bank loadBank() throws JAXBException {
        File f = new File("bank.xml");
        if(f.exists() && !f.isDirectory()) {
            Unmarshaller unmarshaller = JAXBContext.newInstance(Bank.class).createUnmarshaller();
            System.out.println("Bank laden...");
            showProgressBar(); //anzeige des Ladebalkens
            System.out.println("Die Bank wurde geladen!");
            return (Bank) unmarshaller.unmarshal(new File("bank.xml"));
        }
        System.out.println("\033[3mFehler: Die Bank konnte nicht geladen werden!\033[0m");
            return new Bank();
    }

    /**
     * @param bank Bank
     * Bank wird in Bankdaten umgewandelt für XML
     * @return Rückgabe der Bankdaten
     */
    public static Bankdaten bankToBankdaten(Bank bank){
        ArrayList<Kundendaten> kundendatenArrayList = new ArrayList<>();
        int i = 0;
        for (Kunde k: bank.getKunden()) {
            ArrayList<Kontendaten> kontendatenArrayList = new ArrayList<>();
            for (Konto konto:k.getKonten()) {
               if (konto instanceof Tagesgeld){
                    kontendatenArrayList.add(new Kontendaten(konto.getIban(), konto.getSaldo(), konto.getMaxDispo(), "TG"));
               }else{
                   kontendatenArrayList.add(new Kontendaten(konto.getIban(), konto.getSaldo(), konto.getMaxDispo(), "GI"));
               }
            }
            Kundendaten kundendaten = new Kundendaten(i, k.getName(), k.getVorname(), k.getAdresse(), k.getGeburtsdatum(), kontendatenArrayList);
            kundendatenArrayList.add(kundendaten);
            i++;
        }
        Bankdaten bankdaten = new Bankdaten(bank.getName(), bank.getAdresse(), bank.getBlz(), kundendatenArrayList);
        return bankdaten;
    }

    /**
     * @param bankdaten Daten der Bank
     * Bankdaten wird zur Bank umgewandelt für die XML
     * @return Rückgabe der Bankdaten (Name, Adresse,Blz, Konten, Kunden)
     */
    public static Bank bankdatenToBank(Bankdaten bankdaten){
        HashMap<String, Konto> konten = new HashMap<>(); //HashMap ist zur speicherung der Daten in einer Datentabelle
        ArrayList<Kunde> kunden = new ArrayList<>();
        ArrayList<Kundendaten> kundendatenArrayList = bankdaten.getKunden();
        for (Kundendaten k: kundendatenArrayList) {
            ArrayList<Kontendaten> kontendatenArrayList = k.getKonten();
            ArrayList<Konto> kontList = new ArrayList<>();
            for (Kontendaten kd: kontendatenArrayList) {
                if (kd.getType().equals("TG")){
                    Tagesgeld tg = new Tagesgeld(kd.getIban(), kd.getSaldo());
                    konten.put(kd.getIban(), tg);
                    kontList.add(tg);
                }else{
                    Giro gi = new Giro(kd.getIban(), kd.getSaldo());
                    konten.put(kd.getIban(), gi);
                    kontList.add(gi);
                }
            }
            Kunde kunde = new Kunde(k.getName(), k.getVorname(), k.getAdresse(), k.getGeburtsdatum(), kontList);
            kunden.add(kunde);
        }
        return new Bank(bankdaten.getName(), bankdaten.getAdresse(), bankdaten.getBlz(), konten, kunden);
    }

    /**
     * @return Rückrabe der Bankdaten an Klasse Bankdaten
     * Methode um zu prüfen ob Bankdaten vorhanden sind, um diese anzuzeigen
     * @throws JAXBException
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
