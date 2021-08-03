package com.globallogic.ejerciciogl.controller

import com.globallogic.ejerciciogl.dto.UserDto
import com.globallogic.ejerciciogl.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import java.time.LocalDate

class UserControllerSpec extends Specification {
    UserController userController
    UserService userService = Mock(UserService)

    def setup(){
        userController = new UserController(userService)
    }

    def "get all users"() {
        given:
        List<UserDto> users = new ArrayList<>()
        50.times {
            users.add(UserDto.builder()
                    .id(it)
                    .name(it+"user")
                    .email(it+"user@lulu.lo")
                    .token("=)(Y=HGTV")
                    .created(LocalDate.now())
                    .isActive(true).build())
        }
        userService.findAll() >> users
        when:
        userController.users()
        then:
        notThrown()
    }

    def "get User details for id"() {
        given:
        UserDto user = UserDto.builder()
                .id(1)
                .name("user")
                .email("user@lulu.lo")
                .token("=)(Y=HGTV")
                .created(LocalDate.now())
                .isActive(true)
                .build()
        userService.findById(_ as Long) >> user
        when:
        userController.user(1)
        then:
        notThrown()
    }

    def "Save user correct fields"() {
        given:
        UserDto user = UserDto.builder()
                .id(1)
                .name("user")
                .email("user@lulu.lo")
                .token("=)(Y=HGTV")
                .created(LocalDate.now())
                .isActive(true)
                .build()
        userService.userSave(_ as UserDto) >> user
        when:
        userController.userSave(user)
        then:
        notThrown()
    }

    def "Update user correct fields"() {
        given:
        UserDto user = UserDto.builder()
                .id(1)
                .name("user")
                .email("user@lulu.lo")
                .token("=)(Y=HGTV")
                .created(LocalDate.now())
                .isActive(true)
                .build()
        userService.update(_ as UserDto) >> user
        when:
        def res = userController.userUpdate(user)
        def body = res.getBody()
        then:
        res instanceof ResponseEntity
        res.statusCode == HttpStatus.OK
        body instanceof UserDto
    }

    def "Delete user use DTO"() {
        given:
        userService.deleteById(_) >> { }
        when:
        def res = userController.userDelete(1)
        then:
        res instanceof ResponseEntity
        res.statusCode == HttpStatus.OK
    }

}
