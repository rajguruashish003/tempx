package com.tempx.service;

import com.tempx.entity.UserDetails;

public interface BaseService {

    UserDetails getUserById(long id);
    UserDetails getUserByFirstName(String firstName);
}
