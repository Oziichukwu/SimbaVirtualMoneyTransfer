package com.example.simbavirtualmoneytransfer.services.appUser;

import com.example.simbavirtualmoneytransfer.data.models.AppUser;
import com.example.simbavirtualmoneytransfer.data.repositories.AppUserRepository;
import com.example.simbavirtualmoneytransfer.dtos.request.CreateAppUserRequest;
import com.example.simbavirtualmoneytransfer.dtos.request.LoginRequest;
import com.example.simbavirtualmoneytransfer.dtos.response.CreateAppUserResponse;
import com.example.simbavirtualmoneytransfer.dtos.response.JwtResponseToken;
import com.example.simbavirtualmoneytransfer.security.CustomUserDetailService;
import com.example.simbavirtualmoneytransfer.security.JwtTokenProvider;
import com.example.simbavirtualmoneytransfer.security.UserPrincipal;
import com.example.simbavirtualmoneytransfer.web.exceptions.EmailAlreadyExistException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public CreateAppUserResponse register(CreateAppUserRequest createAppUserRequest) {

        if (createAppUserRequest == null){
            throw new IllegalArgumentException("Argument cannot be null");
        }

        if (appUserRepository.existsByEmail(createAppUserRequest.getEmail())){
            throw new EmailAlreadyExistException("User with this email "
                    + createAppUserRequest.getEmail()
                    + " already exist");
        }

        AppUser user = modelMapper.map(createAppUserRequest, AppUser.class);

        user.setPassword(passwordEncoder.encode(createAppUserRequest.getPassword()));

        AppUser savedUser = save(user);

        return modelMapper.map(savedUser, CreateAppUserResponse.class);
    }

    @Override
    public JwtResponseToken login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserPrincipal userDetails = (UserPrincipal) customUserDetailService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtTokenProvider.generateToken(userDetails);
        AppUser user = internalFindUserByEmail(loginRequest.getEmail());

        return new JwtResponseToken(token , user.getEmail());
    }

    private AppUser internalFindUserByEmail(String email) {
        if (email == null){
            throw new IllegalArgumentException("Argument cannot be null");
        }
        return appUserRepository.findUserByEmail(email).orElse(null);
    }


    private AppUser save(AppUser user) {
        return appUserRepository.save(user);
    }
}
