# WI70/22 Leistungsnachweis

*Grundlagen der Programmierung bei Bastian Grupe.
Eine Arbeit von Tim Borkowy, Tristan Rathgeber und Dat Huynh.*

# Javadoc

Das gesamte Projekt verfügt über eine standardisierte Javadoc. Dieses ist im Projekt [hier](https://github.com/TIMBLOCKER/hsw-gdp-s1-leistungsnachweis/tree/main/javadoc) zu finden. Dafür einfach den gesamten Ordner klonen und die [index.html](https://github.com/TIMBLOCKER/hsw-gdp-s1-leistungsnachweis/blob/main/javadoc/index.html) öffnen.

# Dateien (Teil 1) (50 Punkte)

Hier erfolgt eine Auflistung der verschiedenen Projekte, die über dieses Git-Repo abgerufen werden können. Es handelt sich um die Projekte der Aufgaben 1-6, die unterschiedliche Programmieraufgaben und deren Lösung, sowie eine adäquate Dokumentation beinhalten.

**Folgend sind die Aufgabenstellungen hier noch einmal abgelegt (Quelle: Bastian Grupe - Hochschule Weserbergland - WS 2022):**

## 11-aufgabe1 (10 Punkte)

Erstellen Sie ein Programm, das Ihnen hilft, den Lottoschein auszufüllen. Dazu soll das Programm Ihnen die Lottozahlen (6 aus 49) plus Zusatzzahl, plus Superzahl (zwischen 1 und 9) zufällig ermitteln und ausgeben. Nutzen Sie dazu die Zufallsfunktion java.util.Random.Beachten Sie, dass die Zahlen nicht doppelt auftreten dürfen.

## 12-aufgabe2 (5 Punkte) ("Eigentliche Aufgabe")

Entwickeln Sie ein Programm, das für einen „fully qualfied filename“ die Endung austauscht. Beispiel:C:\Users\Test\Programmierung Teil 1.pdfändern zuC:\Users\Test\Programmierung Teil 1.bak

## 12-aufgabe2

Außerdem haben wir zu diesem Problem noch die Funktionalität hinzugefügt, die einen etwas realistischeren Anwendungskontext beinhaltet. Mit diesem zweiten Paket lassen sich Dateien über die Eingabe einlesen und die Dateiendungen beliebiger Files programmatisch ändern.

## 13-aufgabe3 (10 Punkte)

Checksumme berechnen: Erstellen Sie eine Methode, die eine Checksumme (Da-tentyp long) für einen Eingabestring ausgibt. Bei Konsonanten(aus dem ASCII-Bereich)erhöht sich die Checksumme um 1, bei Vokalen (aus dem ASCII-Bereich) verringert sie sich um 1, bei Ziffern wird sie verdoppelt(alle anderen Zeichen sind nicht erlaubt, außerdem darf der String nicht mit einer Ziffer beginnen).

Geben Sie die Checksumme als Hexadezimalzahl aus.

## 14-aufgabe4 (5 Punkte)

Erstellen Sie ein Programm, indem geprüft wird, ob ein eingegebener String ein Palindrom ist.

## 15-aufgabe5 (10 Punkte)

Schreiben Sie ein Programmfür die Berechnung der Fakultät. Testen Sie diese Me-thode mit Unittests inkl. Fehlerbehandlungaller Randbedingungen einer Fakultät.

## 16-aufgabe6 (10 Punkte)

Recherchieren Sie den Aufbau einer IBAN in Deutschland. Erstellen Sie ein Konso-lenprogramm für die Berechnung einer deutschen IBAN zu einer Bankleitzahl und einer Kon-tonummer. Weisen Sie ungültige Bestandteile mit einem Fehler ab. Geben Sie nach der Berechnung jeweils die einzelnen Bestandteile und zuletzt die IBAN als Ergebnis aus. Formatieren Sie das Ergebnis wie bei einer IBAN üblich in 4er Blöcken.

# Dateien (Teil 2) (50 Punkte)

Der Bankbetrieb soll mindestens die Eigenschaften Bankname, Bankleitzahl(muss dem Format einer gültigen deutschen Bankleitzahl entsprechen), Adresse besitzen.Beim Erstellen des Bankbetriebs müssen diese Eigenschaften sinnvoll initialisiert werden.Der Bankbetrieb hat Kunden(mit typischen Eigenschaften)und Konten(mit typischen Eigen-schaften), die in jeweils sinnvollenCollections gespeichert werden sollen.Es gibt zwei Konten-typen: Girokonto und Tagesgeldkonto.•(10 Punkte) Der Bankbetrieb kann Kunden und Konten erstellenund löschen. Beim Erstellenvon Konten, nutzen Sie das Ergebnis aus Teil 1 Aufgabe 6, um eine zufällige IBAN zu erzeugen. Prüfen Sie, dass die neu generierte IBAN nicht bereits existiert.Ein Kunde darf maximal 2 Gi-rokonten und 3 Tagesgeldkonten besitzen•(20 Punkte) Der Bankbetrieb kann Einzahlungen, Auszahlungen und Umbuchungen durchfüh-ren, dafür gelten folgende Regeln:oMan kann Geld zwischen Girokonto und Tagesgeldkonto umbuchenoEin Girokonto nicht mehr als 1000 € überzogen werden, ein Tagesgeldkonto darf nicht überzogen werdenoEs darf vom Girokonto nicht mehr als 1000 € auf einmal ausgezahlt werden können, vom Tagesgeldkonto darf nichts ausgezahlt werden•(10Punkte) Speichern Sie den Zustand des Bankbetriebs bei Programmende und laden Sie den Zustand bei Programmstart.Implementieren Sie eine Funktion, um den gespeicherten Zustand zu löschen und beachten Sie den Startohne gespeicherten Zustand.
