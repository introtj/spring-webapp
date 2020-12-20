package com.example.petclinic.web;

import com.example.petclinic.model.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PetController {

    public PetController() {
    }

    @RequestMapping(value = "/owners")
    public String getOwners(Owner owner, BindingResult bindingResult, Model model) {

        return "owners/ownersList";
    }
}
