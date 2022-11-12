package de.hsw;


import java.util.Random;




public class Lottozahlen {
    Random rand = new Random();

    public void lottoscheinZahlen(int anzahl) {


            int[] spielRundenArray = new int[anzahl];
            for (int i = 0; i < spielRundenArray.length; i++) {

                System.out.println("Das sind die Zahlen für Spielrunde " + (i+1) +":");
                int[] lottozahlenArray = new int[6];


                for (int n = 0; n < lottozahlenArray.length; n++) {
                    lottozahlenArray[n] = rand.nextInt(50);
                    System.out.println("[" + lottozahlenArray[n] +"]");

                }
             }


    } //laut Internet wurde Zusatzzahl durch Superzahl ersetzt --> Zusatzzahl wird nicht mehr benötigt
    public void lottoscheinSuperzahl(){

            int superZahl = rand.nextInt(9);
            System.out.println("Ihre Superzahl: " + superZahl);
    }
}




