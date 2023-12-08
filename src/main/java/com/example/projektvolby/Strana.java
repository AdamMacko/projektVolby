package com.example.projektvolby;

import javafx.scene.text.Text;

import java.util.List;
import java.util.Objects;

public class Strana {
	private Long id;
	private String nazov;
	private String volebnyPlan;
	private List<Kandidat> kandidati;

	public Strana() {
	}

	public Strana(Long id, String nazov, String volebnyPlan, List<Kandidat> kandidati) {
		this.id = id;
		this.nazov = nazov;
		this.volebnyPlan= volebnyPlan;
		this.kandidati = kandidati;
	}
	public Strana( String nazov, String volebnyPlan, List<Kandidat> kandidati) {
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

	public String getVolebny_plan() {
		return volebnyPlan;
	}

	public void setVolebny_plan(String volebny_plan) {
		this.volebnyPlan = volebny_plan;
	}

	public List<Kandidat> getKandidati() {
		return kandidati;
	}

	public void setKandidat(List<Kandidat> kandidati) {
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

	public void setVolebnyPlan(String volebnyPlan) {
		this.volebnyPlan = volebnyPlan;
	}

	@Override
	public String toString() {
		return "Strana{" +
				"id=" + id +
				", nazov='" + nazov + '\'' +
				", volebnyPlan='" + volebnyPlan + '\'' +
				", kandidati=" + kandidati +
				'}';
	}
}
