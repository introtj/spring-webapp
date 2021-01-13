package com.example.petclinic.repository.jpa;

import com.example.petclinic.model.Pet;
import com.example.petclinic.model.PetType;
import com.example.petclinic.repository.PetRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaPetRepositoryImpl implements PetRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pet findById(int petId) {
        return this.entityManager.find(Pet.class, petId);
    }

    @Override
    public void save(Pet pet) {
        if (pet.getId() == null) {
            this.entityManager.persist(pet);
        } else {
            this.entityManager.merge(pet);
        }
    }

    @Override
    public List<PetType> findPetTypes() {
        return this.entityManager.createQuery("SELECT petType FROM PetType AS petType ORDER BY petType.name")
            .getResultList();
    }
}
