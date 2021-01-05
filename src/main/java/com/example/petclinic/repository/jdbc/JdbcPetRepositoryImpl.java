package com.example.petclinic.repository.jdbc;

import com.example.petclinic.model.Pet;
import com.example.petclinic.model.PetType;
import com.example.petclinic.repository.PetRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcPetRepositoryImpl implements PetRepository {
    @Override
    public Pet findById(int petId) {
        return null;
    }

    @Override
    public void savePet(Pet pet) {

    }

    @Override
    public List<PetType> findPetTypes() {
        return null;
    }
}
