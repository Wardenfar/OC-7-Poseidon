package com.nnk.springboot.service;

import com.nnk.springboot.config.UserDetailsImpl;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Get current connected user
     */
    public String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object user = auth.getPrincipal();
        System.out.println(user.getClass());
        if (user instanceof UserDetailsImpl) {
            UserDetailsImpl details = (UserDetailsImpl) user;
            return details.getUsername();
        } else if (user instanceof DefaultOAuth2User) {
            DefaultOAuth2User details = (DefaultOAuth2User) user;
            return details.getAttributes().get("login").toString();
        } else {
            return "NA";
        }
    }
}
