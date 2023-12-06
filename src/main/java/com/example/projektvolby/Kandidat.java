package com.example.projektvolby;

public class Kandidat {
	private Long id;
    private String meno;
    private String priezvisko;
    private int vek;

    private com.example.projektvolby.Strana Strana;


    public Kandidat(Long id, String meno, String priezvisko, int vek) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.vek = vek;
    }
    public Kandidat( String meno, String priezvisko, int vek) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.vek = vek;
    }

    public static Kandidat clone(Kandidat k) {
        return new Kandidat(k.id,k.meno,k.priezvisko,k.vek);
    }


    public long getId() {
        return id;
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public int getVek() {
        return vek;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }

    @Override
    public String toString() {
        return meno+" "+priezvisko+" vek: "+vek;

    }
}
