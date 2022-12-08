package de.hsw.jaxbUtils;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    public LocalDate unmarshal(String v) throws Exception {
        String date = filterdate(v);
        return LocalDate.parse(date);
    }

    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }

    public String filterdate(String handoverdate){
        if (handoverdate.length() == 10){
            if (handoverdate.matches("^?[0-9-]+$")){
                int year = Integer.parseInt(handoverdate.substring(0,4));
                if(year > 1900){
                    return handoverdate;
                }else {
                    throw new IllegalArgumentException("Der String entspricht nicht dem richtigen Format!");
                }
            }else {
                throw new IllegalArgumentException("Der String entspricht nicht dem richtigen Format!");
            }
        }else {
            throw new IllegalArgumentException("Der String entspricht nicht dem richtigen Format!");
        }
    }
}