package de.hsw;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Tagesgeld extends Konto {

    @XmlAttribute
    double zinsen;

    /**
     * legt den Zinssatz für das Tagesgeldkonto fest
     */
    public Tagesgeld() {
        this.zinsen = 0.75;
    }

    /**
     * @param iban Iban
     * @param saldo Wert des Kontos
     */
    public Tagesgeld(String iban, double saldo) {
        super(iban, saldo); //Vererbt von der Methode Konto
        this.zinsen = 0.75;
    }

    /**
     * @return gibt den Zinssatz zurück
     */
    public double getZinsen() {
        return zinsen;
    }

    /**
     * @return gibt den Kontotyp zurück --> TAGESGELD
     */
    public String getType() {
        return "Tagesgeld";
    }

    /**
     * @return Rückgabe der Zinsen und der Iban für das Tagesgeld Konto
     */
    @Override
    public String toString() {
        return "Tagesgeld{" +
                "zinsen=" + zinsen +
                ", iban='" + iban + '\'' +
                '}';
    }
}
