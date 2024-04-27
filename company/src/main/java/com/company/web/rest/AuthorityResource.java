package com.company.web.rest;

import com.company.entity.Authority;
import com.company.service.AuthorityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorityResource {

    private final AuthorityService authorityService;

    public AuthorityResource(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @PostMapping("/authorities")
    public ResponseEntity createAuthority(@RequestBody Authority authority){
        Authority result = authorityService.save(authority);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/authorities")
    public ResponseEntity getAll(){
        List<Authority> result = authorityService.findAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/authorities")
    public ResponseEntity delete(@RequestParam String positionName){
        authorityService.delete(positionName);
        return ResponseEntity.ok("PositionName is deleted successfully!");
    }
}
