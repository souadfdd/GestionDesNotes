package com.gsnotes.services;

import com.gsnotes.bo.Etudiant;
import java.util.List;

public interface IEtudiant {
    public void addEtudiant(Etudiant pPerson);

    public void updateEtudiant(Etudiant pPerson);

    public List<Etudiant> getAllEtudiants();

    public void deleteEtudiant(Long id);

    public Etudiant getEtudiantById(Long id);

    public Etudiant getEtudiantByCin(String cin);
}
