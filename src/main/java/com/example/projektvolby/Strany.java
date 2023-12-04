package com.example.projektvolby;

import javafx.scene.text.Text;

import java.util.List;
import java.util.Objects;

public class Strany {
	private long id;
	private String nazov;
	private Text volebnyPlan;
	private List<Kandidati> kandidati;

	public Strany() {
	}

	public Strany(Long id, String nazov, Text volebnyPlan, List<Kandidati> kandidati) {
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

	public List<Kandidati> getKandidati() {
		return kandidati;
	}

	public void setKandidati(List<Kandidati> kandidati) {
		this.kandidati = kandidati;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Strany strany = (Strany) o;
		return Objects.equals(id, strany.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}
