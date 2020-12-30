package com.example.petclinic.web;

import com.example.petclinic.model.Owner;
import com.example.petclinic.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OwnerController {

    private final ClientService clientService;

    public OwnerController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/owners")
    public String getOwners(Owner owner, BindingResult bindingResult, Model model) {

        List<Owner> owners = this.clientService.findOwnerByLastName(owner.getLastName());

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
        model.addAttribute("owner", this.clientService.findOwnerById(ownerId));

        return "owners/ownerDetails";
    }
}
