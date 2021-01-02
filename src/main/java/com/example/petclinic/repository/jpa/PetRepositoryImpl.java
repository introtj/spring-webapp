package com.example.petclinic.repository.jpa;

import com.example.petclinic.model.Pet;
import com.example.petclinic.repository.PetRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PetRepositoryImpl implements PetRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pet findById(int petId) {
        return this.entityManager.find(Pet.class, petId);
    }

    @Override
    public void savePet(Pet pet) {
        this.entityManager.persist(pet);
    }
}
