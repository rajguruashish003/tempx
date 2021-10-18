package com.tempx.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {

    public  static  String encryptPassword(String inputPassword){
        BCryptPasswordEncoder encoder4 = new BCryptPasswordEncoder(4);
        return encoder4.encode(inputPassword);
    }
}
