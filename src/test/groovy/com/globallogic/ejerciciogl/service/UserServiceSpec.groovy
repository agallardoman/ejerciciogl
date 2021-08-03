package com.globallogic.ejerciciogl.service

import com.globallogic.ejerciciogl.dto.UserDto
import com.globallogic.ejerciciogl.entity.User
import com.globallogic.ejerciciogl.repository.UserRepository
import org.modelmapper.ModelMapper
import spock.lang.Specification

import java.time.LocalDate

class UserServiceSpec extends Specification {
    UserService userService
    UserRepository userRepository = Mock(UserRepository)
    ModelMapper mapper = new ModelMapper()

    def setup(){
        userService = new UserServiceImpl(userRepository, mapper)
    }
    def "UserSave"() {
        given:
        UserDto user = UserDto.builder()
                .id(1)
                .name("user")
                .email("user@lulu.lo")
                .token("=)(Y=HGTV")
                .created(LocalDate.now())
                .isActive(true)
                .build()
        userRepository.save(_)>>mapper.map(user, User.class)
        when:
        userService.userSave(user)

        then:
        notThrown()
    }

    def "Update"() {
        given:
        UserDto user = UserDto.builder()
                .id(1)
                .name("user")
                .email("user@lulu.lo")
                .token("=)(Y=HGTV")
                .created(LocalDate.now())
                .isActive(true)
                .build()
        userRepository.save(_)>>mapper.map(user, User.class)
        when:
        userService.update(user)

        then:
        notThrown()
    }

    def "FindAll"() {
        given:
        UserDto user = UserDto.builder()
                .id(1)
                .name("user")
                .email("user@lulu.lo")
                .token("=)(Y=HGTV")
                .created(LocalDate.now())
                .isActive(true)
                .build()
        User userEntity = mapper.map(user, User.class)
        List<User> userList = new ArrayList<>()
        userList.add(userEntity)
        userRepository.findAll()>>userList
        when:
        userService.findAll()

        then:
        notThrown()
    }

    def "DeleteById"() {
        given:
        UserDto user = UserDto.builder()
                .id(1)
                .name("user")
                .email("user@lulu.lo")
                .token("=)(Y=HGTV")
                .created(LocalDate.now())
                .isActive(true)
                .build()
        userRepository.deleteById(_)>>{ }
        when:
        userService.deleteById(1)
        then:
        notThrown()
    }

    def "FindById"() {
        given:
        User user = User.builder()
                .id(1)
                .name("user")
                .email("user@lulu.lo")
                .token("=)(Y=HGTV")
                .created(LocalDate.now())
                .isActive(true)
                .build()
        userRepository.findById(_ as Long)>>Optional.of(user)
        when:
        userService.findById(1)
        then:
        notThrown()
    }
}
