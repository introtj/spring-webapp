package com.example.petclinic.repository;

import com.example.petclinic.model.Visit;

import java.util.Collection;

public interface VisitRepository {
    void save(Visit visit);

    Collection<Visit> findVisitByPetId(int petId);
}
