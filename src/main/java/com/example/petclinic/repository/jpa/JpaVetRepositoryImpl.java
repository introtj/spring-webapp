package com.example.petclinic.repository.jpa;

import com.example.petclinic.model.Vet;
import com.example.petclinic.repository.VetRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class JpaVetRepositoryImpl implements VetRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<Vet> findAll() {
        return this.entityManager
            .createQuery("SELECT vet FROM Vet AS vet left join fetch vet.specialties ORDER BY vet.lastName, vet.firstName", Vet.class)
            .getResultList();
    }
}
