package com.advancesd.group17.security.services;

import com.advancesd.group17.security.dao.SecurityUserDetailsDao;
import com.advancesd.group17.security.dao.SecurityUserDetailsDaoImpl;
import com.advancesd.group17.user.models.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SecurityUserDetailsServiceImpl implements SecurityUserDetailsService {

    private SecurityUserDetailsDao securityUserDetailsDao = new SecurityUserDetailsDaoImpl();

    @Override
    public com.advancesd.group17.user.models.User getUserByBannerId(String bannerId, com.advancesd.group17.user.models.User user) {
        return securityUserDetailsDao.getUserByBannerId(bannerId, user);
    }

    @Override
    public Set<Role> getUserRoleByBannerId(String bannerId, Set<Role> roleList) {
        return securityUserDetailsDao.getUserRoleByBannerId(bannerId, roleList);
    }
}
