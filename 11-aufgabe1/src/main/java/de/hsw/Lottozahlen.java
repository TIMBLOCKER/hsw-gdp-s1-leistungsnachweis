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



                    while (lottozahlenArray.size() < 6){
                    lottozahlenArray.add(rand.nextInt(50));
                }

                System.out.println(lottozahlenArray.toString());
             }


    }
    public void lottoscheinSuperzahl(){

            int superZahl = rand.nextInt(9);
            System.out.println("Ihre Superzahl: " + superZahl);
        int zusatzzahl = rand.nextInt(9);
        System.out.println("Ihre Zusatzzahl: ");
        // zusatzzahl --> siebte Zahl aus 49
    }
}




