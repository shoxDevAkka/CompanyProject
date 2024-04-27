package com.company.service;

import com.company.entity.Authority;
import com.company.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    public void delete(String positionName) {
        authorityRepository.deleteByPositionName(positionName);
    }
}
