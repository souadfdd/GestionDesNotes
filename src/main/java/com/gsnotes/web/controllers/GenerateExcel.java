
package com.gsnotes.web.controllers;


import com.gsnotes.bo.*;
import com.gsnotes.bo.Module;
import com.gsnotes.services.*;
import com.gsnotes.utils.export.ExcelExporter2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("Generate")
public class GenerateExcel {

    @Autowired
    private IEtudiant Etudiantservice; // On obtient par injection automatique le service
    @Autowired
    private Imodule modelService;
    @Autowired
    private INiveau niveauService;
    @Autowired
    private IInscriptionMatiere inscriptionMatiereService;


    public GenerateExcel() {
        System.out.println("souad");
    }

    @GetMapping("test")
    public String test() {
        return "admin/test";
    }

    @GetMapping("generate")
    public void exportExcel(HttpServletResponse response) throws IOException {
        ArrayList<ArrayList<String>> columnNames = new ArrayList<>();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String dateOnly = dateFormat.format(currentDate);


        columnNames.add(new ArrayList(Arrays.asList("Année Universitaire", "2021/2022", "Date délibération", dateOnly)));
        HashMap<String, Integer> map = new HashMap<String, Integer>();


        ArrayList<String> row2 = new ArrayList<>(Arrays.asList("ID ETUDIANT", "CNE", "NOM", "PRENOM"));
        ArrayList<String> row3 = new ArrayList<>(Arrays.asList("", "", "", ""));
        ArrayList<ArrayList<String>> data = new ArrayList<>();


         List<InscriptionAnnuelle> inscrp = niveauService.getNiveauById(1L).getInscriptions();

         int a=row3.size(); //la taille de ligne 2 dans excel au debut avant insere les elements


        int i = 0;
        for(InscriptionAnnuelle in : inscrp){
            i=0;
            Etudiant etudiant = in.getEtudiant();
            System.out.println("etudiant"+etudiant.getNom());
            ArrayList<String> ligne=new ArrayList();
            ligne.add(String.valueOf(etudiant.getIdUtilisateur()));
            ligne.add(etudiant.getCin());
            ligne.add(etudiant.getNom());
            ligne.add(etudiant.getPrenom());
            for(InscriptionModule inscriptionModule : in.getInscriptionModules()){
                Module module = inscriptionModule.getModule();
                System.out.println("module : "+module.getTitre());
                List<Element> elements = module.getElements();
                if(elements!=null) {
                    //System.out.println("elemens not null");
                    for (Element element : elements) {
                        //System.out.println("inside elemnts");
                        InscriptionMatiere inscriptionMatiere = inscriptionMatiereService.getInscriptionMatiereByInscriptionAnnuelleAndIdMatiere(in.getIdInscription(),element.getIdMatiere()).get(0);
                        ligne.add(String.valueOf(inscriptionMatiere.getNoteFinale()));
                       // System.out.println("element added");
                        System.out.println("note Element"+String.valueOf(inscriptionMatiere.getNoteFinale()));
                        row3.add(element.getNom());
                        i++;

                    }
                }
                row3.add("Moyenne");
                i++;
                //System.out.println("moyenne added");
                ligne.add(String.valueOf(inscriptionModule.getNoteFinale()));
                System.out.println("note module"+String.valueOf(inscriptionModule.getNoteFinale()));
                row3.add("Validation");
                i++;
                System.out.println("validation"+inscriptionModule.getValidation());
               // System.out.println("validation added");


                ligne.add(inscriptionModule.getValidation());

                map.put(module.getTitre(), module.getElements().size()+2);




            }
            data.add(ligne);




        }
        row3.subList(i+a,row3.size()).clear();
        columnNames.add(row2);
        columnNames.add(row3);




        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);


        ExcelExporter2 excelExporter = new ExcelExporter2(columnNames, data, "persons", map);


        excelExporter.export(response);

        }

    }
