package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidati;
import com.example.projektvolby.Strany;

import java.util.List;

public interface StranyDao {
    List<Strany> getAll();
    Strany save(Strany strana) throws EntityNotFoundException;
    void delete(long stranaId) throws EntityNotFoundException ;
}
