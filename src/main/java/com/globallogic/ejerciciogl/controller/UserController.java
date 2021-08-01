package com.globallogic.ejerciciogl.controller;

import com.globallogic.ejerciciogl.dto.UserDto;
import com.globallogic.ejerciciogl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> users() {
        List<UserDto> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> user(@PathVariable("id") Long id) {
        UserDto user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDto> userSave(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.userSave(userDto));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> userUpdate(@RequestBody UserDto userDto) {
        UserDto userUpdated = userService.update(userDto);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity userDelete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
