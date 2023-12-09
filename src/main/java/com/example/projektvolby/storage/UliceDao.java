package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;
import com.example.projektvolby.Strana;
import com.example.projektvolby.Ulica;

import java.util.List;

public interface UliceDao {
    List<Ulica> getAll();
    Ulica save(Ulica ulica) throws EntityNotFoundException;
    void delete(long ulicaid) throws EntityNotFoundException ;
    void deleteall();
    int getIdByPSC(String psc);
}
