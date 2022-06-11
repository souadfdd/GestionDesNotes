package com.gsnotes.dao;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantDao extends JpaRepository<Etudiant, Long> {
    public Etudiant getEtudiantByCin(String cin);
}
