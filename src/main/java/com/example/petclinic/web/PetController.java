package com.example.petclinic.web;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.PetType;
import com.example.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PetController {

    private final ClinicService clinicService;

    public PetController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @ModelAttribute("types")
    public List<PetType> findPetTypes() {
        return this.clinicService.findPetTypes();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable(name = "ownerId") int ownerId) {
        return this.clinicService.findOwnerById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("pet")
    public void initPetBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new PetValidator());
    }

    @GetMapping(value = "/owners/{ownerId}/pets/new")
    public String createForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);

        model.addAttribute("pet", pet);

        return "pets/createOrUpdatePetForm";
    }

    @PostMapping(value = "/owners/{ownerId}/pets/new")
    public String insertPet(Owner owner, @Validated Pet pet, BindingResult bindingResult) {
        if (owner.getPet(pet.getName()).isPresent()) {
            bindingResult.rejectValue("name", "duplicate", "already exists");
        }

        if (bindingResult.hasErrors()) {
            return "pets/createOrUpdatePetForm";
        } else {
            pet.setOwner(owner);
            this.clinicService.savePet(pet);

            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping(value = "/owners/{ownerId}/pets/{petId}/edit")
    public String editFrom(Owner owner, @PathVariable(name = "petId") int petId, Model model) {
        Pet pet = this.clinicService.findPetById(petId);

        model.addAttribute("pet", pet);

        return "pets/createOrUpdatePetForm";
    }

    @PostMapping(value = "/owners/{ownerId}/pets/{petId}/edit")
    public String updatePet(Owner owner,
                            @Validated Pet pet,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pets/createOrUpdatePetForm";
        } else {
            pet.setOwner(owner);
            this.clinicService.savePet(pet);

            return "redirect:/owners/{ownerId}";
        }
    }
}
