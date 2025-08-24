package org.example.jobsrestfulapi.controller;

import org.apache.tomcat.util.compat.Jre21Compat;
import org.example.jobsrestfulapi.dto.CompanyDTO;
import org.example.jobsrestfulapi.service.CompanyService;
import org.example.jobsrestfulapi.service.CompanyServiceImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    private CompanyServiceImp companyService;
    public CompanyController(CompanyServiceImp companyService){
        this.companyService=companyService;
    }

    @GetMapping("/")
    public ResponseEntity getCompanies(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "5") int size,
            @RequestParam(required = false) String filterKey,
            @RequestParam(required = false) String filterValue
    ){

        Pageable pageable= PageRequest.of(page,size);
        Page<CompanyDTO> companyDTOS=this.companyService.getCompanies(pageable,filterKey,filterValue);
        return ResponseEntity.ok(companyDTOS);
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
        catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There's a bgg errorrr"+e.getMessage());
        }


    }






}
