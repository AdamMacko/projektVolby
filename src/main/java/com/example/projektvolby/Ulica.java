package com.example.projektvolby;

import java.util.Objects;

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

	public Ulica(String nazov, int popisne_cislo, String PSC) {
		this.nazov = nazov;
		this.popisne_cislo = popisne_cislo;
		this.PSC = PSC;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	public static Ulica clone(Ulica v) {
		return new Ulica(v.id,v.nazov,v.popisne_cislo,v.PSC);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ulica ulica = (Ulica) o;
		return popisne_cislo == ulica.popisne_cislo && Objects.equals(id, ulica.id) && Objects.equals(nazov, ulica.nazov) && Objects.equals(PSC, ulica.PSC);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nazov, popisne_cislo, PSC);
	}

	@Override
	public String toString() {
		return nazov +" "+popisne_cislo+" PSC: " + PSC;
	}
}
