package de.hsw;

public class Tagesgeld extends Konto {

    double zinsen;

    public Tagesgeld() {
        this.zinsen = 0.75;
    }

    public double getZinsen() {
        return zinsen;
    }

    public String getType() {
        return "Tagesgeld";
    }

    @Override
    public String toString() {
        return "Tagesgeld{" +
                "zinsen=" + zinsen +
                ", iban='" + iban + '\'' +
                '}';
    }
}
