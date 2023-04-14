package com.example.Restaurante.config.security;

import com.example.Restaurante.usuarios.persistence.entity.UserEntity;
import com.example.Restaurante.utils.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private LoginRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity usuario = userRepository.findOneByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con ese email no existe"));
        return new UserDetailsImpl(usuario);
    }
}
