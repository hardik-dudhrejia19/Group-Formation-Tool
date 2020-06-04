package com.advancesd.group17.security;

import com.advancesd.group17.auth.dao.AuthDaoImpl;
import com.advancesd.group17.security.services.SecurityUserDetailsServiceImpl;
import com.advancesd.group17.user.models.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class CustomAuthProvider implements AuthenticationProvider {

    private SecurityUserDetailsServiceImpl userDetailsService = new SecurityUserDetailsServiceImpl();
    private AuthDaoImpl authDao = new AuthDaoImpl();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String bannerId = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        User user = userDetailsService.getUserByBannerId(bannerId, new User());

        List<GrantedAuthority> roles = new ArrayList<>();
        UsernamePasswordAuthenticationToken userToken = null;

        if (user != null) {
            if (authDao.loginAuthentication(user)) {
                if (bannerId.equals("B000000") && BCrypt.checkpw(password, user.getPassword())) {
                    roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                } else {
                    roles.add(new SimpleGrantedAuthority("ROLE_USER"));
                }
                userToken = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                        authentication.getCredentials(),
                        roles);
            } else {
                throw new BadCredentialsException("Bad credentials for " + bannerId);
            }
        } else {
            throw new UsernameNotFoundException("No user with bannerId " + bannerId + " found");
        }
        return userToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
