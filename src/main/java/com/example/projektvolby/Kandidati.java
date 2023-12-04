package com.example.projektvolby;

import java.util.Objects;

public class Kandidati {

	private long id;
    private String meno;
    private String priezvisko;
    private int vek;

    public Kandidati() {

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

    public int getVek() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }


    public Kandidati(Long id, String meno, String priezvisko,int vek) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.vek=vek;
    }
    public Kandidati(String meno, String priezvisko ,int vek) {
        this.meno = meno;
        this.priezvisko= priezvisko;
        this.vek=vek;
    }




    public static Kandidati clone(Kandidati s){
        return new Kandidati(s.id, s.meno, s.priezvisko,s.vek);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kandidati kandidati = (Kandidati) o;
        return Objects.equals(meno, kandidati.meno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return meno + " " + priezvisko + " vek:" + vek;
    }
}
