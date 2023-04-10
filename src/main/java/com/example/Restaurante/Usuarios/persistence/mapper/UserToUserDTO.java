package com.example.Restaurante.Usuarios.persistence.mapper;

import com.example.Restaurante.Usuarios.domain.dto.CreateUserDTO;
import com.example.Restaurante.Usuarios.domain.dto.UserInfoDTO;
import com.example.Restaurante.Usuarios.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToUserDTO {

    UserToUserDTO INSTANCE = Mappers.getMapper(UserToUserDTO.class);

    @Mappings({
            @Mapping(target = "userDNI", source = "idNumber"),
            @Mapping(target = "userRol", source = "rol")
    })
    UserInfoDTO userEntityToUserInfoDTO(UserEntity user);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "idNumber", source = "userDNI"),
            @Mapping(target = "restaurant.id", source = "idRestaurant"),
            @Mapping(target = "rol.id", source = "idRol")

    })
    UserEntity createUserDTOToUserEntity(CreateUserDTO userDTO);

}
