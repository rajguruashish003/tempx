package com.tempx.service.impl;

import com.tempx.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    TokenStore tokenStore;
    @Override
    public void logout(String strAccessToken) {
        try{
            OAuth2AccessToken accessToken=tokenStore.readAccessToken(strAccessToken);
            if(accessToken!=null)
                tokenStore.removeAccessToken(accessToken);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
