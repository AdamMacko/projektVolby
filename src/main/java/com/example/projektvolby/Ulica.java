package com.example.projektvolby;

public class Ulica {
	private Long id;
	private String nazov;
	private int popisne_cislo;
	private String PSC;


	public Ulica(Long id, String nazov, int popisne_cislo, String PSC) {
		this.id = id;
		this.nazov = nazov;
		this.popisne_cislo = popisne_cislo;
		this.PSC = PSC;

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

	public String getPSC() {
		return PSC;
	}

	public void setPSC(String PSC) {
		this.PSC = PSC;
	}


}
