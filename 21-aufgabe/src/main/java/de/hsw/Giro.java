package de.hsw;

public class Giro extends Konto {

    double maxDispo, maxAuszahlung;

    public Giro(){
        this.maxDispo = 1000;
        this.maxAuszahlung = 1000;
    }

    public double getMaxDispo() {
        return maxDispo;
    }


    public double getMaxAuszahlung() {
        return maxAuszahlung;
    }

    public String getType() {
        return "Giro";
    }

    @Override
    public String toString() {
        return "Giro{" +
                "maxDispo=" + maxDispo +
                ", maxAuszahlung=" + maxAuszahlung +
                ", iban='" + iban + '\'' +
                '}';
    }
}
