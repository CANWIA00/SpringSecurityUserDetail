package com.example.security3.util;

import com.example.security3.model.Role;
import com.example.security3.model.User;
import com.example.security3.repository.RoleRepository;
import com.example.security3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role roleAdmin = Role.builder().id(1L).roleName("ROLE_ADMIN").build();
        Role roleUser = Role.builder().id(2L).roleName("ROLE_USER").build();

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        List<Role> adminRoleList =new ArrayList<>();
        adminRoleList.add(roleAdmin);

        User admin = User.builder().id(3L).name("Burak").password("{noop}1234").roles(adminRoleList).build();
        userRepository.save(admin);

        List<Role> userRoleList = new ArrayList<>();
        userRoleList.add(roleUser);

        User user = User.builder().id(3L).name("Ali").password("{noop}1234").roles(userRoleList).build();
        userRepository.save(user);


        System.out.println("--Roles--");
        roleRepository.findAll().forEach(System.out::println);
        System.out.println("--Users--");
        userRepository.findAll().forEach(System.out::println);
    }
}
