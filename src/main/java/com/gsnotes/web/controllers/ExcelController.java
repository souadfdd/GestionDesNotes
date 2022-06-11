package com.gsnotes.web.controllers;

import com.gsnotes.bo.*;
import com.gsnotes.bo.Module;
import com.gsnotes.services.IInscriptionMatiere;
import com.gsnotes.services.INiveau;

import com.gsnotes.services.Inscription;
import com.gsnotes.utils.export.ExcelExporter2;
import com.gsnotes.utils.export.ExcelExporter3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("ExcelGenerate")

public class ExcelController {
    @Autowired
    private INiveau niveauService;
    @Autowired
    private Inscription inscription;
    private HashMap<String,String> niveau=new HashMap();

    /**
     * ajout des niveau dans jsp e
     * @param model
     * @return
     */
    @RequestMapping("excel")
    public String ExportForm(Model model){
        for(Niveau niveau1:niveauService.getAllNiveau()){
            niveau.put(niveau1.getTitre(),niveau1.getTitre());
        }
        model.addAttribute("niveau",new Niveau());
        model.addAttribute("Listniveau",niveau);
        return "admin/Excel";
    }

    /**
     * recuper le niveau depuis le form jsp
     * @param niveau
     * @param attributes
     * @return
     */
    @PostMapping("getNiveau")
    public String getForm(@ModelAttribute("niveau") Niveau niveau, RedirectAttributes attributes){
        attributes.addFlashAttribute("niveau",niveauService.getNiveauByTitre(niveau.getTitre()));
        System.out.println(niveauService.getNiveauByTitre(niveau.getTitre()).getIdNiveau());
        return "redirect:/ExcelGenerate/export";

    }

    /**
     * fonction d'export
     * @param model
     * @param response
     * @throws IOException
     */
    @RequestMapping("export")
    public void exporter(Model model, HttpServletResponse response) throws IOException{

       Niveau niveau = (Niveau) model.getAttribute("niveau");
        Long idNiveau=niveau.getIdNiveau();
        //list de s inscriptions
        List<InscriptionAnnuelle> inscrp = niveauService.getNiveauById(idNiveau).getInscriptions();

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);


        ExcelExporter3 excelExporter = inscription.prepareExport(inscrp);
        //excelExporter.formula();

        excelExporter.export(response);

    }


}
