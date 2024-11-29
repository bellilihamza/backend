package com.example.motos.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.motos.Moto;
import com.example.motos.service.MotoService;

@Controller
public class MotoController {
	
    @Autowired
    MotoService motoService;

	@RequestMapping("/listeMotos") 
    public String listeMotos(ModelMap modelMap, 
    @RequestParam (name="page",defaultValue = "0") int page, 
    @RequestParam (name="size", defaultValue = "3") int size) 
    { 
    Page<Moto> prods = motoService.getAllMotosParPage(page, size); 
    modelMap.addAttribute("motos", prods); 
    modelMap.addAttribute("pages", new int[prods.getTotalPages()]);  
    modelMap.addAttribute("currentPage", page);    
    return "listeMotos";  
    } 

    @RequestMapping("/showCreateMoto")
    public String showCreateMoto() {
        return "createMoto";
    }

    @RequestMapping("/saveMoto")
    public String saveMoto(@ModelAttribute("moto") Moto moto,
                           @RequestParam("date") String date,
                           ModelMap modelMap) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateFormat.parse(date);
        moto.setDateCreation(dateCreation);
        Moto savedMoto = motoService.saveMoto(moto);
        String msg = "Moto enregistrée avec Id " + savedMoto.getIdMotot();
        modelMap.addAttribute("msg", msg);
        return "createMoto";
    }

    @RequestMapping("/supprimerMoto")
    public String supprimerMoto(@RequestParam("id") Long id, 
    ModelMap modelMap, 
    @RequestParam (name="page",defaultValue = "0") int page, 
    @RequestParam (name="size", defaultValue = "6") int size) 
    { 
    motoService.deleteMotoById(id); 
    Page<Moto> prods = motoService.getAllMotosParPage(page, size); 
	modelMap.addAttribute("motos", prods); 
	modelMap.addAttribute("pages", new int[prods.getTotalPages()]);  
	modelMap.addAttribute("currentPage", page);  
	modelMap.addAttribute("size", size);  
	return "listeMotos";  


    		} 

    @RequestMapping("/modifierMoto")
    public String editerMoto(@RequestParam("id") Long id,
                             ModelMap modelMap) {
        Moto moto = motoService.getMoto(id);
        modelMap.addAttribute("moto", moto);
        return "editerMoto";
    }

    @RequestMapping("/updateMoto")
    public String updateMoto(@ModelAttribute("moto") Moto moto,
                             @RequestParam("date") String date,
                             ModelMap modelMap) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateFormat.parse(date);
        moto.setDateCreation(dateCreation);
        motoService.updateMoto(moto);
        List<Moto> motos = motoService.getAllMotos();
        modelMap.addAttribute("motos", motos);
        return "listeMotos";
    }
}
