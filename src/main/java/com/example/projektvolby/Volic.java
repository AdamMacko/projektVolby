package com.example.projektvolby;


import java.util.Objects;

// trieda uchovavajuca info o volicoch
public class Volic {
	private long id;
	private String meno;
	private String priezvisko;
	private String cOP;
	private boolean dochadzka;
	private Ulica ulica;


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

	public String getcOP() {
		return cOP;
	}

	public void setcOP(String cOP) {
		this.cOP = cOP;
	}

	public boolean isDochadzka() {
		return dochadzka;
	}

	public void setDochadzka(boolean dochadzka) {
		this.dochadzka = dochadzka;
	}

	public Ulica getUlica() {
		return ulica;
	}

	public void setUlica(Ulica ulica) {
		this.ulica = ulica;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Volic volic = (Volic) o;
		return id == volic.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
