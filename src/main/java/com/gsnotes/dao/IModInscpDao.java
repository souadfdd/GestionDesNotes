package com.gsnotes.dao;

import com.gsnotes.bo.InscriptionMatiere;
import com.gsnotes.bo.InscriptionModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IModInscpDao extends JpaRepository<InscriptionModule,Long> {
    List<InscriptionModule> getAllByInscriptionAnnuelle_IdInscriptionAndModule_IdModule(Long inscriptionAnnuelle_idInscription, Long module_idModule);

}
