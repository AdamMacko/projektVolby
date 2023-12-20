package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;
import com.example.projektvolby.Strana;
import com.example.projektvolby.VolebnyListok;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class MysqlVolebnyListokDaoTest {
    private VolebnyListokDao volebnyListokDao;
    public MysqlVolebnyListokDaoTest(){
        volebnyListokDao=DaoFactory.INSTANCE.getVolebnyListokDao();
    }

    @Test
    void getAll() {
        System.out.println("getAll testing: ");
        List<VolebnyListok> volebneListky=volebnyListokDao.getAll();
        System.out.println("getall testing funguje fajn: "+volebneListky);
    }

    @Test
    void getAllByStranaId() {
        System.out.println("getallbystranaid testing: ");
        List<VolebnyListok> volebneListky=volebnyListokDao.getAllByStranaId(14);
        System.out.println("getALlbystranId funguje pohode: "+ volebneListky);
    }

    @Test
    void save() {
        System.out.println("save testing: ");
        List<VolebnyListok>  volebneListkypred= volebnyListokDao.getAllByStranaId(14);
        System.out.println("pocet volebnych listkov pred: "+volebneListkypred.size());
        VolebnyListok volebnyListok=new VolebnyListok(valueOf(14));
        volebnyListokDao.save(volebnyListok);
        List<VolebnyListok>  volebneListkypo=volebnyListokDao.getAllByStranaId(14);
        System.out.println("pocet volebnych listkov po: "+volebneListkypo.size());

    }

    @Test
    void najvacsieId() {
        System.out.println("najvacsie Id v listoch: "+volebnyListokDao.najvacsieId());
    }
}