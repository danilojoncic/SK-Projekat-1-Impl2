package raf.jsonimpl2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.boljeRijesenje.Dogadjaj;

public class JSONDogadjajPisac {

    Dogadjaj dogadjaj;
    ObjectMapper objectMapper;

    public JSONDogadjajPisac(Dogadjaj dogadjaj) {
        this.dogadjaj = dogadjaj;
        objectMapper = new ObjectMapper();
    }

    public String ispisiDogadjajJSON() throws JsonProcessingException {

        String json = objectMapper.writeValueAsString(dogadjaj.getStavkeDogadjaja());
        System.out.println(json);
        return json;
    }
}
