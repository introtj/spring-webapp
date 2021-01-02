package com.example.petclinic.repository.jpa;

import com.example.petclinic.model.Vet;
import com.example.petclinic.repository.VetRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class VetRepositoryImpl implements VetRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<Vet> findVets() {
        return this.entityManager
            .createQuery("SELECT vet FROM Vet AS vet", Vet.class)
            .getResultList();
    }
}
