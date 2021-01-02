package com.example.petclinic.service;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.Vet;
import com.example.petclinic.model.Visit;
import com.example.petclinic.repository.OwnerRepository;
import com.example.petclinic.repository.PetRepository;
import com.example.petclinic.repository.VetRepository;
import com.example.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final VisitRepository visitRepository;
    private final VetRepository vetRepository;

    public ClientServiceImpl(OwnerRepository ownerRepository,
                             PetRepository petRepository,
                             VisitRepository visitRepository,
                             VetRepository vetRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.visitRepository = visitRepository;
        this.vetRepository = vetRepository;
    }

    @Override
    public List<Owner> findOwnerByLastName(String lastName) {
        return this.ownerRepository.findByLastName(lastName);
    }

    @Override
    public Owner findOwnerById(int ownerId) {
        return this.ownerRepository.findById(ownerId);
    }

    @Override
    @Transactional
    public void saveOwner(Owner owner) {
        this.ownerRepository.saveOwner(owner);
    }

    @Override
    public Pet findPetById(int petId) {
        return this.petRepository.findById(petId);
    }

    @Override
    public void savePet(Pet pet) {
        this.petRepository.savePet(pet);
    }

    @Override
    public void saveVisit(Visit visit) {
        this.visitRepository.saveVisit(visit);
    }

    @Override
    public Collection<Vet> findVets() {
        return this.vetRepository.findVets();
    }

    @Override
    public Collection<Visit> findVisitsByPetId(int petId) {
        return this.visitRepository.findVisitByPetId(petId);
    }
}
