package com.example.petclinic.repository.jdbc;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.PetType;
import com.example.petclinic.repository.OwnerRepository;
import com.example.petclinic.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOwnerRepositoryImpl implements OwnerRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcOwnerRepositoryImpl(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("owners")
            .usingGeneratedKeyColumns("id");

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        Map<String, Object> params = new HashMap<>();
        params.put("lastName", lastName + "%");
        List<Owner> owners = this.namedParameterJdbcTemplate.query(
            "SELECT id, first_name, last_name, address, city, telephone FROM owners WHERE last_name like :lastName",
            params,
            BeanPropertyRowMapper.newInstance(Owner.class)
        );
        loadOwnersPetsAndVisits(owners);
        return owners;
    }

    @Override
    public Owner findById(int ownerId) {
        Owner owner;

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", ownerId);

            owner = this.namedParameterJdbcTemplate.queryForObject(
                "SELECT id, first_name, last_name, address, city, telephone FROM owners WHERE id = :id",
                params,
                BeanPropertyRowMapper.newInstance(Owner.class)
            );
        } catch (EmptyResultDataAccessException e) {
            throw new ObjectRetrievalFailureException(Owner.class, ownerId);
        }

        loadPetsAndVisits(owner);

        return owner;
    }

    @Override
    public void saveOwner(Owner owner) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(owner);

        if (owner.isNew()) {
            Number ownerId = this.simpleJdbcInsert.executeAndReturnKey(parameterSource);
            owner.setId(ownerId.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                "UPDATE owners SET first_name = :firstName, last_name = :lastName, address = :address, " +
                    "city = :city, telephone = :telephone WHERE id = :id",
                parameterSource
            );
        }

    }

    public void loadPetsAndVisits(final Owner owner) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", owner.getId());
        final List<JdbcPet> pets = this.namedParameterJdbcTemplate.query(
            "SELECT pets.id, name, birth_date, type_id, owner_id, visits.id as visit_id, visit_date, description, pet_id FROM pets LEFT OUTER JOIN visits ON pets.id = pet_id WHERE owner_id=:id ORDER BY pet_id",
            params,
            new JdbcPetVisitExtractor()
        );
        Collection<PetType> petTypes = getPetTypes();
        for (JdbcPet pet : pets) {
            pet.setType(EntityUtils.getById(petTypes, PetType.class, pet.getTypeId()));
            owner.addPet(pet);
        }
    }

    public Collection<PetType> getPetTypes() {
        return this.namedParameterJdbcTemplate.query(
            "SELECT id, name FROM types ORDER BY name", new HashMap<>(),
            BeanPropertyRowMapper.newInstance(PetType.class));
    }

    private void loadOwnersPetsAndVisits(List<Owner> owners) {
        for (Owner owner : owners) {
            loadPetsAndVisits(owner);
        }
    }
}
