package com.gsnotes.dao;

import com.gsnotes.bo.InscriptionMatiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InscriptionMatiereDao extends JpaRepository<InscriptionMatiere,Long> {

    List<InscriptionMatiere> getAllByInscriptionAnnuelle_IdInscriptionAndMatiere_IdMatiere(Long inscriptionAnnuelle_idInscription, Long matiere_idMatiere);

}
