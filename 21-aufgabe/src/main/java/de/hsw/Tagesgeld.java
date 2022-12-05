package de.hsw;

public class Tagesgeld extends Konto {

    double zinsen;



    public Tagesgeld(String blz, double zinsen) {
        super(blz);
        this.zinsen = zinsen;
    }

    public Tagesgeld(String blz) {
        super(blz);
        this.zinsen = 0.45;
    }

    public Tagesgeld() {
        this.zinsen = 0.45;
    }

    public double getZinsen() {
        return zinsen;
    }

    public void setZinsen(double zinsen) {
        this.zinsen = zinsen;
    }

    public String getType() {
        return "Tagesgeld";
    }
}
