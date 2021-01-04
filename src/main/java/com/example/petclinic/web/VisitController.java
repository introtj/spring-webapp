package com.example.petclinic.web;

import com.example.petclinic.model.Pet;
import com.example.petclinic.model.Visit;
import com.example.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VisitController {

    private final ClinicService clinicService;

    public VisitController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @ModelAttribute(name = "visit")
    public Visit beforeGetPetByPetId(@PathVariable(name = "petId") int petId) {
        Visit visit = new Visit();

        Pet pet = this.clinicService.findPetById(petId);
        pet.addVisit(visit);

        return visit;
    }

    @GetMapping(value = "/owners/{ownerId}/pets/{petId}/visits/new")
    public String createForm() {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping(value = "/owners/{ownerId}/pets/{petId}/visits/new")
    public String insertVisit(@Validated Visit visit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            this.clinicService.saveVisit(visit);

            return "redirect:/owners/{ownerId}";
        }
    }
}
