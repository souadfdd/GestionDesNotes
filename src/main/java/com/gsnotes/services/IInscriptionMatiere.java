package com.gsnotes.services;



import com.gsnotes.bo.InscriptionMatiere;

import java.util.List;

public interface IInscriptionMatiere {
    public void addInscriptionMatiere(InscriptionMatiere m);

    public void updateInscriptionMatiere(InscriptionMatiere m);

    public List<InscriptionMatiere> getAllModInscp();

    public void deleteInscriptionMatiere(Long id);

    public InscriptionMatiere getInscriptionMatiereById(Long id);

    public List<InscriptionMatiere> getInscriptionMatiereByInscriptionAnnuelleAndIdMatiere(Long idAnnuelle,Long idMatiere) ;
}
