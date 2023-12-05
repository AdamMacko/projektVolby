package com.example.projektvolby.storage;

import com.example.projektvolby.Strana;

import java.util.List;

public interface StranaDao {
    List<Strana> getAll();
    Strana save(Strana strana) throws EntityNotFoundException;
    void delete(long stranaId) throws EntityNotFoundException ;
}
