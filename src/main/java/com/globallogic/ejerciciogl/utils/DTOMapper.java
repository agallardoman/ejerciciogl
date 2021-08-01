package com.globallogic.ejerciciogl.utils;

import com.globallogic.ejerciciogl.dto.PhonesDto;
import com.globallogic.ejerciciogl.dto.UserDto;
import com.globallogic.ejerciciogl.entity.Phone;
import com.globallogic.ejerciciogl.entity.User;

import java.util.Collections;
import java.util.stream.Collectors;

public class DTOMapper {

    private DTOMapper() {
    }

    private static void toEntity(UserDto userDto, User user){
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhones(
                userDto.getPhones() == null ? Collections.emptySet():
                        userDto.getPhones().stream().map(DTOMapper::toEntity).collect(Collectors.toSet())
        );
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        toEntity(userDto, user);
        return user;
    }

    private static void toEntity(PhonesDto phonesDto, Phone phone){
        phone.setId(phonesDto.getId());
        phone.setNumber((long) phonesDto.getNumber());
        phone.setCitycod((long) phonesDto.getCitycode());
        phone.setContrycode((long) phonesDto.getContrycode());
        phone.setUser(phone.getUser());
    }

    public static Phone toEntity(PhonesDto phonesDto){
        Phone phone = new Phone();
        toEntity(phonesDto, phone);
        return phone;
    }

}
