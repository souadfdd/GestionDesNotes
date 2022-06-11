package com.gsnotes.services.impl;

import com.gsnotes.bo.InscriptionMatiere;
import com.gsnotes.dao.InscriptionMatiereDao;
import com.gsnotes.services.IInscriptionMatiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class InscriptionMatiereService implements IInscriptionMatiere {
    @Autowired
    private InscriptionMatiereDao dao;
    @Override
    public void addInscriptionMatiere(InscriptionMatiere m) {
      dao.save(m);
    }

    @Override
    public void updateInscriptionMatiere(InscriptionMatiere m) {
            dao.save(m);
    }

    @Override
    public List<InscriptionMatiere> getAllModInscp() {
        return dao.findAll();
    }

    @Override
    public void deleteInscriptionMatiere(Long id) {
        dao.deleteById(id);

    }

    @Override
    public InscriptionMatiere getInscriptionMatiereById(Long id) {
        return  dao.getById(id);
    }

    @Override
    public List<InscriptionMatiere> getInscriptionMatiereByInscriptionAnnuelleAndIdMatiere(Long idAnnuelle,Long idMatiere) {
        return dao.getAllByInscriptionAnnuelle_IdInscriptionAndMatiere_IdMatiere(idAnnuelle,idMatiere);
    }
}
