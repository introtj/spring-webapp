package com.example.petclinic.web;

import com.example.petclinic.model.Owner;
import com.example.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OwnerController {

    private final ClinicService clinicService;

    public OwnerController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(value = "/owners")
    public String getOwners(Owner owner, BindingResult bindingResult, Model model) {

        List<Owner> owners = this.clinicService.findOwnerByLastName(owner.getLastName());

        model.addAttribute("selections", owners);

        return "owners/ownersList";
    }

    @GetMapping(value = "/owners/find")
    public String findForm(Model model) {
        model.addAttribute("owner", new Owner());

        return "owners/findOwners";
    }

    @GetMapping(value = "/owners/{ownerId}")
    public String getOwnerById(@PathVariable(name = "ownerId") int ownerId, Model model) {
        Owner owner = this.clinicService.findOwnerById(ownerId);
        if (owner == null) {

        }

        model.addAttribute("owner", owner);

        return "owners/ownerDetails";
    }

    @GetMapping(value = "/owners/new")
    public String createOwnerForm(Model model) {
        Owner owner = new Owner();

        model.addAttribute("owner", owner);

        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping(value = "/owners/new")
    public String insertOwner(@Validated Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            this.clinicService.saveOwner(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping(value = "/owners/{ownerId}/edit")
    public String editOwner(@PathVariable(name = "ownerId") int ownerId, Model model) {
        model.addAttribute("owner", this.clinicService.findOwnerById(ownerId));

        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping(value = "/owners/{ownerId}/edit")
    public String updateOwner(@PathVariable(name = "ownerId") int ownerId,
                              @ModelAttribute(name = "owner") @Valid Owner owner,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            owner.setId(ownerId);

            return "owners/createOrUpdateOwnerForm";
        } else {
            owner.setId(ownerId);
            this.clinicService.saveOwner(owner);

            return "redirect:/owners/{ownerId}";
        }
    }
}
