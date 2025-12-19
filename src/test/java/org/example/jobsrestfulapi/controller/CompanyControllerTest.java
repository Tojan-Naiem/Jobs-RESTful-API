package org.example.jobsrestfulapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.jobsrestfulapi.dto.CompanyDTO;
import org.example.jobsrestfulapi.model.Company;
import org.example.jobsrestfulapi.service.CompanyService;
import org.example.jobsrestfulapi.service.CompanyServiceImp;
import org.example.jobsrestfulapi.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.awt.*;
import java.lang.runtime.ObjectMethods;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;//simulate http requests
    @MockBean
    private CompanyServiceImp companyService;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;


    @BeforeEach
    void print(){
        System.out.println("Successful test ");
    }

    @Test
    public void givenCompanyObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception{
        //create the company object to save the data
        CompanyDTO company=new CompanyDTO("test","test desc","Ramallah","testURL","testImg",new ArrayList<>());
        // addCompany method in CompanyService class is a void method
        doAnswer(invocation -> {
            CompanyDTO dto = invocation.getArgument(0);
            MultipartFile file = invocation.getArgument(1);
            return null;
        }).when(companyService).addCompany(any(CompanyDTO.class), any(MultipartFile.class));

        //create mock file for the image

        MockMultipartFile file=new MockMultipartFile(
                "image",
                "test.png",
                MediaType.IMAGE_PNG_VALUE,
                "image".getBytes(StandardCharsets.UTF_8)
        );

        //simulate the company's data as a JSON

        MockMultipartFile metadata=new MockMultipartFile(
                "company",
                "",
                "application/json",
                objectMapper.writeValueAsBytes(company)
        );
        //execute the request
        mockMvc.perform(
                multipart("/api/v1/company/")
                        .file(file)
                        .file(metadata)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .with(user("admin").roles("ADMIN"))
        ).andExpect(status().isOk())
                .andExpect(content().string("Successfully added company"));
        //verify the service is called
        verify(companyService).addCompany(any(CompanyDTO.class),any(MultipartFile.class));

    }

    public void givenListOCompanies_whenGetAllCompanies_thenReturnCompanyList() throws Exception{
        List<CompanyDTO> listOfCompanies=new ArrayList<>();
    }



}
