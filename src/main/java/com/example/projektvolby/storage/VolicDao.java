package com.example.projektvolby.storage;

import com.example.projektvolby.Volic;


import java.util.List;

public interface VolicDao {
 List<Volic> getAll();
 Volic save(Volic volic,Long ulicaId)throws EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;


    void deleteAll();
    boolean overHesloMeno(String meno,String priezvisko, String cOP)throws EntityNotFoundException;
     void aktualizujDochadzku(String meno, String priezvisko, String cOP)throws EntityNotFoundException;

    boolean bolVolit (String meno,String priezvisko, String cOP)throws EntityNotFoundException;

}

