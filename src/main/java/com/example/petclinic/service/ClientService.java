package com.example.petclinic.service;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.Vet;
import com.example.petclinic.model.Visit;

import java.util.Collection;
import java.util.List;

public interface ClientService {

    List<Owner> findOwnerByLastName(String lastName);

    Owner findOwnerById(int ownerId);

    void saveOwner(Owner owner);

    Pet findPetById(int petId);

    void savePet(Pet pet);

    void saveVisit(Visit visit);

    Collection<Vet> findVets();

    Collection<Visit> findVisitsByPetId(int petId);
}
