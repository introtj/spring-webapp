package com.example.petclinic.repository;

import com.example.petclinic.model.Owner;

import java.util.List;

public interface OwnerRepository {
    List<Owner> findByLastName(String lastName);

    Owner findById(int ownerId);

    void saveOwner(Owner owner);
}
