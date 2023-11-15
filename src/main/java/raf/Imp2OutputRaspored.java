package raf;

import model.boljeRijesenje.Dogadjaj;

import java.util.Date;
import java.util.List;

public class Imp2OutputRaspored {
    List<Dogadjaj> dogadjaji;
    Date datumPocetkaVazenja;
    Date datumKrajVazenjea;
    List<Date> datumiDesavanja;


    public Imp2OutputRaspored(List<Dogadjaj> dogadjaji, Date datumPocetkaVazenja, Date datumKrajVazenjea, List<Date> datumiDesavanja) {
        this.dogadjaji = dogadjaji;
        this.datumPocetkaVazenja = datumPocetkaVazenja;
        this.datumKrajVazenjea = datumKrajVazenjea;
        this.datumiDesavanja = datumiDesavanja;
    }


    public List<Dogadjaj> getDogadjaji() {
        return dogadjaji;
    }

    public void setDogadjaji(List<Dogadjaj> dogadjaji) {
        this.dogadjaji = dogadjaji;
    }

    public Date getDatumPocetkaVazenja() {
        return datumPocetkaVazenja;
    }

    public void setDatumPocetkaVazenja(Date datumPocetkaVazenja) {
        this.datumPocetkaVazenja = datumPocetkaVazenja;
    }

    public Date getDatumKrajVazenjea() {
        return datumKrajVazenjea;
    }

    public void setDatumKrajVazenjea(Date datumKrajVazenjea) {
        this.datumKrajVazenjea = datumKrajVazenjea;
    }

    public List<Date> getDatumiDesavanja() {
        return datumiDesavanja;
    }

    public void setDatumiDesavanja(List<Date> datumiDesavanja) {
        this.datumiDesavanja = datumiDesavanja;
    }
}
