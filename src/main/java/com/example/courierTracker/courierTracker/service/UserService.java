package com.example.courierTracker.courierTracker.service;

import com.example.courierTracker.courierTracker.entity.RoleEntity;
import com.example.courierTracker.courierTracker.entity.UserEntity;
import com.example.courierTracker.courierTracker.exception.UserAlreadyExistsException;
import com.example.courierTracker.courierTracker.model.UserRegisterModel;
import com.example.courierTracker.courierTracker.reopsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("user with such username doesn't exists");
        }
        ArrayList<SimpleGrantedAuthority> userRoles = new ArrayList<>();
        for(var role: user.getRoles()) {
            userRoles.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(user.getUsername(), user.getPassword(), userRoles);
    }

    public void addUserToDatabase(UserRegisterModel userData) throws UserAlreadyExistsException {
        UserEntity user = userRepo.findByUsername(userData.getUsername());
        if(user != null) {
            throw new UserAlreadyExistsException("user with such username already exists");
        }
        user = new UserEntity();
        user.setUsername(userData.getUsername());
        user.setName(userData.getName());
        user.setSurname(userData.getSurname());
        user.setEmail(userData.getEmail());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        userRepo.save(user);
    }
}
