package com.example.Restaurante.Usuarios.domain.service;

import com.example.Restaurante.application.RestauranteApplication;
import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.emun.RestExceptionE;
import com.example.Restaurante.Usuarios.domain.repositorio.UserCrudRepository;
import com.example.Restaurante.emun.RolE;
import com.example.Restaurante.Restaurantes.persistence.entity.RestaurantEntity;
import com.example.Restaurante.Usuarios.persistence.entity.RolEntity;
import com.example.Restaurante.Usuarios.persistence.entity.UserEntity;
import com.example.Restaurante.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(RestauranteApplication.class);

    @Autowired
    private UserCrudRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Utils utils;

    @Transactional
    @Override
    public ResponseEntity<Void> createOwner(UserEntity newUser, String rolCreatingEntity) throws RestException {
        utils.validateCreatingRol(rolCreatingEntity, RolE.ADMIN_VALUE);

        RestExceptionE restExceptionE = validateNewUser(newUser);
        if(restExceptionE != null){
            throw new RestException(restExceptionE);
        }
        createRolUser(newUser, RolE.ONWER_VALUE);
        userRepository.saveUser(
                newUser.getNames(),
                newUser.getLastNames(),
                newUser.getIdNumber(),
                newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()),
                newUser.getCellNumber(),
                newUser.getRol().getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> createEmployee(UserEntity newUser, Integer ownerId, String rolCreatingEntity) throws RestException{
        utils.validateCreatingRol(rolCreatingEntity, RolE.ONWER_VALUE);

        RestExceptionE restExceptionE = validateNewUser(newUser);
        if(restExceptionE != null){
            throw new RestException(restExceptionE);
        }

        Optional<Integer> restaurantOwnerId = userRepository.findRestaurantIdByUserId(ownerId);
        if(restaurantOwnerId == null || restaurantOwnerId.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_OWNER_NOT_RESTAURANT);
        }

        createRolUser(newUser, RolE.EMPLOYEE_VALUE);
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setId(restaurantOwnerId.get());
        newUser.setRestaurant(restaurant);

        userRepository.save(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> createClient(UserEntity newUser) throws RestException{
        RestExceptionE restExceptionE = validateNewUser(newUser);
        if(restExceptionE != null){
            throw new RestException(restExceptionE);
        }

        createRolUser(newUser, RolE.CLIENT_VALUE);
        userRepository.saveUser(
                newUser.getNames(),
                newUser.getLastNames(),
                newUser.getIdNumber(),
                newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()),
                newUser.getCellNumber(),
                newUser.getRol().getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private RestExceptionE validateNewUser(UserEntity user){
        UserEntity newUserExist = userRepository.findUserEntityByNamesAndIdNumber(
                user.getNames(),
                user.getIdNumber());
        Optional<UserEntity> userEmailExist = userRepository.findOneByEmail(user.getEmail());
        if(newUserExist != null){
            return RestExceptionE.ERROR_USER_ALREADY_EXIST;
        }
        if(!userEmailExist.isEmpty()){
            return RestExceptionE.ERROR_USER_EMAIL_ALREADY_EXIST;
        }
        return null;
    }

    private void createRolUser(UserEntity user, RolE rolE){
        RolEntity userRol = new RolEntity();
        userRol.setId(rolE.getId());
        user.setRol(userRol);
    }

    @Override
    public UserEntity getUserInfo(Integer idUser) throws RestException {
        UserEntity userDB = userRepository.findUserEntityById(idUser);
        if(userDB == null){
            throw new RestException(RestExceptionE.ERROR_USER_NOT_FOUND);
        }
        return userDB;
    }
}
