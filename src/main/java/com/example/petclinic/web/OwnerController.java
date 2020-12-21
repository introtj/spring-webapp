package com.example.petclinic.web;

import com.example.petclinic.model.Owner;
import com.example.petclinic.service.ClientService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

public class OwnerController {

    private final ClientService clientService;

    public OwnerController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/owners")
    public String getOwners(Owner owner, BindingResult bindingResult, Model model) {

        this.clientService.findOwnerByLastName();
        return "owners/ownersList";
    }
}
