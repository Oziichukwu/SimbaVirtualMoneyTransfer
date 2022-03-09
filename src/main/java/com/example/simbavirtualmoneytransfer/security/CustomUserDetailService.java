package com.example.simbavirtualmoneytransfer.security;

import com.example.simbavirtualmoneytransfer.data.models.AppUser;
import com.example.simbavirtualmoneytransfer.data.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static java.lang.String.format;


@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser user = appUserRepository.findUserByEmail(email).orElseThrow(()->
                new UsernameNotFoundException(format("user with email does not exist %s", email)));
        return UserPrincipal.create(user);
    }
}
