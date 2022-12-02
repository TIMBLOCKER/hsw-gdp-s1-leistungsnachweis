package de.hsw;

import java.util.*;



 //In der Klasse Lottozahlen werden Lottozahlen, Zusatzzahl und Superzahl generiert und ausgegeben
public class Lottozahlen {
     Random rand;

     public Lottozahlen(){ //Konstruktor für die Random Zahlen
         rand = new Random();
     }

     public Lottozahlen(long seed){ //Konstruktor für den Random Seed
         rand = new Random(seed);
     }

     /**
      * Handling der Konsoleneingabe und Eingabefilter
      * @return String der in der Konsole eingegeben wurde.
      */
     public int eingabe()throws InputMismatchException {
         Scanner mainScanner = new Scanner(System.in);
         while (true) {
             try {
                 int eingabe = mainScanner.nextInt();
                 if (eingabe < 13 && eingabe > 0){
                     System.out.println("↓");
                     return eingabe;
                 }
                 System.out.println("\n\033[41m\033[30mFehler: Bitte eine positive ganzzahlige Zahl eingeben die zwischen 1 und 12 liegt!\033[0m");
             } catch (InputMismatchException e) {
                 System.out.println("\n\033[41m\033[30mFehler: Bitte eine positive ganzzahlige Zahl eingeben!\033[0m");
                 mainScanner.nextLine();
             }
         }
     }

     /**
      * Diese Methode generiert 6 zufalls generierte Zahlen (von 1 bis 49),
     * @param anzahl sind die Spielrunden/ Spielfelder ( 12< und 0>)
     */
    public Set<Integer> lottoscheinZahlen(int anzahl) {

        int[] spielRundenArray = new int[anzahl]; //Spielfeld Anzahl als Array abgespeichert
        Set<Integer> lottozahlenArray = null; // Set Integers = Sets, die nur Integers beinhalten können --> null

        for (int i = 0; i < spielRundenArray.length; i++) {

            System.out.println("DAS SIND DIE ZAHLEN FÜR DIE SPIELRUNDE " + (i + 1) + ":");
            //aktuelle Anzahl der Spielrunde wird ausgeben
            lottozahlenArray = new TreeSet<>();
            //sortiert lottozahlenArray


            while (lottozahlenArray.size() < 6) {
                lottozahlenArray.add(rand.nextInt(1, 49));
                //generiert 6 zufällige Lottozahlen zwischen 1 bis 49

            }
            System.out.println(lottozahlenArray);
            //gibt die Lottozahlen aus
            zusatzZahlMethode(lottozahlenArray);
        }

        return lottozahlenArray; //gibt das LottozahlenArray zurück
    }

     /**
      * Diese Methode generiert die Zusatzzahl, also die 7. Zahl
      * @param lottozahlenArray 6 zufalls generierte Zahlen in einem Array
      */
        public void zusatzZahlMethode(Set<Integer> lottozahlenArray ){
            int zusatzZahl = rand.nextInt(1, 49);
            while (lottozahlenArray.contains(zusatzZahl)) {
                zusatzZahl = rand.nextInt(1, 49);
            }
            lottozahlenArray.add(zusatzZahl);
            //Es wird eine Zusatzzahl generiert und überprüft, ob sie schon vorhanden ist
            //es wird so lange ausgeführt, bis eine Zahl gefunden wird die nicht schon vorhanden ist
            System.out.println("Ihre Zusatzzahl: [" + zusatzZahl + "]\n");
            //Ausgabe der Zusatzzahl
        }


     /**
      *Diese Methode generiert die Superzahl, die zwischen 1 und 9 liegt
      *  @return gibt die Superzahl nach der Ausführung zurück
      */
        public int superZahl(){
            System.out.println("\n--------------------SUPERZAHL--------------------");
            int superZahl;
                superZahl = rand.nextInt(1, 9);
                System.out.println("Ihre Superzahl: [" + superZahl + "]");
                //generiert und gibt eine Superzahl aus, die zwischen 1 und 9 liegt
            return superZahl;
        }



 }








