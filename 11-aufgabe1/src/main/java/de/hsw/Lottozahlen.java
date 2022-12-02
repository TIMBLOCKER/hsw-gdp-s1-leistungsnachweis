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
            System.out.println(lottozahlenArray.toString());
            //gibt die Lottozahlen aus
            zusatzZahlMethode(lottozahlenArray);
        }

        return lottozahlenArray; //gibt das LottozahlenArray zurück
    }

     /**
      * Diese Methode generiert die Zusatzzahl, also die 7. Zahl
      * @param lottozahlenArray 6 zufalls generierte Zahlen in einem Array
      * @return gibt die Zusatzzahl nach der Ausführung zurück
      */
        public int zusatzZahlMethode(Set<Integer> lottozahlenArray ){
            int zusatzZahl = rand.nextInt(1, 49);
            while (lottozahlenArray.contains(zusatzZahl)) {
                zusatzZahl = rand.nextInt(1, 49);
            }
            lottozahlenArray.add(zusatzZahl);
            //Es wird eine Zusatzzahl generiert und überprüft, ob sie schon vorhanden ist
            //es wird so lange ausgeführt, bis eine Zahl gefunden wird die nicht schon vorhanden ist
            System.out.println("Ihre Zusatzzahl: [" + zusatzZahl + "]\n");
            //Ausgabe der Zusatzzahl

            return zusatzZahl;
        }


     /**
      *Diese Methode generiert die Superzahl, die zwischen 1 und 9 liegt
      *  @return gibt die Superzahl nach der Ausführung zurück
      */
        public int superZahl(){
            System.out.println("--------------------SUPERZAHL--------------------");
            int superZahl = 0;
                superZahl = rand.nextInt(1, 9);
                System.out.println("Ihre Superzahl: [" + superZahl + "]");
                //generiert und gibt eine Superzahl aus, die zwischen 1 und 9 liegt
            return superZahl;
        }



 }








