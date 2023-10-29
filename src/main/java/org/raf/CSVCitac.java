package org.raf;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CSVCitac {




    public Raspored citaj(String filename) throws IOException {
        Raspored raspored = new Raspored(new Date());
        String path = filename;
        String line = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        //pocetno ucitaj jednu liniju i na osnovu nje napravi broj hashMapi koji koristimo
        line = bufferedReader.readLine();
        napraviBrojHashMapiZaNiz(line,raspored);
        dodajUHashMapu(line,raspored);

        while ((line = bufferedReader.readLine()) != null) {
            dodajUHashMapu(line,raspored);
        }

        return raspored;
    }


    private static void dodajUHashMapu(String line,Raspored raspored){
        String splits[] = line.split("\"");
        int indexZaHashMapu;
        Dogadjaj dogadjaj = napraviDogadjaj(line);
        for(int i = 0; i < splits.length;i++){
            indexZaHashMapu = i/2;
            if(i % 2 == 1){
                String s = splits[i];
//                System.out.println(s);
                s = s.replaceAll("\\p{Z}","");
//                System.out.println(s);
                ubaciUListu(dogadjaj,raspored,indexZaHashMapu,s);
            }
        }
    }

    private static void ubaciUListu(Dogadjaj dogadjaj,Raspored raspored,int index,String kljuc){
        if(raspored.getBozePomozi().get(index).containsKey(kljuc)){
            HashMap<String,List<Dogadjaj>> hm = raspored.getBozePomozi().get(index);
            List<Dogadjaj> lista = hm.get(kljuc);
            lista.add(dogadjaj);
            hm.put(kljuc,lista);
            raspored.getBozePomozi().set(index,hm);
        }else{
            HashMap<String,List<Dogadjaj>> hm = raspored.getBozePomozi().get(index);
            List<Dogadjaj> lista = new ArrayList<>();
            lista.add(dogadjaj);
            hm.put(kljuc,lista);
            raspored.getBozePomozi().set(index,hm);
        }
    }

    private static void napraviBrojHashMapiZaNiz(String line,Raspored raspored){
        String splits[] = line.split("\"");
        for(int i = 0; i < splits.length;i++){
            if(i % 2 == 1){
                raspored.getBozePomozi().add(new HashMap<String,List<Dogadjaj>>());
            }
        }
    }
    //1 3 5 7 9 11
    // /2
    //0 1 2 3 4
    private static Dogadjaj napraviDogadjaj(String line){
        Dogadjaj dogadjaj = new Dogadjaj(new ArrayList<String>());
        String splits[] = line.split("\"");
        for(int i = 0; i < splits.length;i++){
            if(i % 2 == 1){
                String s = splits[i];
                s = s.replaceAll("\\p{Z}","");
                dogadjaj.getStavkeDogadjaja().add(s);
            }
        }

        return dogadjaj;
    }



    private static void stampajDogadjaje(Raspored raspored){
        for(Dogadjaj dogadjaj : raspored.getDogadjaji()){
            System.out.println(dogadjaj);
        }
    }
}
