package com.example.petclinic.repository.jpa;

import com.example.petclinic.model.Owner;
import com.example.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaOwnerRepositoryImpl implements OwnerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Owner> findByLastName(String lastName) {
        Query query = this.entityManager.createQuery("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName");
        query.setParameter("lastName", lastName + "%");

        return query.getResultList();
    }

    @Override
    public Owner findById(int ownerId) {
        Query query = this.entityManager.createQuery("SELECT owner FROM Owner owner left join fetch owner.pets WHERE owner.id = :ownerId");
        query.setParameter("ownerId", ownerId);

        return (Owner) query.getSingleResult();
    }

    @Override
    public void saveOwner(Owner owner) {
        if (owner.getId() == null) {
            this.entityManager.persist(owner);
        } else {
            this.entityManager.merge(owner);
        }
    }
}
