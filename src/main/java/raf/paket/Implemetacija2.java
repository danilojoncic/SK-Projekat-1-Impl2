package raf.paket;

import com.google.common.io.Files;
import model.Specifikacija;
import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Manager;
import model.boljeRijesenje.Raspored;
import raf.StampacKonzola;
import raf.csvimpl2.CSVCitac;
import raf.csvimpl2.CSVPisac;
import raf.jsonimpl2.JSONCitac;
import raf.jsonimpl2.JSONPIsac;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class Implemetacija2 implements Specifikacija {
    private Raspored raspored;

    static {
        Manager.setSpecifikacija(new Implemetacija2());
    }


    public Implemetacija2() {
    }

    @Override
    public void stampaj() {
        StampacKonzola stampacKonzola = new StampacKonzola(raspored);
        stampacKonzola.stampajSadrzajHashMape();
    }

    @Override
    public void exportujRaspored(String filename, String type) {
        if(type.equalsIgnoreCase("csv")){
            List<String> header = this.raspored.getHeader().getStavkeDogadjaja();
            String[] kolone = new String[header.size()];
            CSVPisac csvPisac = new CSVPisac();
            csvPisac.napisi(raspored);
        }else if(type.equalsIgnoreCase("pdf")){

        }else if(type.equalsIgnoreCase("json")){
            JSONPIsac jsonpIsac = new JSONPIsac(this.raspored);
            File file = new File( filename +".json");
            try {
                jsonpIsac.ispisiJSON(file);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void pretraziRaspred(String s) {
        List<Dogadjaj> filtrirani = raspored.vratiFiltrirano(s);
        raspored.refresh(filtrirani);
    }


    @Override
    public void sacuvajRaspored() {
        raspored.refresh(raspored.getDogadjaji());
    }

    public Raspored getRaspored() {
        return raspored;
    }


    @Override
    public Raspored ucitajRaspored(String s){
        String[] extensionCheck = s.split("\\.");
        if(Files.getFileExtension(s).equalsIgnoreCase("csv")){ // najjaca biblioteka bayo Guava firmetina
            CSVCitac csvCitac = new CSVCitac();
            try {
               raspored =  csvCitac.citaj(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if(Files.getFileExtension(s).equalsIgnoreCase("json")){
            JSONCitac jsonCitac = new JSONCitac();
            try {
                raspored = jsonCitac.procitajJSON(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return raspored;
    }
//    public void nejmarUPetercu() {
//        //ovo jednostavno mora na ovaj nacin, nema sanse da se koristi obrisiKolonu jer to pravi problem
//        List<Integer> zaBrisanje = new ArrayList<>();
//        for (int i = 0; i < raspored.getHeader().getStavkeDogadjaja().size(); i++) {
//            String columnHeader = raspored.getHeader().getStavkeDogadjaja().get(i);
//            if (!columnHeader.equals("Termin") && !columnHeader.equals("Dan") && !columnHeader.equals("Učionica")) {
//                zaBrisanje.add(i);
//            }
//        }
//        for (int i = zaBrisanje.size() - 1; i >= 0; i--) {
//            int columnIndex = zaBrisanje.get(i);
//            raspored.getHeader().getStavkeDogadjaja().remove(columnIndex);
//        }
//        for (int i = 0; i < raspored.getDogadjaji().size(); i++) {
//            List<String> row = raspored.getDogadjaji().get(i).getStavkeDogadjaja();
//            for (int j = zaBrisanje.size() - 1; j >= 0; j--) {
//                int columnIndex = zaBrisanje.get(j);
//                row.remove(columnIndex);
//            }
//        }
//        raspored.refresh(raspored.getDogadjaji());
//    }
    private int dajIndeksZaString(String s){
        return raspored.getHeader().getStavkeDogadjaja().indexOf(s);
    }
    public void setRaspored(Raspored raspored) {
        this.raspored = raspored;
    }
}
