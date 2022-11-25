package de.hsw;


import com.sun.istack.Nullable;
import com.sun.source.tree.Tree;

import java.lang.reflect.Array;
import java.util.*;



 //In dieser Klasse Lottozahlen werden Lottozahlen, Zusatzzahl und Superzahl generiert und ausgegeben
public class Lottozahlen {
     public static Random rand = new Random();

    /**
     * @param anzahl Gibt die Anzahl der Lottozahlen ein
     */
    public Set<Integer> lottoscheinZahlen(int anzahl) {

        int[] spielRundenArray = new int[anzahl];
        Set<Integer> lottozahlenArray = null;

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
            zusatzZahlMethode(lottozahlenArray, null);
        }

        return lottozahlenArray;
    }

        public int zusatzZahlMethode(Set<Integer> lottozahlenArray, @Nullable Long seed){
            int zusatzZahl = rand.nextInt(1, 49);
            while (lottozahlenArray.contains(zusatzZahl)) {
                zusatzZahl = rand.nextInt(1, 49);
            }
            lottozahlenArray.add(zusatzZahl);
            //Es wird eine Zusatzzahl generiert und überprüft ob sie schon vorhanden ist
            //Dies wird solange ausgeführt bis eine Zahl gefunden wird die nicht schon vorhanden ist
            System.out.println("Ihre Zusatzzahl: [" + zusatzZahl + "]\n");
            //Ausgabe der Zusatzzahl

            return zusatzZahl;
        }


        public int superZahl(@Nullable Long seed){
            System.out.println("--------------------SUPERZAHL--------------------");
            int superZahl = 0;
            if (seed == null) {
                superZahl = rand.nextInt(1, 9);
                System.out.println("Ihre Superzahl: [" + superZahl + "]");
                //generiert und gibt eine Superzahl aus, die zwischen 1 und 9 liegt
            } else {
                rand.setSeed(seed);
                superZahl = rand.nextInt(1, 9);
                System.out.println("Ihre Superzahl: [" + superZahl + "]");
            }
            return superZahl;
        }



 }








