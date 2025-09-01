package org.example.jobsrestfulapi.repository;

import org.example.jobsrestfulapi.model.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    @AfterEach
    public void setUP(){
        companyRepository.deleteAll();
        companyRepository.saveAll(
                List.of(
                        new Company("testName","TestDesc","testCity","testUrl","testImage"),
                        new Company("testName2","TestDesc","testCity","testUrl","testImage"),
                        new Company("testName3","TestDesc","testCity","testUrl","testImage")
                )
        );

    }

    @Test
    public void testFindByNameContainingIgnoreCase(){
        Pageable pageable= PageRequest.of(0,10);

        Page<Company>companies=companyRepository.findByNameContainingIgnoreCase("testName",pageable);
        assertEquals(1,companies.getTotalPages());
        assertEquals(3,companies.getTotalElements());
        assertEquals("testName",companies.getContent().get(0).getName());
    }
    @Test
    public void testFindByCityContainingIgnoreCase(){

        Pageable pageable= PageRequest.of(0,10);

        Page<Company>companies=companyRepository.findByCityContainingIgnoreCase("testCity",pageable);
        assertEquals(1,companies.getTotalPages());
        assertEquals(3,companies.getTotalElements());
        assertEquals("testCity",companies.getContent().get(0).getCity());


    }
}