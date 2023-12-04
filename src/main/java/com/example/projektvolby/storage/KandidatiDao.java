package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;

import java.util.List;

public interface KandidatiDao {
    List<Kandidat> getAllBySubjectId(long StranaId);
    Kandidat getById(long id) throws EntityNotFoundException;
    Kandidat save(Kandidat kandidat, long StranaId) throws EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;

}
