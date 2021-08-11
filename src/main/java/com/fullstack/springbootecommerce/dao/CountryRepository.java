package com.fullstack.springbootecommerce.dao;

import com.fullstack.springbootecommerce.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "countries", path = "countries") //for spring data rest 
public interface CountryRepository extends JpaRepository<Countries, Integer> {
}
