package com.service.impl;

import com.domain.Author;
import com.domain.Role;
import com.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    AuthorRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author authorName = repository.findByName(username);

        return buildUserDetails(authorName);
    }

    private List<GrantedAuthority> getAuthorities(Set<Role> roles) {
        Function<Role, SimpleGrantedAuthority> function = role -> new SimpleGrantedAuthority(role.getName().name());
        return roles
                .stream()
                .map(function)
                .collect(Collectors.toList());
    }

    private UserDetails buildUserDetails(Author authorName) {
        List<GrantedAuthority> authorities = getAuthorities(authorName.getRoles());

        return new User(authorName.getName(), authorName.getPassword(), authorName.isActive(), true,
                true, true, authorities);
    }




}
