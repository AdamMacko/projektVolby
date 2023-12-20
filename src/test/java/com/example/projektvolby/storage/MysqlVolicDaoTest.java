package com.example.projektvolby.storage;

import com.example.projektvolby.Strana;
import com.example.projektvolby.Ulica;
import com.example.projektvolby.Volic;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class MysqlVolicDaoTest {
    private VolicDao volicDao;
    public MysqlVolicDaoTest(){
        volicDao=DaoFactory.INSTANCE.getVoliciDao();
    }

    @Test
    void getAll() {
        System.out.println("getAll testing: ");
        List<Volic> volici=volicDao.getAll();
        System.out.println("getall testing funguje fajn: "+volici);

    }

    @Test
    void save() {
        System.out.println("save testing: ");
        List<Volic> volicipred=volicDao.getAll();
        System.out.println("volici pred savom : "+volicipred);
        Volic volic=new Volic("Dano","Krivo","SK13","Galakticka","04015");
        volicDao.save(volic,valueOf(15));
        List<Volic> volicipo=volicDao.getAll();
        System.out.println("volici po save : "+volicipo);

    }

    @Test
    void delete() {
        System.out.println("delete testing: ");
        List<Volic> volicipred=volicDao.getAll();
        System.out.println("volici pred deletom: "+volicipred);
        volicDao.delete(12);
        List<Volic> volicipo=volicDao.getAll();
        System.out.println("ulice po delete: "+volicipo);

    }

    @Test
    void deleteAll() {
        System.out.println("deleteall testing: ");
        List<Volic> volicipred=volicDao.getAll();
        System.out.println("ulice pred deletom: "+volicipred);
        volicDao.deleteAll();
        List<Volic> volicipo=volicDao.getAll();
        System.out.println("ulice po delete: "+volicipo);

    }

    @Test
    void overHesloMeno() {

        System.out.println("overenie hesla a mena: "+volicDao.overHesloMeno("Dano","Krivo","SK1346"));
    }

    @Test
    void aktualizujDochadzku() {
        volicDao.aktualizujDochadzku("Dano","Krivo","SK1346");


    }

    @Test
    void bolVolit() {
        System.out.println("overenie ci bol dany volic uz volit alebo nie: "+ volicDao.bolVolit("Dano","Krivo","SK134"));

    }
}