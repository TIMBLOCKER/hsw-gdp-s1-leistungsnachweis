package de.hsw;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Giro extends Konto {

    @XmlAttribute
    double maxDispo, maxAuszahlung;

    public Giro(){
        this.maxDispo = 1000;
        this.maxAuszahlung = 1000;
    }

    public Giro(String iban, double saldo) {
        super(iban, saldo);
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
