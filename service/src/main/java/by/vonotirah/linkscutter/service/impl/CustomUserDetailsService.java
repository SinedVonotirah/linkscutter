package by.vonotirah.linkscutter.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService{
 
    @Inject
    private UserService userService;
     
    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        UserAccount user = userService.getUserByLogin(login);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = new User(user.getLogin(), user.getPassword(), authorities);
        return userDetails;
    }
}

          
    
 
     

