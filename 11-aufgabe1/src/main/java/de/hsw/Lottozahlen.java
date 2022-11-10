package de.hsw;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;


public class Lottozahlen {

    public void lottoscheinErstellen(int anzahl) {
        Random rand = new Random();


            int[] spielRundenArray = new int[anzahl];
            for (int i = 0; i < spielRundenArray.length; i++) {

                System.out.println("Das sind die Zahlen für Spielrunde " + (i+1) +":");
                int[] lottozahlenArray = new int[6];


                for (int n = 0; n < lottozahlenArray.length; n++) {
                    lottozahlenArray[n] = rand.nextInt(50);
                    System.out.println(lottozahlenArray[n]);

                }
             }
            System.out.println("Ihre Zusatzzahl: ");


            System.out.println("Ihre Superzahl: ");
    }
}



