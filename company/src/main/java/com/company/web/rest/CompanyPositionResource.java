package com.company.web.rest;

import com.company.entity.CompanyPosition;
import com.company.service.CompanyPositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyPositionResource {

    private final CompanyPositionService companyPositionService;

    public CompanyPositionResource(CompanyPositionService companyPositionService) {
        this.companyPositionService = companyPositionService;
    }

    @PostMapping("/positions")
    public ResponseEntity create(@RequestBody CompanyPosition companyPosition){
        CompanyPosition result = companyPositionService.save(companyPosition);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/positions")
    public ResponseEntity getAll(){
        List<CompanyPosition> result = companyPositionService.findAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/positions/{name}")
    public ResponseEntity delete(@PathVariable String name){
        companyPositionService.deleteByName(name);
        return ResponseEntity.ok("Kiritilgan lavozim o'chirildi!");
    }

}
