package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MysqlKandidatDaoTest {
    private KandidatDao kandidatDao;
    public MysqlKandidatDaoTest(){
        kandidatDao=DaoFactory.INSTANCE.getKandidatiDao();
    }


    @Test
    void getAllByStranaId() {
        System.out.println("getallbystranaid testing: ");
        List<Kandidat> kandidati=kandidatDao.getAllByStranaId(13);
        System.out.println("getALlbystranId funguje pohode: "+kandidati);
    }

    @Test
    void getById() {
        System.out.println("getbyid testing: ");
        Kandidat kandidat=kandidatDao.getById(465);
        System.out.println("getbyId funguje pohode: "+kandidat);
    }

    @Test
    void save() {
        System.out.println("save testing: ");
        List<Kandidat> kandidatipred=kandidatDao.getAllByStranaId(14);
        System.out.println("kandidati strany pred: "+kandidatipred);
        Kandidat kandidat=new Kandidat("Drevorubac","Peter",36);
        kandidatDao.save(kandidat,14);
        List<Kandidat> kandidatipo=kandidatDao.getAllByStranaId(14);
        System.out.println("kandidati strany po: "+kandidatipo);



    }

    @Test
    void delete() {
        System.out.println("Delete testing:");
        List<Kandidat> plnyKandidati=kandidatDao.getAllByStranaId(14);
        System.out.println("Pred mazanim: "+plnyKandidati);
        kandidatDao.delete(467);
        List<Kandidat> odobranyKandidati=kandidatDao.getAllByStranaId(14);
        System.out.println("Po mazani : "+odobranyKandidati);
    }
}