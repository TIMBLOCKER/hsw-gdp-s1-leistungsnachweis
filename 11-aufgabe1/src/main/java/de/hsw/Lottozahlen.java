package de.hsw;


import com.sun.source.tree.Tree;

import java.lang.reflect.Array;
import java.util.*;


public class Lottozahlen {
    Random rand = new Random();

    public void lottoscheinZahlen(int anzahl) {


        int[] spielRundenArray = new int[anzahl];
        Set<Integer> lottozahlenArray = null;

        for (int i = 0; i < spielRundenArray.length; i++) {

            System.out.println("DAS SIND DIE ZAHLEN FÜR DIE SPIELRUNDE " + (i + 1) + ":");
            lottozahlenArray = new TreeSet<>();


            while (lottozahlenArray.size() < 6) {
                lottozahlenArray.add(rand.nextInt(1, 49));

            }
            System.out.println(lottozahlenArray.toString());

            int zusatzZahl = rand.nextInt(1,49);
                while(lottozahlenArray.contains(zusatzZahl)){
                    zusatzZahl = rand.nextInt(1,49);
                }
                lottozahlenArray.add(zusatzZahl);

            System.out.println("Ihre Zusatzzahl: [" + zusatzZahl + "]");

            System.out.println(" ");
            }


        System.out.println("--------------------SUPERZAHL--------------------");
        int superZahl = rand.nextInt(1, 9);
        System.out.println("Ihre Superzahl: [" + superZahl + "]");


        }



    }





