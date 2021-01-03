package com.example.petclinic.service;

import com.example.petclinic.model.*;

import java.util.Collection;
import java.util.List;

public interface ClinicService {

    List<Owner> findOwnerByLastName(String lastName);

    Owner findOwnerById(int ownerId);

    void saveOwner(Owner owner);

    Pet findPetById(int petId);

    void savePet(Pet pet);

    void saveVisit(Visit visit);

    Collection<Vet> findVets();

    Collection<Visit> findVisitsByPetId(int petId);

    List<PetType> findPetTypes();
}
