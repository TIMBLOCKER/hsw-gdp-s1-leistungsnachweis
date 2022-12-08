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

    /**
     * Konstruktor der den maximalen Auszahlungsbetrag und den maximalen Übererziehungsbetrag festlegt
     */
    public Giro(){
        this.maxDispo = -1000;
        this.maxAuszahlung = 1000;
    }

    /**
     * @param iban Bankkontonummer
     * @param saldo Betrag des Kontos
     */
    public Giro(String iban, double saldo) {
        super(iban, saldo);
        this.maxDispo = -1000; //Maximaler Wert für die Überziehung
        this.maxAuszahlung = 1000; //Eine Auszahlung darf nicht 1000 € Übersteigen
    }

    /**
     * @return gibt den maximalen Überanziehungsbetrag zurück
     */
    public double getMaxDispo() {
        return maxDispo;
    }


    /**
     * @return gibt den maximalen Auszahlungsbetrag zurück
     */
    public double getMaxAuszahlung() {
        return maxAuszahlung;
    }

    /**
     * @return Gibt den Kontotypen zurück --> giro
     */
    public String getType() {
        return "Giro";
    }

    /**
     * @return Rückgabe des maximalen Dispo-Betrages, der maximale Auszahlung-Betrag und der Iban für das Girokonto
     */
    @Override
    public String toString() {
        return "Giro{" +
                "maxDispo=" + maxDispo +
                ", maxAuszahlung=" + maxAuszahlung +
                ", iban='" + iban + '\'' +
                '}';
    }
}
