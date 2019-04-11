package com.example.demo.security;

import com.example.demo.CustomerDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LoginUser extends User {
    public LoginUser(CustomerDto customer, List<GrantedAuthority> grantedAuthorityList) {
        super(customer.getEmail(), customer.getPassword(), grantedAuthorityList);

    }


}
