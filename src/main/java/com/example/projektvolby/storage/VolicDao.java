package com.example.projektvolby.storage;

import com.example.projektvolby.Volic;
import sk.upjs.attender.storage.EntityNotFoundException;

import java.util.List;

public interface VolicDao {
    List<Volic> getById(long id) throws EntityNotFoundException;
    Volic save (Volic volic) throws EntityNotFoundException;

    void delete(long id) throws EntityNotFoundException;


}
