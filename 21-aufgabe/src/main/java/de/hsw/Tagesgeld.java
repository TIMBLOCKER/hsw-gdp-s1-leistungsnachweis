package de.hsw;

public class Tagesgeld extends Konto {

    double zinsen;



    public Tagesgeld(double zinsen) {
        super();
        this.zinsen = zinsen;
    }

    public Tagesgeld(String blz) {
        super(blz);
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
