package com.Authentication.SpringSecurityJWT.Auth;

import java.util.HashSet;
import java.util.Set;

// for logout uer to check token is black listed or not. 
public class TokenBlacklistService {
    private final Set<String> blacklistedTokens = new HashSet<>();

    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
