package com.example.petclinic.repository;

import com.example.petclinic.model.Visit;

import java.util.Collection;

public interface VisitRepository {
    void saveVisit(Visit visit);

    Collection<Visit> findVisitByPetId(int petId);
}
