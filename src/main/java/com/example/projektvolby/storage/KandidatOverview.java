package com.example.projektvolby.storage;

public class KandidatOverview {
    private String meno;
    private String priezvisko;
    private int pocetHlasov;
    private double percentaHlasov;

    public KandidatOverview(String meno, String priezvisko, int pocetHlasov, double percentaHlasov) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.pocetHlasov = pocetHlasov;
        this.percentaHlasov = percentaHlasov;
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public int getPocetHlasov() {
        return pocetHlasov;
    }

    public double getPercentaHlasov() {
        return percentaHlasov;
    }

}
