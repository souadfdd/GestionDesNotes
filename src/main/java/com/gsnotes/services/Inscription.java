package com.gsnotes.services;


import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.utils.export.ExcelExporter3;

import java.util.List;

public interface Inscription {
    public void addInscription (InscriptionAnnuelle p);

    public void updateInscription (InscriptionAnnuelle p);

    public List<InscriptionAnnuelle> getAllPersons();

    public void deleteInscription (Long id);

    public InscriptionAnnuelle getInscriptionById(Long id);
    public ExcelExporter3 prepareExport(List<InscriptionAnnuelle> inscp ) ;

}
