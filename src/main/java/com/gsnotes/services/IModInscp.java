package com.gsnotes.services;





import com.gsnotes.bo.InscriptionMatiere;
import com.gsnotes.bo.InscriptionModule;

import java.util.List;

public interface IModInscp {
    public void addMInscp(InscriptionModule m);

    public void updateModInscp(InscriptionModule m);

    public List<InscriptionModule> getAllModInscp();

    public void deleteModInscp(Long id);

    public InscriptionModule getModInscpById(Long id);
    public List<InscriptionModule> getInscriptionMatiereByInscriptionAnnuelleAndIdModule(Long idAnnuelle, Long idModule) ;


}
