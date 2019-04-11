package com.example.demo.security;

import com.example.demo.Config;
import com.example.demo.CustomerDto;
import com.example.demo.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    private Config config;
    public LoginUserDetailsService(Config config){
        this.config = config;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        RestTemplate restTemplate = new RestTemplate();

       ResponseEntity<CustomerDto> response =
               restTemplate.getForEntity("http://localhost:8080/customers/" + email,
                       CustomerDto.class);
        if(response.getBody() == null){
            throw new UsernameNotFoundException("Email" + email + "doesn't exist.");
        }
        return new LoginUser(response.getBody(),getGrantedAuthority(response.getBody().getRoles()));
        //return new LoginUser(response.getBody().getEmail(), response.getBody().getPassword());
    }

    public List<GrantedAuthority> getGrantedAuthority(Set<Role> roles){
        return  roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

    }
}
