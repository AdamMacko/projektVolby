package com.example.projektvolby;

import java.util.Objects;

public class VolenyKandidat {
	private Long id;
	private Long kandidatId;
	private Long listokId;

	public VolenyKandidat(Long id, Long kandidatId,Long listokId) {
		this.id = id;
		this.kandidatId=kandidatId;
		this.listokId=listokId;
	}
	public VolenyKandidat() {

	}
	public static VolenyKandidat clone(VolenyKandidat k) {
		return new VolenyKandidat(k.id,k.kandidatId,k.listokId);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKandidatId() {
		return kandidatId;
	}

	public void setKandidatId(Long kandidatId) {
		this.kandidatId = kandidatId;
	}

	public Long getListokId() {
		return listokId;
	}

	public void setListokId(Long listokId) {
		this.listokId = listokId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VolenyKandidat that = (VolenyKandidat) o;
		return Objects.equals(id, that.id) && Objects.equals(kandidatId, that.kandidatId) && Objects.equals(listokId, that.listokId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, kandidatId, listokId);
	}
}
