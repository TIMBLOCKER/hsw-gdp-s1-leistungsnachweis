classDiagram
direction BT
class Bank {
  + Bank(String, String, String) 
  + Bank(String, String, String, HashMap~String, Konto~, ArrayList~Kunde~) 
  + Bank() 
  ~ ArrayList~Kunde~ kunden
  - String ADRESSE
  ~ String adresse
  ~ String blz
  + String BANKLEITZAHL
  ~ String name
  - String NAME
  ~ HashMap~String, Konto~ konten
  + outputMoney(String, double) boolean
  + addKonto(Konto) Konto
  + deleteKontofromIBAN(String) Konto
  + getName() String
  + addKunde(String, String, String, LocalDate) Kunde
  + deleteKundeAtPosition(int) Kunde
  + deleteKonto(Konto) boolean
  + getBlz() String
  + getKontofromIBAN(String) Konto
  + unAssignKonto(Kunde, String) Konto
  + toString() String
  + deleteKunde(Kunde) boolean
  + getAdresse() String
  + assignKonto(Kunde, String) boolean
  + transferMoney(String, String, double) boolean
  + getKundefromIBAN(String) Kunde
  + addMoney(String, double) boolean
  + getKunden() ArrayList~Kunde~
}
class Bankdaten {
  + Bankdaten(String, String, String, ArrayList~Kundendaten~) 
  + Bankdaten() 
  ~ String name
  ~ String adresse
  ~ String blz
  ~ ArrayList~Kundendaten~ kunden
  + getName() String
  + setAdresse(String) void
  + getKunden() ArrayList~Kundendaten~
  + getBlz() String
  + getAdresse() String
  + setKunden(ArrayList~Kundendaten~) void
  + setBlz(String) void
  + setName(String) void
}
class ConvertBank {
  + ConvertBank() 
  + bankToBankdaten(Bank) Bankdaten
  + bankdatenToBank(Bankdaten) Bank
}
class Giro {
  + Giro(String, double) 
  + Giro() 
  ~ double maxDispo
  ~ double maxAuszahlung
  + getType() String
  + toString() String
  + getMaxAuszahlung() double
  + getMaxDispo() double
}
class Kontendaten {
  + Kontendaten(String, double, double, String) 
  + Kontendaten() 
  ~ String iban
  - String type
  - double saldo
  - double maxDispo
  + getIban() String
  + setType(String) void
  + getSaldo() double
  + setSaldo(double) void
  + setMaxDispo(double) void
  + getMaxDispo() double
  + setIban(String) void
  + getType() String
}
class Konto {
  + Konto() 
  + Konto(String, double) 
  + Konto(String) 
  - double saldo
  - double maxDispo
  ~ String iban
  + generateIBANDE(String) String
  + setSaldo(double) void
  + assembleIban(String, String, String) String
  + getMaxDispo() double
  + generateChecksum(String, String) long
  + getIban() String
  + toString() String
  + getSaldo() double
  + getType() String
}
class Kunde {
  + Kunde(String, String, String, LocalDate) 
  + Kunde(String, String, String, LocalDate, ArrayList~Konto~) 
  + Kunde() 
  ~ LocalDate geburtsdatum
  ~ int tgeld
  ~ String adresse
  ~ int giro
  ~ String name
  ~ String vorname
  ~ ArrayList~Konto~ konten
  + getAdresse() String
  + toString() String
  + getName() String
  + updateIndex() void
  + deleteKonto(Konto) Konto
  + getVorname() String
  + addKonto(Konto) boolean
  + getKonten() ArrayList~Konto~
  + getStringValue() String
  + getGeburtsdatum() LocalDate
}
class Kundendaten {
  + Kundendaten() 
  + Kundendaten(int, String, String, String, LocalDate, ArrayList~Kontendaten~) 
  ~ LocalDate geburtsdatum
  ~ String name
  ~ int kundennummer
  ~ String vorname
  ~ String adresse
  ~ ArrayList~Kontendaten~ konten
  + setKonten(ArrayList~Kontendaten~) void
  + setVorname(String) void
  + setAdresse(String) void
  + setGeburtsdatum(LocalDate) void
  + setName(String) void
  + getKonten() ArrayList~Kontendaten~
  + getName() String
  + setKundennummer(int) void
  + getGeburtsdatum() LocalDate
  + getAdresse() String
  + getKundennummer() int
  + getVorname() String
}
class LocalDateAdapter {
  + LocalDateAdapter() 
  + marshal(LocalDate) String
  + unmarshal(String) LocalDate
}
class Starter {
  + Starter() 
  - ConvertBank convertBank
  - Bank bank
  - Bankdaten bankdaten
  + geldTransfer() void
  + geldAuszahlen() void
  + promiseLocalDateFromConsole() LocalDate
  + getKundenListe() void
  + showProgressBar() void
  + saveBankdaten(Bankdaten) void
  + waitThread() void
  + promiseIntFromConsole() int
  + geldAufloesen(double) void
  + kontoAufloesen() void
  + main(String[]) void
  + kontoZuKunde(Konto) void
  + kontoEroeffnen() void
  + promiseStringFromConsole() String
  + promiseDoubleFromConsole() double
  + geldAuszahlen(double) void
  + loadBankdaten() Bankdaten
  + kundeLoeschen() void
  + geldEinzahlen() void
  + addKundeToBank() void
  + getKontenListe() void
}
class Tagesgeld {
  + Tagesgeld(String, double) 
  + Tagesgeld() 
  ~ double zinsen
  + getType() String
  + toString() String
  + getZinsen() double
}

Bank  -->  ConvertBank 
Bank  -->  Konto 
Bank  -->  Kunde 
Bankdaten  -->  Kundendaten 
ConvertBank  -->  Bankdaten 
Giro  -->  Konto 
Konto  -->  Kunde 
Kundendaten  -->  Kontendaten 
LocalDateAdapter  -->  Bankdaten 
Starter  -->  Bank 
Tagesgeld  -->  Konto 
