package com.fullstack.springbootecommerce.dao;

import com.fullstack.springbootecommerce.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
public interface ProductRepository extends JpaRepository<Product, Long>{

    // Page: sublist of list of objects , query method
    // @requestParam extracts the id from query param 
    Page<Product> findByCategoryId(@RequestParam("id") long id, Pageable pageable); 

    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
}
