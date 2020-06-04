package com.advancesd.group17.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.advancesd.group17.auth.dao.AuthDaoImpl;
import com.advancesd.group17.user.models.User;

public class CustomAuthProvider implements AuthenticationProvider {

    //private SecurityUserDetailsServiceImpl userDetailsService = new SecurityUserDetailsServiceImpl();
    private AuthDaoImpl authDao = new AuthDaoImpl();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String bannerId = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

//        User user = userDetailsService.getUserByBannerId(bannerId, new User());
        User user = new User();
        user.setPassword(password);
        user.setBannerId(bannerId);
        
        List<GrantedAuthority> roles = new ArrayList<>();
        UsernamePasswordAuthenticationToken userToken = null;

        
        if (authDao.loginAuthentication(user)) {
//            if (bannerId.equals("B000000") && BCrypt.checkpw(password, user.getPassword())) {
        	if (bannerId.equals("B000000") && password.equals("admin")) {
                roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                roles.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            userToken = new UsernamePasswordAuthenticationToken(authentication.getPrincipal().toString(),
                    authentication.getCredentials().toString(),
                    roles);
        } else {
            throw new BadCredentialsException("Bad credentials for " + bannerId);
        }
        
        return userToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
