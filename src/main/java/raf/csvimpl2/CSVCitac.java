package raf.csvimpl2;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSVCitac {


    private List<String> daniUNedelji = new ArrayList<>(Arrays.asList("PON","UTO","SRE","ČET","PET","SUB","NED"));

    public Raspored citaj(String filename) throws IOException {
        Raspored raspored = new Raspored(new Date());
        String path = filename;
        String line = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        //pocetno ucitaj jednu liniju i na osnovu nje napravi broj hashMapi koji koristimo
        line = bufferedReader.readLine();
        raspored.getDogadjaji().add(napraviDogadjaj(line));
        napraviBrojHashMapiZaNiz(line,raspored);
//        dodajUHashMapu(line,raspored);
        raspored.setHeader(raspored.getDogadjaji().get(0));
        raspored.getDogadjaji().remove(0);



        while ((line = bufferedReader.readLine()) != null) {
            dodajUHashMapu(line,raspored);
        }

        return raspored;
    }

    private boolean danUNedelji(String string){
        for (String s : daniUNedelji) {
            if(s.equalsIgnoreCase(string)){
                return true;
            }
            if(string.contains(s)){
                return true;
            }
        }
        return false;
    }


    private void dodajUHashMapu(String line,Raspored raspored){
        String splits[] = line.split("\"");
        int indexZaHashMapu;
        Dogadjaj dogadjaj = napraviDogadjaj(line);
        raspored.getDogadjaji().add(dogadjaj);
        for(int i = 0; i < splits.length;i++){
            indexZaHashMapu = i/2;
            if(i % 2 == 1){
                String s = splits[i];
                if(danUNedelji(s)){
                    s = s.replaceAll("\\p{Z}","");
                }
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
    private Dogadjaj napraviDogadjaj(String line){
        Dogadjaj dogadjaj = new Dogadjaj(new ArrayList<String>());
        String splits[] = line.split("\"");
        for(int i = 0; i < splits.length;i++){
            if(i % 2 == 1){
                String s = splits[i];
                if(danUNedelji(s)){
                    s = s.replaceAll("\\p{Z}","");
                }
                dogadjaj.getStavkeDogadjaja().add(s);
            }
        }

        return dogadjaj;
    }

    public void napraviTempFajl(String fileName){
        try {
            // Create a FileWriter object with the file name
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.close();
            System.out.println("Temp file created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void upisiUTempFajl(String fileName,List<Dogadjaj> dogadjaji){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for (Dogadjaj dogadjaj : dogadjaji) {
                fileWriter.write(dogadjaj.toString());
                fileWriter.write("\n");
            }
            fileWriter.close();
            System.out.println("Temp file created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Raspored refreshRaspored(List<Dogadjaj> dogadjaji) throws IOException {
        String fileName = "new";
        napraviTempFajl(fileName);
        upisiUTempFajl(fileName,dogadjaji);
        Raspored raspored = new Raspored(new Date());
        String line = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        //pocetno ucitaj jednu liniju i na osnovu nje napravi broj hashMapi koji koristimo
        line = bufferedReader.readLine();
        napraviBrojHashMapiZaNiz(line,raspored);
        dodajUHashMapu(line,raspored);

        while ((line = bufferedReader.readLine()) != null) {
            dodajUHashMapu(line,raspored);
        }
        return raspored;

    }


}
