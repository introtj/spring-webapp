package com.example.petclinic.repository;

import com.example.petclinic.model.Pet;

public interface PetRepository {

    Pet findById(int petId);

    void savePet(Pet pet);
}
