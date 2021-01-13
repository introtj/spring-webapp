package com.example.petclinic.repository.jdbc;

import com.example.petclinic.model.Vet;
import com.example.petclinic.repository.VetRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class JdbcVetRepositoryImpl implements VetRepository {
    @Override
    public Collection<Vet> findAll() {
        return null;
    }
}
