package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;
import com.example.projektvolby.Strana;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class MysqlStranaDaoTest {
    private StranaDao stranaDao;
    public MysqlStranaDaoTest(){
        stranaDao=DaoFactory.INSTANCE.getStranyDao();
    }

    @Test
    void getAll() {
        System.out.println("getAll testing: ");
        List<Strana> strany=stranaDao.getAll();
        System.out.println("getall testing funguje fajn: "+strany);
    }

    @Test
    void save() {
        System.out.println("save testing: ");
        List<Strana> stranyPred=stranaDao.getAll();
        System.out.println("strany pred: "+stranyPred);
        Kandidat kandidat=new Kandidat("Peter","SPiakovsky",45);
        List<Kandidat> kandidati=new ArrayList<>();
        kandidati.add(kandidat);
        Strana strana=new Strana(valueOf(16),"SKP","Toto je nova testovacia strana",kandidati);
        stranaDao.save(strana);
        List<Strana> stranyPo =stranaDao.getAll();
        System.out.println("strany po: "+stranyPo);
    }

    @Test
    void delete() {
        System.out.println("delete testing: ");
        List<Strana> stranyPred=stranaDao.getAll();
        System.out.println("strany pred: "+stranyPred);
        stranaDao.delete(13);
        List<Strana> stranyPo =stranaDao.getAll();
        System.out.println("strany po: "+stranyPo);
    }
}