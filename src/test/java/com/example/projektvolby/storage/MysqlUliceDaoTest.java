package com.example.projektvolby.storage;

import com.example.projektvolby.Strana;
import com.example.projektvolby.Ulica;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MysqlUliceDaoTest {
    private UliceDao uliceDao;
    public  MysqlUliceDaoTest(){
        uliceDao=DaoFactory.INSTANCE.getUliceDao();
    }

    @Test
    void getAll() {
        System.out.println("getAll testing: ");
        List<Ulica> ulice=uliceDao.getAll();
        System.out.println("getall testing funguje fajn: "+ulice);
    }

    @Test
    void save() {
        System.out.println("save testing: ");
        List<Ulica> ulicepred=uliceDao.getAll();
        System.out.println("ulice pred pridanim: "+ulicepred);
        Ulica ulica=new Ulica("Galakticka",8,"04016");
        uliceDao.save(ulica);
        List<Ulica> ulicepo=uliceDao.getAll();
        System.out.println("ulice po pridani: "+ulicepo);
    }

    @Test
    void delete() {
        System.out.println("delete testing: ");
        List<Ulica> ulicepred=uliceDao.getAll();
        System.out.println("ulice pred deletom: "+ulicepred);
        uliceDao.delete(14);
        List<Ulica> ulicepo=uliceDao.getAll();
        System.out.println("ulice po delete: "+ulicepo);

    }

    @Test
    void deleteall() {
        System.out.println("deleteall testing: ");
        List<Ulica> ulicepred=uliceDao.getAll();
        System.out.println("ulice pred deletom: "+ulicepred);
        uliceDao.deleteall();
        List<Ulica> ulicepo=uliceDao.getAll();
        System.out.println("ulice po delete: "+ulicepo);

    }

    @Test
    void getIdByPSC() {
        int id=uliceDao.getIdByPSC("04015");
        System.out.println(id);

    }
}