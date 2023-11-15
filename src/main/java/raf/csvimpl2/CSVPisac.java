package raf.csvimpl2;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;

import java.io.FileWriter;
import java.io.IOException;

public class CSVPisac {

    public CSVPisac() {
    }


    //ovo ovdje je shodno promijeni kod implementacija
    //za sada samo stampa full dogadjaje koji su nam u rasporedu
    public void napisi(Raspored raspored){
        try {
            FileWriter fileWriter = new FileWriter("output.csv");
            for(Dogadjaj dogadjaj : raspored.getDogadjaji()){
                for(String string : dogadjaj.getStavkeDogadjaja()){
                    fileWriter.write("\"" + string + "\"");
                    fileWriter.write(",");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
