package com.example.projektvolby.storage;

import com.example.projektvolby.Strana;

import java.util.List;

public interface OverviewManager {
    List<KandidatOverview> getOverviews(Strana strana);
}
