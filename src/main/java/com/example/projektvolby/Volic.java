package com.example.projektvolby;


// trieda uchovavajuca info o volicoch
public class Volic {
    private long id;
    private String meno;
    private String priezvisko;
    private String cOP;
    private boolean dochadzka;
    private String ulica;

    public Volic(Long id, String meno, String priezvisko, String cOP, String ulica, boolean dochadzka) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.cOP = cOP;
        this.ulica = ulica;
        this.dochadzka = dochadzka;
    }

    public String getcOP() {
        return cOP;
    }

    public void setcOP(String cOP) {
        this.cOP = cOP;
    }

    public long getId() {
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

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    @Override
    public String toString() {
        return "Volic{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", priezvisko='" + priezvisko + '\'' +
                ", cOP='" + cOP + '\'' +
                ", dochadzka=" + dochadzka +
                ", ulica='" + ulica + '\'' +
                '}';
    }


}
