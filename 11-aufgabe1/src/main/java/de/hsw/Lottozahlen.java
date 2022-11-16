package de.hsw;


import com.sun.source.tree.Tree;

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

    public void tristanderprofi(){
            Random random = new Random();

            int arr[] = {12, 13 ,25, 24, 30, 14};
            int zzz = 0;
            boolean lol = false;
            while (lol == false){
                boolean lal = false;
                int zz = random.nextInt(1,49);
                for (int i = 0; i < 6; i++) {
                    if (arr[i] == zz){
                        lal = true;
                        break;
                    }
                }
                if (lal == false){
                    lol = true;
                    zzz = zz;
                }
            }
            System.out.println(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] + " " + arr[4] + " " + arr[5]);
            System.out.println("Deine ZZ ist: " + zzz);
    }
}



