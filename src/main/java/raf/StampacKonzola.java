package raf;

import model.boljeRijesenje.Dogadjaj;
import model.boljeRijesenje.Raspored;

import java.util.HashMap;
import java.util.List;

public class StampacKonzola {
    private Raspored raspored;

    public StampacKonzola(Raspored raspored) {
        this.raspored = raspored;
    }

    public void stampajSadrzajHashMape(){
        HashMap<String, List<Dogadjaj>> hm = raspored.getBozePomozi().get(4);
        for (String s : hm.keySet()) {
            System.out.println(s);
            for (Dogadjaj s1 : hm.get(s)) {
                System.out.println(s1);
            }
        }
    }

    public void stampajSveDogadjaje(){
        for(Dogadjaj dogadjaj : raspored.getDogadjaji()){
            System.out.println(dogadjaj);
        }
    }
}
