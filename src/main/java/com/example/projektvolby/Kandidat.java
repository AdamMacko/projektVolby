package com.example.projektvolby;

import java.util.Objects;

public class Kandidat {

	private long id;
    private String meno;
    private String priezvisko;
    private int vek;

    public Kandidat() {

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


    public Kandidat(Long id, String meno, String priezvisko, int vek) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.vek=vek;
    }
    public Kandidat(String meno, String priezvisko , int vek) {
        this.meno = meno;
        this.priezvisko= priezvisko;
        this.vek=vek;
    }




    public static Kandidat clone(Kandidat s){
        return new Kandidat(s.id, s.meno, s.priezvisko,s.vek);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kandidat kandidati = (Kandidat) o;
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
