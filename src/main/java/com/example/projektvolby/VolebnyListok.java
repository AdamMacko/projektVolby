package com.example.projektvolby;

import java.util.Objects;

public class VolebnyListok {
	private Long id;
	private Long stranaId;

	public VolebnyListok() {

	}

	public VolebnyListok( Long stranaId) {
		this.stranaId=stranaId;
	}
	public VolebnyListok( Long id,Long stranaId) {
		this.id=id;
		this.stranaId=stranaId;
	}

	public static VolebnyListok clone(VolebnyListok k) {
		return new VolebnyListok(k.id,k.stranaId);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStranaId() {
		return stranaId;
	}

	public void setStranaId(Long stranaId) {
		this.stranaId = stranaId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VolebnyListok that = (VolebnyListok) o;
		return Objects.equals(id, that.id) && Objects.equals(stranaId, that.stranaId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, stranaId);
	}
}
