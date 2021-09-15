package com.tempx.service.impl;

import com.tempx.entity.UserDetails;
import com.tempx.repo.BaseRepo;
import com.tempx.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    BaseRepo baseRepo;

    @Override
    public UserDetails getUserById(long id) {
        return baseRepo.findById(id);
    }

    @Override
    public UserDetails getUserByFirstName(String firstName) {
        return baseRepo.findByFirstName(firstName);
    }
}
