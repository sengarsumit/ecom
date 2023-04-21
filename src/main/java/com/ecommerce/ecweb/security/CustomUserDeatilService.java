package com.ecommerce.ecweb.security;

import com.ecommerce.ecweb.entity.Role;
import com.ecommerce.ecweb.entity.User;
import com.ecommerce.ecweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDeatilService implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    public CustomUserDeatilService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserEmail(username).orElseThrow(()->new UsernameNotFoundException("user email not found"));
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(),user.getPassword(),mapRolestoAuthorities(user.getRoles()));
    }
    private Collection<GrantedAuthority> mapRolestoAuthorities(List<Role> roles)
    {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }
}
