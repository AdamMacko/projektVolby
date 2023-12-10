package com.example.projektvolby;


// trieda uchovavajuca info o volicoch
public class Volic {
    private Long id;
    private String meno;
    private String priezvisko;
    private String cOP;
    private String trvaleBydlisko;
    private String PSC;

    private boolean dochadzka;
    private Long ulicaId;



    public Volic(Long id,String meno, String priezvisko, String cOP, boolean dochadzka, Long ulicaId) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.cOP = cOP;
        this.dochadzka = dochadzka;
        this.ulicaId= ulicaId;

    }


    public Volic (String meno, String priezvisko, String cOP,String trvaleBydlisko,String PSC){
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.cOP = cOP;
        this.trvaleBydlisko=trvaleBydlisko;
        this.PSC = PSC;

    }

    public String getTrvaleBydlisko() {
        return trvaleBydlisko;
    }

    public void setTrvaleBydlisko(String trvaleBydlisko) {
        this.trvaleBydlisko = trvaleBydlisko;
    }

    public String getPSC() {
        return PSC;
    }

    public void setPSC(String PSC) {
        this.PSC = PSC;
    }

    public Long getUlicaId() {
        return ulicaId;
    }

    public void setUlicaId(Long ulicaId) {
        this.ulicaId = ulicaId;
    }

    public Long getId() {
        return id;
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public String getcOP() {
        return cOP;
    }

    public boolean isDochadzka() {
        return dochadzka;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public void setcOP(String cOP) {
        this.cOP = cOP;
    }

    public void setDochadzka(boolean dochadzka) {
        this.dochadzka = dochadzka;
    }




    public static Volic clone(Volic v) {
        return new Volic(v.id,v.meno,v.priezvisko,v.cOP,v.dochadzka, v.ulicaId);
    }

    @Override
    public String toString() {
        return
                "meno='" + meno + '\'' +
                ", priezvisko='" + priezvisko + '\'' +
                ", cOP='" + cOP + '\'' ;

    }
}
