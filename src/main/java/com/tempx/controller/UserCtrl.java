package com.tempx.controller;

import com.tempx.dto.RequestIdsDto;
import com.tempx.dto.SignupDto;
import com.tempx.entity.Users;
import com.tempx.repo.GroupMembersRepo;
import com.tempx.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCtrl {
    @Autowired
    UserRepo userRepo;

    @Autowired
    GroupMembersRepo groupMembersRepo;

    @RequestMapping(path = "/getcurrentuser", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getCurrentUser() {
        ResponseEntity responseEntity = null;
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().split("Username=")[1].split(", Password")[0];
            Users users = userRepo.findByUserName(userName);

            responseEntity = new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(path = "/user", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateUser(@RequestBody SignupDto signupDto) {
        ResponseEntity responseEntity = null;
        try {
            Users users = userRepo.findByUserName(signupDto.getUserName());
            if (users == null) {
                return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
            }
            users.setFirstName(signupDto.getfName());
            users.setLastName(signupDto.getlName());
            userRepo.save(users);
            responseEntity = new ResponseEntity<>("User updated Successfully!", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @RequestMapping(path = "/user", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@RequestBody RequestIdsDto requestIdsDto) {
        ResponseEntity responseEntity = null;
        try {
            if (requestIdsDto != null && requestIdsDto.getIds() != null) {
                for (long l : requestIdsDto.getIds()) {
                    Users users = userRepo.findById(l).get();
                    userRepo.delete(users);
                    groupMembersRepo.deleteByuserName(users.getUserName());
                }
                responseEntity = new ResponseEntity<>("User deleted Successfully!", HttpStatus.OK);
            }
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
