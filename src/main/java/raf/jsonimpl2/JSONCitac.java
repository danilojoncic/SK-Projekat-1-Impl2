package raf.jsonimpl2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JSONCitac {



     // Replace with your file path
    public JSONCitac() {

    }

    public Raspored procitajJSON(String filePath) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Path path = Paths.get(filePath);
        String sadrzaj = new String(Files.readAllBytes(path));

        Gson gson = new Gson();

        Type listType = new TypeToken<List<List<String>>>() {}.getType();
        List<List<String>> listOfLists = gson.fromJson(sadrzaj,listType);

        Raspored raspored = new Raspored();
        raspored.setHeader(new Dogadjaj(listOfLists.get(0)));
        raspored.setDogadjaji(new ArrayList<Dogadjaj>());
        for (List<String> listOfList : listOfLists) {
            Dogadjaj d = new Dogadjaj(listOfList);
            raspored.getDogadjaji().add(d);
        }
        raspored.getDogadjaji().remove(0);
        raspored.refresh(raspored.getDogadjaji());
        return raspored;
    }


//    public static void main(String[] args) throws IOException {
//        CSVCitac csvCitac = new CSVCitac();
//        Raspored raspored = csvCitac.citaj("C:\\Users\\Korisnik\\Desktop\\csv.csv");
//        File file = new File("raspored.json");
//
//        JSONPIsac jsonpIsac = new JSONPIsac(raspored);
//        jsonpIsac.ispisiJSON(file);
//
//        for (Dogadjaj d : raspored.getDogadjaji()) {
//            System.out.println(d);
//        }
//
//    }




}

