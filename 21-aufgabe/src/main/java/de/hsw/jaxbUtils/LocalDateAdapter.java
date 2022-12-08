package de.hsw.jaxbUtils;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(filterdate(v));
    }

    public String marshal(LocalDate v) throws Exception {

        return filtertrunaround(v);
    }

    public String filterdate(String handoverdate){
        Calendar cal = new GregorianCalendar();
        if (handoverdate.length() == 10){
            if (handoverdate.matches("^?[0-9-]+$")){
                int year = Integer.parseInt(handoverdate.substring(0,4));
                if(year > 1900 && year <cal.get(Calendar.YEAR)){
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

    public String filtertrunaround(LocalDate handover){
        Calendar cal = new GregorianCalendar();
        String date = handover.toString();
        int year = Integer.parseInt(date.substring(0,4));
        if (year > 1900 && year <cal.get(Calendar.YEAR)){
            return date;
        }else {
            throw new IllegalArgumentException("Der String entspricht nicht dem richtigen Format!");
        }
    }
}