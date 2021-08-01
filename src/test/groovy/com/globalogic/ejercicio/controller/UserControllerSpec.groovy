package com.globalogic.ejercicio.controller

import com.globallogic.ejerciciogl.controller.UserController
import com.globallogic.ejerciciogl.dto.PhonesDto
import com.globallogic.ejerciciogl.dto.UserDto
import com.globallogic.ejerciciogl.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class UserControllerSpec extends Specification {

    UserController userController
    UserService userService = Stub(UserService)

    def setup(){
        userController = new UserController(userService)
    }

    def "get all users"() {
        given:
        List<UserDto> users = new ArrayList<>()
        50.times {
            users.add(new UserDto(it, "","","","", new Date(), new Date(), new Date(), true, [] as Set<PhonesDto>))
        }
        userService.findAll() >> users
        when:
        def res = userController.list()
        List<UserDto> usersList = res.getBody() as List<UserDto>
        then:
        res instanceof ResponseEntity
        usersList.size() == 50
    }

    def "get User details for id"() {
        given:
        UserDto user = new UserDto(1, "","","","", new Date(), new Date(), new Date(), true, [] as Set<PhonesDto>)
        userService.findById(_ as Long) >> user
        when:
        def res = userController.getUserById(1)
        def userFound = res.getBody() as UserDto
        then:
        res instanceof ResponseEntity
        userFound instanceof UserDto
        userFound.id == 1
    }

    def "Save user correct fields"() {
        given:
        UserDto userFields = new UserDto(0, "Juan Rodriguez","Hunter22","juan@rodriguez.com",null, null, null, null, true, [] as Set<PhonesDto>)
        UserDto userSaved = new UserDto(1, "Juan Rodriguez","Hunter22","juan@rodriguez.com","", new Date(), null, null, true, [] as Set<PhonesDto>)
        userService.save(_ as UserDto) >> userSaved
        when:
        def res = userController.save(userFields)
        def body = res.getBody()
        then:
        res instanceof ResponseEntity
        res.statusCode == HttpStatus.CREATED
        body instanceof UserDto
    }

    def "Update user correct fields"() {
        given:
        UserDto userFields = new UserDto(1, "Juan Rodriguez","Hunter22","juan@rodriguez.com","", new Date(), null, null, true, [] as Set<PhonesDto>)
        UserDto userUpdated = new UserDto(1, "Juan Rodriguez","Hunter22","juan@rodriguez.com","", new Date(), new Date(), null, true, [] as Set<PhonesDto>)
        userService.save(_ as UserDto) >> userUpdated
        when:
        def res = userController.update(userFields)
        def body = res.getBody()
        then:
        res instanceof ResponseEntity
        res.statusCode == HttpStatus.OK
        body instanceof UserDto
    }

    def "Delete user use DTO"() {
        given:
        UserDto user = new UserDto(1, "Juan Rodriguez","Hunter22","juan@rodriguez.com","", new Date(), new Date(), null, true, [] as Set<PhonesDto>)
        userService.delete(_ as UserDto) >> null
        when:
        def res = userController.delete(user)
        then:
        res instanceof ResponseEntity
        res.statusCode == HttpStatus.OK
    }

    def "Delete user use ID"() {
        given:
        userService.deleteById(_ as Long) >> null
        when:
        def res = userController.deleteById(1)
        then:
        res instanceof ResponseEntity
        res.statusCode == HttpStatus.OK
    }
}
