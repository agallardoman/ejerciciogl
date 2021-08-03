package com.globallogic.ejerciciogl.controller;

import com.globallogic.ejerciciogl.dto.UserDto;
import com.globallogic.ejerciciogl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> users() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<UserDto> user(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDto> userSave(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.userSave(userDto));
       }

    @PutMapping("/update")
    public ResponseEntity<UserDto> userUpdate(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(userDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity userDelete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
