package de.hsw;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class KundeAdapter extends XmlAdapter<String,Kunde> {


        public Kunde unmarshal(String v) throws Exception {
            return new Kunde();
        }


        public String marshal(Kunde k) throws Exception {
            return k.getStringValue();
        }
    }


