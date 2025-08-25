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
    public CompanyDTO getCompany(String id);
    public void deleteCompany(String id) throws IOException;
    public void updateCompany(String id,CompanyDTO companyDTO,MultipartFile file) throws IOException;
}
