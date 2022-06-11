package com.gsnotes.services.impl;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.dao.EtudiantDao;

import com.gsnotes.services.IEtudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EtudiantService implements IEtudiant {
    @Autowired
    private EtudiantDao dao;
    @Override
    public void addEtudiant(Etudiant pPerson) {
        dao.save(pPerson);
    }

    @Override
    public void updateEtudiant(Etudiant pPerson) {
        dao.save(pPerson);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return dao.findAll();
    }

    @Override
    public void deleteEtudiant(Long id) {
        dao.deleteById(id);

    }

    @Override
    public Etudiant getEtudiantById(Long id) {
        return dao.getById(id);
    }

    @Override
    public Etudiant getEtudiantByCin(String cin) {
        return dao.getEtudiantByCin(cin);
    }
}
