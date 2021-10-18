package com.tempx.controller;

import com.tempx.repo.GroupMembersRepo;
import com.tempx.repo.UserRepo;
import com.tempx.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseCtrl {


    @Autowired
    UserRepo userRepo;

    @Autowired
    GroupMembersRepo groupMembersRepo;


    @RequestMapping(path = "/demo/hello", method = RequestMethod.GET, produces = "application/json")
    public String displayInputString(@RequestParam String inputString) {

        return "Provided Input string is: " + inputString;
    }

    @RequestMapping(path = "/getEncryptedPassword", method = RequestMethod.GET, produces = "application/json")
    public String getEncryptedPassword(@RequestParam String inputPassword) {


        return "Encrypted Password is: " + Utils.encryptPassword(inputPassword);
    }


    @RequestMapping("/test")
    public String test() {
        return "Hello World";
    }
}
