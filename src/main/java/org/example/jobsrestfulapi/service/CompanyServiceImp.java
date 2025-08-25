package org.example.jobsrestfulapi.service;



import org.example.jobsrestfulapi.dto.CompanyDTO;
import org.example.jobsrestfulapi.exception.ResourcesAlreadyFound;
import org.example.jobsrestfulapi.exception.ResourcesNotFound;
import org.example.jobsrestfulapi.model.Company;
import org.example.jobsrestfulapi.repository.CompanyRepository;
import org.example.jobsrestfulapi.service.fileservice.FileUploadUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CompanyServiceImp implements CompanyService {
    private CompanyRepository componyRepository;
    public CompanyServiceImp(CompanyRepository componyRepository){
        this.componyRepository=componyRepository;
    }
    public Page<CompanyDTO> getCompanies(Pageable pageable,String filterKey,String filterValue){
        Page<Company> companies;
        if(filterKey!=null&&filterValue!=null&&!filterKey.isEmpty()&&!filterValue.isEmpty()){
         switch (filterKey){
             case "name":
                 companies=this.componyRepository.findByNameContainingIgnoreCase(filterValue,pageable);
                 break;
             case "city":
                 companies=this.componyRepository.findByCityContainingIgnoreCase(filterValue,pageable);
                 break;
             default:
                 companies=this.componyRepository.findAll(pageable);
                 break;
         }
        }
        else companies=this.componyRepository.findAll(pageable);

        return companies.map(
                c->new CompanyDTO(
                        c.getName(),
                        c.getDesc(),
                        c.getCity(),
                        c.getUrl(),
                        c.getImage()
                )
        );

    }

    public CompanyDTO getCompany(String id){
        Company company=this.componyRepository.findById(id).orElseThrow(
                ()->{throw new ResourcesNotFound("Company with id "+id+" not found");}
        );
        return new CompanyDTO(
                company.getName(),
                company.getDesc(),
                company.getCity(),
                company.getUrl(),
                company.getImage()
        );
    }
    public void addCompany(CompanyDTO companyDTO, MultipartFile file) throws IOException {
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        boolean exist= this.componyRepository.findAll().stream().anyMatch(
                c->
            c.getName().equals(companyDTO.getName())

        );
        if(exist) {
            System.out.println("hi");
            throw new ResourcesAlreadyFound("The company already in the system");
        }
        Company company=new Company(
                companyDTO.getName(),
                companyDTO.getDesc(),
                companyDTO.getCity(),
                companyDTO.getUrl(),
                companyDTO.getImage()
        );
        company.setImage(fileName);
        Company savedCompany=this.componyRepository.save(company);
        String uploadDir="company-image/"+savedCompany.getId();
        FileUploadUtil.saveFile(uploadDir,fileName,file);
    }

    public void deleteCompany(String id) throws IOException {
        Company company=this.componyRepository.findById(id).orElseThrow(
                ()->{throw new ResourcesNotFound("Company with id "+id+" not found");}
        );
        FileUploadUtil.deleteFile("compony-image/"+company.getId(),company.getImage());

        this.componyRepository.delete(company);
    }

    public void updateCompany(String id,CompanyDTO companyDTO,MultipartFile file) throws IOException {
        Company company=this.componyRepository.findById(id).orElseThrow(
                ()->{throw new ResourcesNotFound("Company with id "+id+" not found");}
        );
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());

        if(!fileName.equals(company.getImage())){


            String uploadDir="company-image/"+company.getId();
            String oldFileName=company.getImage();
            FileUploadUtil.saveFile(uploadDir,fileName,file);
            company.setImage(fileName);
            this.componyRepository.save(company);
            FileUploadUtil.deleteFile(uploadDir,oldFileName);


        }
        company.setName(companyDTO.getName());
        company.setDesc(companyDTO.getDesc());
        company.setCity(companyDTO.getCity());
        company.setUrl(companyDTO.getUrl());
        this.componyRepository.save(company);
    }
}
