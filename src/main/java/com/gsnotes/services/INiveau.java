package com.gsnotes.services;

import com.gsnotes.bo.Niveau;

import java.util.List;

public interface INiveau {
    public void addNiveau(Niveau m);

    public void updateNiveau(Niveau m);

    public List<Niveau> getAllNiveau();

    public void deleteNiveau(Long id);

    public Niveau getNiveauById(Long id);
    public Niveau getNiveauByTitre(String titre);
}

