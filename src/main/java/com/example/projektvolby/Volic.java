package com.example.projektvolby;


// trieda uchovavajuca info o volicoch
public class Volic {
    private Long id;
    private String meno;
    private String priezvisko;
    private String cOP;
    private boolean dochadzka;
    private Long ulicaId;


    public Volic(Long id, String meno, String priezvisko, String cOP,boolean dochadzka,Long ulicaId) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.cOP = cOP;
        this.ulicaId = ulicaId;
        this.dochadzka = dochadzka;
    }

    public String getcOP() {
        return cOP;
    }

    public void setcOP(String cOP) {
        this.cOP = cOP;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public boolean isDochadzka() {
        return dochadzka;
    }

    public void setDochadzka(boolean dochadzka) {
        this.dochadzka = dochadzka;
    }

    public Long getUlica() {
        return ulicaId;
    }

    public void setUlica(Long ulicaId) {
        this.ulicaId = ulicaId;
    }

    @Override
    public String toString() {
        return "Volic{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", priezvisko='" + priezvisko + '\'' +
                ", cOP='" + cOP + '\'' +
                ", dochadzka=" + dochadzka +
                ", ulica='" + ulicaId + '\'' +
                '}';
    }

    public static Volic clone(Volic v) {
        return new Volic(v.id,v.meno,v.priezvisko,v.cOP,v.dochadzka,v.getUlica());
    }


}
