package com.tempx.repo;

import com.tempx.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<Users,Long> {

    Users findByUserName(String userName);
}
