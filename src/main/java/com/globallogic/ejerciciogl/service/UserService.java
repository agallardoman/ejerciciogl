package com.globallogic.ejerciciogl.service;

import com.globallogic.ejerciciogl.dto.UserDto;
import com.globallogic.ejerciciogl.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    UserDto userSave(UserDto userDto);
    UserDto update(UserDto userDto);
    List<UserDto> findAll();
    UserDto findById(Long id);
    void deleteById(Long id);

}
