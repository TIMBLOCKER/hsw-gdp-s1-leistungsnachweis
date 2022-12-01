package de.hsw;


import com.sun.istack.Nullable;
import com.sun.source.tree.Tree;

import java.lang.reflect.Array;
import java.util.*;

//TODO: JavaDoc nachziehen und Formatierung anpassen
//Todo camelCase

//In dieser Klasse Lottozahlen werden Lottozahlen, Zusatzzahl und Superzahl generiert und ausgegeben
public class Lottozahlen {

    Random rand;

    public Lottozahlen(long seed) {
        rand = new Random(seed);
    }

    public Lottozahlen() {
        rand = new Random();

    }

    /**
     * @param anzahl Gibt die Anzahl der Lottozahlen ein
     */
    public Set<Integer> lottoscheinZahlen(Set<Integer> set, int anzahl) {

        int[] spielRundenArray = new int[anzahl];
        Set<Integer> lottoZahlenArray = null;

        for (int i = 0; i < spielRundenArray.length; i++) {

            System.out.println("DAS SIND DIE ZAHLEN FÜR DIE SPIELRUNDE " + (i + 1) + ":");
            //aktuelle Anzahl der Spielrunde wird ausgeben
            lottoZahlenArray = new TreeSet<>();
            //sortiert lottoZahlenArray


            while (lottoZahlenArray.size() < 6) {
                lottoZahlenArray.add(rand.nextInt(1, 49));
                //generiert 6 zufällige Lottozahlen zwischen 1 bis 49

            }
            System.out.println(lottoZahlenArray.toString());
            //gibt die Lottozahlen aus
            zusatzZahlMethode(lottoZahlenArray, null);
        }

        return lottoZahlenArray;
    }

    public int zusatzZahlMethode(Set<Integer> lottozahlenArray, @Nullable Long seed) {
        int zusatzZahl = rand.nextInt(1, 49);
        while (lottozahlenArray.contains(zusatzZahl)) {
            zusatzZahl = rand.nextInt(1, 49);
        }
        lottozahlenArray.add(zusatzZahl);
        //Es wird eine Zusatzzahl generiert und überprüft, ob sie schon vorhanden ist
        //Dies wird solange ausgeführt bis eine Zahl gefunden wird die nicht schon vorhanden ist
        System.out.println("Ihre Zusatzzahl: [" + zusatzZahl + "]\n");
        //Ausgabe der Zusatzzahl

        return zusatzZahl;
    }


    public int superZahl() {
        System.out.println("--------------------SUPERZAHL--------------------");
        int superZahl = 0;
        superZahl = rand.nextInt(1, 9);
        System.out.println("Ihre Superzahl: [" + superZahl + "]");
        return superZahl;
    }
}








