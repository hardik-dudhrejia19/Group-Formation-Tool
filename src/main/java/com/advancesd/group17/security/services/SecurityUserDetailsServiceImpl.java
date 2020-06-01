package com.advancesd.group17.security.services;

import com.advancesd.group17.auth.models.Role;
import com.advancesd.group17.security.dao.SecurityUserDetailsDao;
import com.advancesd.group17.security.dao.SecurityUserDetailsDaoImpl;
import com.advancesd.group17.security.models.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SecurityUserDetailsServiceImpl implements SecurityUserDetailsService, UserDetailsService {

    private SecurityUserDetailsDao securityUserDetailsDao = new SecurityUserDetailsDaoImpl();

    @Override
    public com.advancesd.group17.auth.models.User getUserByBannerId(String bannerId, com.advancesd.group17.auth.models.User user) {
        return securityUserDetailsDao.getUserByBannerId(bannerId, user);
    }

    @Override
    public Set<Role> getUserRoleByBannerId(String bannerId, Set<Role> roleList) {
        return securityUserDetailsDao.getUserRoleByBannerId(bannerId, roleList);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.advancesd.group17.auth.models.User user = getUserByBannerId(username, new com.advancesd.group17.auth.models.User());
        user.setRoles(getUserRoleByBannerId(username, new HashSet<Role>()));
        return new UserPrincipal(user);
    }
}
