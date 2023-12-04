package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidati;

import java.util.List;

public interface KandidatiDao {
    List<Kandidati> getAllBySubjectId(long StranaId);
    Kandidati getById(long id) throws EntityNotFoundException;
    Kandidati save(Kandidati kandidat, long StranaId) throws EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;

}
