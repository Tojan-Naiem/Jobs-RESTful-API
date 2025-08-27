package org.example.jobsrestfulapi.service;

import org.example.jobsrestfulapi.dto.RoleDTO;
import org.example.jobsrestfulapi.exception.ResourcesNotFound;
import org.example.jobsrestfulapi.model.Role;
import org.example.jobsrestfulapi.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }
    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
    public Role getRole(String id){
        return roleRepository.findById(id).orElseThrow(
                ()->new ResourcesNotFound("Role not found")
        );
    }
    public Role addRole(RoleDTO roleDTO){
        if(roleRepository.findByName(roleDTO.getName()).isPresent())
            throw  new ResourcesNotFound("Role already exists");
        Role role=new Role();
        role.setName(roleDTO.getName());
        Role createdRole=this.roleRepository.save(role);
        return createdRole;
    }
    public Role updateRole(String id,Role updateedRole){
        Role role=roleRepository.findById(id).orElseThrow(
                ()->new ResourcesNotFound("Role id not found")
        );
        role.setName(updateedRole.getName());
        Role savedRole=this.roleRepository.save(role);
        return savedRole;
    }
    public void deleteRole(String id){
        this.roleRepository.deleteById(id);
    }


}
