package com.example.projektvolby.storage;

import com.example.projektvolby.Volic;


import java.util.List;

public interface VolicDao {
 List<Volic> getAll();
 Volic save(Volic volic)throws EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;
}

