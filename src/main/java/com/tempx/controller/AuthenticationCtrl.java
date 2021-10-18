package com.tempx.controller;

import com.tempx.dto.SignupDto;
import com.tempx.entity.GroupMember;
import com.tempx.entity.Users;
import com.tempx.repo.GroupMembersRepo;
import com.tempx.repo.UserRepo;
import com.tempx.service.AuthenticationService;
import com.tempx.util.ResponseChild;
import com.tempx.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationCtrl {

    @Autowired
    UserRepo userRepo;

    @Autowired
    GroupMembersRepo groupMembersRepo;

    @Autowired
    AuthenticationService authenticationService;


    @RequestMapping(path = "/authentication/signup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> signup(@RequestBody SignupDto signupDto) {
        ResponseEntity responseEntity = null;
        ResponseChild  responseChild=new ResponseChild();
        try {
            if (userRepo.findByUserName(signupDto.getUserName()) != null) {
                responseChild.message="User Name Already Exists";

                return  new ResponseEntity<>(responseChild, HttpStatus.BAD_REQUEST);
            }
            Users users = new Users();
            users.setUserName(signupDto.getUserName());
            users.setPassword(Utils.encryptPassword(signupDto.getPassword()));
            users.setEnabled(true);
            users.setFirstName(signupDto.getfName());
            users.setLastName(signupDto.getlName());
            users = userRepo.save(users);

            GroupMember groupMember = new GroupMember();
            groupMember.setUserName(signupDto.getUserName());
            groupMember.setGroupId(1L);
            groupMembersRepo.save(groupMember);
            responseChild.message="Signup Success!";

            responseEntity = new ResponseEntity<>(responseChild, HttpStatus.OK);
        } catch (Exception e) {
            responseChild.message=e.getMessage();
            responseChild.errorList=new Object[]{e.getStackTrace()};
            responseEntity = new ResponseEntity<>(responseChild, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @RequestMapping(path = "/authentication/logout", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            authenticationService.logout(tokenValue);
        }
    }
}
