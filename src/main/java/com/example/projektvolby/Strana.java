package com.example.projektvolby;

import javafx.scene.text.Text;

import java.util.List;
import java.util.Objects;

public class Strana {
	private long id;
	private String nazov;
	private Text volebnyPlan;
	private List<Kandidat> kandidati;

	public Strana() {
	}

	public Strana(Long id, String nazov, Text volebnyPlan, List<Kandidat> kandidati) {
		this.id = id;
		this.nazov = nazov;
		this.volebnyPlan= volebnyPlan;
		this.kandidati = kandidati;
	}


	public Long getId() {
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

	public Text getVolebny_plan() {
		return volebnyPlan;
	}

	public void setVolebny_plan(Text volebny_plan) {
		this.volebnyPlan = volebny_plan;
	}

	public List<Kandidat> getKandidati() {
		return kandidati;
	}

	public void setKandidati(List<Kandidat> kandidati) {
		this.kandidati = kandidati;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Strana strany = (Strana) o;
		return Objects.equals(id, strany.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}
