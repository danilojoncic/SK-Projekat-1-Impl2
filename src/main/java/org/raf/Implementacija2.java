package org.raf;

import model.Specifikacija;
import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;

import java.io.IOException;

public class Implementacija2 implements Specifikacija {


    @Override
    public void stampaj() {

    }

    @Override
    public void kreirajRaspored() {

    }

    @Override
    public void pretraziRaspred() {

    }

    @Override
    public void sacuvajRaspored() {

    }

    @Override
    public void ucitajRaspored(String s) {
        String[] extensionCheck = s.split("\\.");
        if(extensionCheck[1].equalsIgnoreCase("csv")){
            CSVCitac csvCitac = new CSVCitac();
            try {
                csvCitac.citaj(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
