package com.example.petclinic.web;

import com.example.petclinic.model.Vet;
import com.example.petclinic.model.Vets;
import com.example.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
public class VetController {

    private final ClinicService clinicService;

    public VetController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(value = "/vets")
    public String getVets(Model model) {
        Collection<Vet> vetList = this.clinicService.findVets();

        Vets vets = new Vets();
        vets.getVetList().addAll(vetList);

        model.addAttribute("vets", vets);

        return "vets/vetList";
    }

    @GetMapping(value = {"/vets.json", "/vets.xml"})
    @ResponseBody
    public Vets getVetsExport() {
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());

        return vets;
    }
}
