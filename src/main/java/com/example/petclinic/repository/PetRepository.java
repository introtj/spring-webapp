package com.example.petclinic.repository;

import com.example.petclinic.model.Pet;
import com.example.petclinic.model.PetType;

import java.util.List;

public interface PetRepository {

    Pet findById(int petId);

    void savePet(Pet pet);

    List<PetType> findPetTypes();
}
