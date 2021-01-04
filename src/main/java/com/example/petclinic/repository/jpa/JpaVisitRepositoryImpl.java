package com.example.petclinic.repository.jpa;

import com.example.petclinic.model.Visit;
import com.example.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class JpaVisitRepositoryImpl implements VisitRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveVisit(Visit visit) {
        if (visit.getId() == null) {
            this.entityManager.persist(visit);
        } else {
            this.entityManager.merge(visit);
        }
    }

    @Override
    public Collection<Visit> findVisitByPetId(int petId) {
        return this.entityManager.createQuery("SELECT visit FROM Visit AS visit WHERE visit.pet.id = :petId")
            .setParameter("petId", petId)
            .getResultList();
    }
}
