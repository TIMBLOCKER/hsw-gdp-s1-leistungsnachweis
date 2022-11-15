package de.hsw;

public class Starter {
    public static void main(String[] args) {
        Code code = new Code();

        System.out.println("Von welcher Zahl möchtest du die Fakultät rechnen?");
        int eingabe = code.eingabe();
        System.out.println();
        code.fakberechnen(eingabe);
    }
}