package com.example.Restaurante.utils;

import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.config.security.UserDetailServiceImpl;
import com.example.Restaurante.config.security.UserDetailsImpl;
import com.example.Restaurante.emun.RestExceptionE;
import com.example.Restaurante.emun.RolE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    @Autowired
    private UserDetailServiceImpl userDetails;
    public UserDetailsImpl getUserInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetailsImpl)principal).getUsername();
        } else {
            username = principal.toString();
        }

        UserDetailsImpl usuario = userDetails.loadUserByUsername(username);
        return usuario;

    }

    public void validateUserRol (String rolCreatingEntity, RolE rolE) throws RestException {
        if(!rolCreatingEntity.equals(rolE.getValue())){
            throw new RestException(RestExceptionE.UNAUTHORIZED);
        }
    }

}
