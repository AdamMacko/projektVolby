package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;
import com.example.projektvolby.Strana;
import com.example.projektvolby.Ulica;
import com.example.projektvolby.VolebnyListok;

import java.util.List;

public interface VolebnyListokDao {
    List<VolebnyListok> getAll();
    List<VolebnyListok> getAllByStranaId(long StranaId);

    VolebnyListok save(VolebnyListok volebnyListok, long StranaId) throws EntityNotFoundException;

    
}
