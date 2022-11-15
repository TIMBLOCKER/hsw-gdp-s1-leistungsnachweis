package de.hsw;


import com.sun.source.tree.Tree;

import java.util.*;


public class Lottozahlen {
    Random rand = new Random();

    public void lottoscheinZahlen(int anzahl) {


            int[] spielRundenArray = new int[anzahl];
            for (int i = 0; i < spielRundenArray.length; i++) {

                System.out.println("Das sind die Zahlen für Spielrunde " + (i+1) +":");
                Set<Integer> lottozahlenArray = new TreeSet<>();
    //TO Zahlen nicht wiederholen innerhalb eines Feldes



            while (lottozahlenArray.size() < 6) {
                lottozahlenArray.add(rand.nextInt(1,49));

            }
            System.out.println(lottozahlenArray.toString());
            System.out.println("Ihre Zusatzzahl: [" + "PLATZHALTER" + "]");

            System.out.println(" ");
        }
        System.out.println("----------SUPERZAHL----------");
        int superZahl = rand.nextInt(1,9);
        System.out.println("Ihre Superzahl: " + superZahl);



    }
}



