package com.example.petclinic.service;

import com.example.petclinic.model.Owner;

import java.util.List;

public interface ClientService {

    List<Owner> findOwnerByLastName();
}
