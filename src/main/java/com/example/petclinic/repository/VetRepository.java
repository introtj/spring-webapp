package com.example.petclinic.repository;

import com.example.petclinic.model.Vet;

import java.util.Collection;

public interface VetRepository {
    Collection<Vet> findVets();
}
