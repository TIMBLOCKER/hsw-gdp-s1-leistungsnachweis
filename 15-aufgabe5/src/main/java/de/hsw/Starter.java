package de.hsw;

public class Starter {
    public static void main(String[] args) {
        Code code = new Code();

        System.out.println("von welcher Zahl möchtest du die Fakultät rechnen?");
        int eingabe = code.eingabe();
        System.out.println();
        code.fakberechnen(eingabe);
    }
}