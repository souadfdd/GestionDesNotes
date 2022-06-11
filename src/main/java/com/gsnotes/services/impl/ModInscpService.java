package com.gsnotes.services.impl;



import com.gsnotes.bo.InscriptionModule;
import com.gsnotes.dao.IModInscpDao;

import com.gsnotes.services.IModInscp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModInscpService implements IModInscp {
    @Autowired
    private IModInscpDao dao;
    @Override
    public void addMInscp(InscriptionModule m) {
        dao.save(m);
    }

    @Override
    public void updateModInscp(InscriptionModule m) {
        dao.save(m);

    }

    @Override
    public List<InscriptionModule> getAllModInscp() {
        return dao.findAll();
    }

    @Override
    public void deleteModInscp(Long id) {
         dao.deleteById(id);

    }

    @Override
    public InscriptionModule getModInscpById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<InscriptionModule> getInscriptionMatiereByInscriptionAnnuelleAndIdModule(Long idAnnuelle, Long idModule) {
        return dao.getAllByInscriptionAnnuelle_IdInscriptionAndModule_IdModule(idAnnuelle,idModule);
    }
}
