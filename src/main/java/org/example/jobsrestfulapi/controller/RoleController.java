package org.example.jobsrestfulapi.controller;

import jakarta.validation.Valid;
import org.example.jobsrestfulapi.dto.RoleDTO;
import org.example.jobsrestfulapi.model.Role;
import org.example.jobsrestfulapi.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private RoleService roleService;
    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }
    @GetMapping("/")
    public ResponseEntity getRoles(){
        return ResponseEntity.ok(this.roleService.getRoles());
    }
    @GetMapping("/{id}")
    public ResponseEntity getRole(@PathVariable String id){
        return ResponseEntity.ok(this.roleService.getRole(id));
    }
    @PostMapping("/")
    public ResponseEntity<Role> addRole(@Valid @RequestBody RoleDTO role){
        Role savedRole=this.roleService.addRole(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable String id,@Valid @RequestBody Role role){
        Role updatedRole=this.roleService.updateRole(id,role);
        return new ResponseEntity<>(updatedRole, HttpStatus.CREATED);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@PathVariable String id){
        this.roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted successfully");

    }
}
