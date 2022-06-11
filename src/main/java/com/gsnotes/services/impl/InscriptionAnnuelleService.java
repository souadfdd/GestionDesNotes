package com.gsnotes.services.impl;

import com.gsnotes.bo.*;
import com.gsnotes.bo.Module;
import com.gsnotes.dao.InscrDao;
import com.gsnotes.services.IInscriptionMatiere;
import com.gsnotes.services.Inscription;
import com.gsnotes.utils.export.ExcelExporter3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class InscriptionAnnuelleService implements Inscription {
    @Autowired
    private InscrDao dao;
    @Autowired
    private IInscriptionMatiere inscriptionMatiereService;

    @Override
    public void addInscription(InscriptionAnnuelle p) {
        dao.save(p);

    }

    @Override
    public void updateInscription(InscriptionAnnuelle p) {
        dao.save(p);

    }

    @Override
    public List<InscriptionAnnuelle> getAllPersons() {
        return dao.findAll();
    }

    @Override
    public void deleteInscription(Long id) {
        dao.deleteById(id);

    }

    @Override
    public InscriptionAnnuelle getInscriptionById(Long id) {
        return dao.getById(id);
    }

    @Override
    public ExcelExporter3 prepareExport(List<InscriptionAnnuelle> inscrp) {

        ArrayList<ArrayList<String>> columnNames = new ArrayList<>();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String dateOnly = dateFormat.format(currentDate);


        columnNames.add(new ArrayList(Arrays.asList("Année Universitaire", "2021/2022", "Date délibération", dateOnly)));
        HashMap<String, Integer> map = new HashMap<String, Integer>();


        ArrayList<String> row2 = new ArrayList<>(Arrays.asList("ID ETUDIANT", "CNE", "NOM", "PRENOM"));
        ArrayList<String> row3 = new ArrayList<>(Arrays.asList("", "", "", ""));
        ArrayList<ArrayList<Object>> data = new ArrayList<>();






        for(InscriptionAnnuelle in : inscrp){
            for(InscriptionModule inscriptionModule : in.getInscriptionModules()){
                Module module = inscriptionModule.getModule();
                List<Element> elements = module.getElements();
                if(elements!=null) {
                    for (Element element : elements) {
                        row3.add(element.getNom());
                    }
                }
                row3.add("Moyenne");
                row3.add("Validation");


                map.put(module.getTitre(), module.getElements().size()+2);

            }
            break;
        }

        columnNames.add(row2);
        columnNames.add(row3);


        //partie 2 insertion de donne
        for(InscriptionAnnuelle in : inscrp){

            Etudiant etudiant = in.getEtudiant();
            System.out.println("***************************************************");
            ArrayList<Object> ligne=new ArrayList();
            ligne.add(String.valueOf(etudiant.getIdUtilisateur()));
            ligne.add(etudiant.getCin());
            ligne.add(etudiant.getNom());
            ligne.add(etudiant.getPrenom());
            for(InscriptionModule inscriptionModule : in.getInscriptionModules()){
                Module module = inscriptionModule.getModule();
                List<Element> elements = module.getElements();
                if(elements!=null) {

                    for (Element element : elements) {

                        InscriptionMatiere inscriptionMatiere = inscriptionMatiereService.getInscriptionMatiereByInscriptionAnnuelleAndIdMatiere(in.getIdInscription(),element.getIdMatiere()).get(0);
                        ligne.add(String.valueOf(inscriptionMatiere.getNoteFinale()));
                        System.out.println("inscript"+inscriptionMatiere.getNoteFinale());
                        System.out.println("matier"+inscriptionMatiere.getMatiere().getNom());

                    }
                }

                ligne.add(String.valueOf(inscriptionModule.getNoteFinale()));
                ligne.add(inscriptionModule.getValidation());

            }
            data.add(ligne);

        }
        System.out.println("tableau"+data);

/*        //creation de formula

        String moyenneFormula = "";
        char colName = 'D';
        int size = map.size();
        for(String key: map.keySet()) {
            colName+=map.get(key)-1;
            moyenneFormula += (colName) + "" +(4) ;
            if ((--size) > 0) {
                moyenneFormula += "+";
            }
            colName++;
        }
        moyenneFormula="("+moyenneFormula+")/"+String.valueOf(map.size());

        System.out.println("formula"+moyenneFormula);*/








        //return new ExcelExporter3(columnNames, data, "persons", map, moyenneFormula);
        return new ExcelExporter3(columnNames, data, "persons", map);


    }


}
