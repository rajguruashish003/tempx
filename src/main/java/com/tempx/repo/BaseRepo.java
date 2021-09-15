package com.tempx.repo;

import com.tempx.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;

public interface BaseRepo extends CrudRepository<UserDetails,Long> {

    UserDetails findById(long id);

    UserDetails findByFirstName(String firstName);
}
