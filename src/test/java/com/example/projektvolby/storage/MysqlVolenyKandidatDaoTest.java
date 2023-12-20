package com.example.projektvolby.storage;

import com.example.projektvolby.VolebnyListok;
import com.example.projektvolby.VolenyKandidat;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class MysqlVolenyKandidatDaoTest {
    private VolenyKandidatDao volenyKandidatDao;
    public MysqlVolenyKandidatDaoTest(){
        volenyKandidatDao=DaoFactory.INSTANCE.getVolenyKandidatDao();
    }

    @Test
    void getAllByKandidatId() {
        System.out.println("getallbykandidatid testing: ");
        List<VolenyKandidat> volenyKandidati=volenyKandidatDao.getAllByKandidatId(465);
        System.out.println("getAllByKandidatId funguje pohode: "+ volenyKandidati);
    }

    @Test
    void save() {
        System.out.println("save testing: ");
        List<VolenyKandidat>  volenyKandidatipred= volenyKandidatDao.getAllByKandidatId(465);
        System.out.println("pocet volebnych listkov pred: "+volenyKandidatipred.size());
        VolenyKandidat volenyKandidat=new VolenyKandidat(valueOf(465),valueOf(10));
        volenyKandidatDao.save(volenyKandidat);
        List<VolenyKandidat>  volenyKandidatipo=volenyKandidatDao.getAllByKandidatId(465);
        System.out.println("pocet volebnych listkov po: "+volenyKandidatipo.size());
    }
}