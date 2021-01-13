package com.example.petclinic.repository.jdbc;

import com.example.petclinic.model.Visit;
import com.example.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class JdbcVisitRepositoryImpl implements VisitRepository {
    @Override
    public void save(Visit visit) {

    }

    @Override
    public Collection<Visit> findVisitByPetId(int petId) {
        return null;
    }
}
