package org.example.jobsrestfulapi.controller;

import org.apache.tomcat.util.compat.Jre21Compat;
import org.example.jobsrestfulapi.dto.CompanyDTO;
import org.example.jobsrestfulapi.dto.PostDTO;
import org.example.jobsrestfulapi.service.CompanyService;
import org.example.jobsrestfulapi.service.CompanyServiceImp;
import org.example.jobsrestfulapi.service.PostService;
import org.example.jobsrestfulapi.service.fileservice.FileUploadUtil;
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
import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    private CompanyServiceImp companyService;
    private PostService postService;


    public CompanyController(CompanyServiceImp companyService,PostService postService){
        this.companyService=companyService;
        this.postService=postService;
    }
    //{{Jurl}}/company/?filterKey=name&filterValue=exalt

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
    @GetMapping("/{id}")
    public ResponseEntity getCompany(
            @PathVariable String id
    ){

        return ResponseEntity.ok(this.companyService.getCompany(id));
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
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompany(@PathVariable String id) throws IOException {

        this.companyService.deleteCompany(id);
        return ResponseEntity.ok("Successfully deleted company");
    }


    @PutMapping("/{id}")
    public ResponseEntity updateCompany(@PathVariable String id,@RequestPart(value = "company") CompanyDTO companyDTO,@RequestPart(value = "image") MultipartFile file) throws IOException {
        this.companyService.updateCompany(id,companyDTO,file);
        return ResponseEntity.ok("Successfully updated company");
    }
// company posts


    @GetMapping("/{companyId}/posts")
    public ResponseEntity getCompanyPosts(
            @PathVariable String companyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        this.companyService.getCompany(companyId);
        Pageable pageable=PageRequest.of(page,size);
        Page<PostDTO> posts=this.postService.getPostsByCompanyID(companyId,pageable);
        return ResponseEntity.ok(posts);
    }





}
