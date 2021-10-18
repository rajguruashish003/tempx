package com.tempx.repo;

import com.tempx.entity.GroupMember;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface GroupMembersRepo extends CrudRepository<GroupMember,Long> {
    @Transactional
    @Modifying
    void deleteByuserName(String userName);
}
