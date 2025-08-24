package org.example.jobsrestfulapi.controller;

import org.example.jobsrestfulapi.dto.CompanyDTO;
import org.example.jobsrestfulapi.service.CompanyService;
import org.example.jobsrestfulapi.service.CompanyServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    private CompanyServiceImp companyService;
    public CompanyController(CompanyServiceImp companyService){
        this.companyService=companyService;
    }

    @PostMapping(value = "/" ,consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity addCompany(@RequestPart(value = "company") CompanyDTO companyDTO, @RequestPart(value = "image") MultipartFile file){
        System.out.println("I'm in");

        try{
            System.out.println("I'm in");
            this.companyService.addCompany(companyDTO,file);
            return ResponseEntity.ok("Successfully added company");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There's a bgg errorrr"+e.getMessage());
        }



    }





}
