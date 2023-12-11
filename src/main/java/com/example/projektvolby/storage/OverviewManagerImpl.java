package com.example.projektvolby.storage;

import com.example.projektvolby.Kandidat;
import com.example.projektvolby.Strana;
import com.example.projektvolby.VolebnyListok;
import com.example.projektvolby.VolenyKandidat;

import java.util.ArrayList;
import java.util.List;

public class OverviewManagerImpl implements OverviewManager{
    @Override
    public List<KandidatOverview> getOverviews(Strana strana) {
        List<Kandidat> kandidati=strana.getKandidati();
        VolenyKandidatDao volenyKandidatDao=DaoFactory.INSTANCE.getVolenyKandidatDao();
        VolebnyListokDao volebnyListokDao=DaoFactory.INSTANCE.getVolebnyListokDao();
        List<VolebnyListok> volebnyListokyStrany=volebnyListokDao.getAllByStranaId(strana.getId());
        int hlasyStrany=0;
        List<KandidatOverview> vysledok= new ArrayList<KandidatOverview>();
        for (Kandidat kandidat:kandidati){
            List<VolenyKandidat> volenyKandidat=volenyKandidatDao.getAllByKandidatId(kandidat.getId());
            int pocet=volenyKandidat.size();
            double percenta=0.0;
            if (pocet>0 && !volebnyListokyStrany.isEmpty()){
                hlasyStrany=volebnyListokyStrany.size();
                percenta=100*pocet/hlasyStrany;
            }
            vysledok.add(new KandidatOverview(kandidat.getMeno(), kandidat.getPriezvisko(), pocet,percenta));
        }
        return vysledok;

    }


    }
