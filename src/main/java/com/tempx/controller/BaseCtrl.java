package com.tempx.controller;

import com.tempx.entity.UserDetails;
import com.tempx.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseCtrl {

    @Autowired
    BaseService baseService;

    @RequestMapping(path = "/demo/hello",method = RequestMethod.GET, produces = "application/json")
    public  String displayInputString(@RequestParam String inputString){

        return "Provided Input string is: "+inputString;
    }

    @RequestMapping(path = "/getEncryptedPassword",method = RequestMethod.GET, produces = "application/json")
    public  String getEncryptedPassword(@RequestParam String inputPassword){
        BCryptPasswordEncoder encoder4 = new BCryptPasswordEncoder(4);

        return "Encrypted Password is: "+encoder4.encode(inputPassword);
    }

    @RequestMapping(path = "/demo/getuser",method = RequestMethod.GET, produces = "application/json")
    public  ResponseEntity<Object> getUserDetailsById(@RequestParam long id) {
        ResponseEntity responseEntity = null;
        try {
           UserDetails userDetails= baseService.getUserById(id);
            responseEntity = new ResponseEntity<>(userDetails, HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping("/test")
    public String test() {
        return "Hello World";
    }
}
