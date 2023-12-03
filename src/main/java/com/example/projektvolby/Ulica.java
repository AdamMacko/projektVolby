package com.example.projektvolby;

public class Ulica {
	private long id;
	private String nazov;
	private int popisne_cislo;
	private int PSC;
	private String obec;


	public Ulica(long id, String nazov, int popisne_cislo, int PSC, String obec) {
		this.id = id;
		this.nazov = nazov;
		this.popisne_cislo = popisne_cislo;
		this.PSC = PSC;
		this.obec = obec;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazov() {
		return nazov;
	}

	public void setNazov(String nazov) {
		this.nazov = nazov;
	}

	public int getPopisne_cislo() {
		return popisne_cislo;
	}

	public void setPopisne_cislo(int popisne_cislo) {
		this.popisne_cislo = popisne_cislo;
	}

	public int getPSC() {
		return PSC;
	}

	public void setPSC(int PSC) {
		this.PSC = PSC;
	}

	public String getObec() {
		return obec;
	}

	public void setObec(String obec) {
		this.obec = obec;
	}
}
