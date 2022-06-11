package com.gsnotes.services.impl;



import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.INiveauDao;
import com.gsnotes.services.INiveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class NiveauService implements INiveau {
    @Autowired
    private INiveauDao Dao;

    @Override
    public void addNiveau(Niveau m) {
        Dao.save(m);

    }

    @Override
    public void updateNiveau(Niveau m) {
        Dao.save(m);
    }

    @Override
    public List<Niveau> getAllNiveau() {
        return Dao.findAll();
    }

    @Override
    public void deleteNiveau(Long id) {
        Dao.deleteById(id);

    }

    @Override
    public Niveau getNiveauById(Long id) {
        return Dao.getById(id);
    }

    @Override
    public Niveau getNiveauByTitre(String titre) {
        return Dao.getNiveauByTitre(titre);
    }
}
