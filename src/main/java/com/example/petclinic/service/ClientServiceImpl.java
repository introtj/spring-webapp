package com.example.petclinic.service;

import com.example.petclinic.model.Owner;
import com.example.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final OwnerRepository ownerRepository;

    public ClientServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> findOwnerByLastName(String lastName) {
        return this.ownerRepository.findByLastName(lastName);
    }

    @Override
    public Owner findOwnerById(int ownerId) {
        return this.ownerRepository.findById(ownerId);
    }
}
