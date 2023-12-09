package com.example.projektvolby.storage;

import com.example.projektvolby.VolenyKandidat;

import java.util.List;

public interface VolenyKandidatDao {
     List<VolenyKandidat> getAllByKandidatId(long kandidatId);
     VolenyKandidat save(VolenyKandidat volenyKandidat) throws EntityNotFoundException;

}
