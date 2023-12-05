package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;
import sk.upjs.attender.storage.EntityNotFoundException;

import java.util.List;

public interface KandidatDao {
    List<Kandidat> getAllByStranaId(long StranaId);
    Kandidat getById(long id) throws EntityNotFoundException;
    Kandidat save(Kandidat kandidat, long StranaId) throws EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;

}
