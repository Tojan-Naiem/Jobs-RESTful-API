package org.example.jobsrestfulapi.service;

import org.example.jobsrestfulapi.dto.CompanyDTO;
import org.example.jobsrestfulapi.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
    public void addCompany(CompanyDTO companyDTO, MultipartFile file) throws IOException;
    public Page<CompanyDTO> getCompanies(Pageable pageable,String filterKey,String filterValue);
}
