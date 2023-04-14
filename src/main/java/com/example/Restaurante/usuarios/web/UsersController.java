package com.example.Restaurante.usuarios.web;

import com.example.Restaurante.application.RestauranteApplication;
import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.usuarios.domain.dto.CreateUserDTO;
import com.example.Restaurante.usuarios.domain.dto.UserInfoDTO;
import com.example.Restaurante.usuarios.domain.service.UserService;
import com.example.Restaurante.usuarios.persistence.entity.UserEntity;
import com.example.Restaurante.usuarios.persistence.mapper.UserToUserDTO;
import com.example.Restaurante.config.security.UserDetailsImpl;
import com.example.Restaurante.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class UsersController implements UsersAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private Utils utils;

    private static final Logger LOG = LoggerFactory.getLogger(RestauranteApplication.class);

    @Override
    public ResponseEntity<UserInfoDTO> consultUserInfo(Integer userId) throws RestException {
        UserEntity userEntity = userService.getUserInfo(userId);
        UserInfoDTO userDTO = UserToUserDTO.INSTANCE.userEntityToUserInfoDTO(userEntity);
        return ResponseEntity.ok().body(userDTO);
    }

    @Override
    public ResponseEntity<Void> createOwner (CreateUserDTO userDTO) throws RestException {
        if(userDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDetailsImpl userDetails = utils.getUserInfo();
        UserEntity user = UserToUserDTO.INSTANCE.createUserDTOToUserEntity(userDTO);
        userService.createOwner(user, userDetails.getRol().getValue());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> createEmployee(CreateUserDTO userDTO) throws RestException {
        if(userDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDetailsImpl userDetails = utils.getUserInfo();
        UserEntity user = UserToUserDTO.INSTANCE.createUserDTOToUserEntity(userDTO);
        userService.createEmployee(user, userDetails.getId(), userDetails.getRol().getValue());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> createClient(CreateUserDTO userDTO) throws RestException {
        if(userDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserEntity user = UserToUserDTO.INSTANCE.createUserDTOToUserEntity(userDTO);
        userService.createClient(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
