package com.globalogic.ejercicio.service

import com.globallogic.ejerciciogl.dto.UserDto
import com.globallogic.ejerciciogl.entity.Phone
import com.globallogic.ejerciciogl.entity.User
import com.globallogic.ejerciciogl.repository.UserRepository
import com.globallogic.ejerciciogl.service.UserServiceImpl
import spock.lang.Specification

class UserServiceSpec extends Specification {

    UserServiceImpl userService
    UserRepository userRepository = Stub(UserRepository)

    def setup(){
        userService = new UserServiceImpl(userRepository, passwordService)
    }

    def "FindAll"() {
        given:
        List<User> users = userListData()
        userRepository.findAll() >> users
        when:
        def res = userService.findAll()
        then:
        res instanceof List<UserDto>
        res.size() == 3
    }

    def "Save"() {
        given:
        UserDto userDTO = userDTOData()
        userRepository.save(_) >> userData()
        when:
        def res = userService.save(userDTO)
        then:
        res instanceof UserDto
    }

    def "Update"() {
        given:
        UserDto userDTO = userDTOData()
        userRepository.save(_) >> userData()
        when:
        def res = userService.update(UserDto)
        then:
        res instanceof UserDto
    }

    def "FindById"() {
        given:
        Long id = 1
        Optional<User> optionalUser = Optional.of(userEntityData())
        userRepository.findById(_) >> optionalUser

        when:
        def res = userService.findById(id)

        then:
        res instanceof UserDto
    }

    def "Delete"() {
        given:
        UserDto userDTO = userDTOData()
        userRepository.delete(_)
        when:
        def res = userService.delete(userDTO)
        then:
        res == null
    }

    def "DeleteById"() {
        when:
        def res = userRepository.deleteById(_)
        then:
        res == null
    }

    User userEntityData(){
         return new User(1, "","","","", new Date(), new Date(), new Date(), true, [] as Set<Phone>)
    }

    UserDto userDTOData(){
        UserDto userDTO = new UserDto()
        userDTO.id = 1
        userDTO.name = "name"
        userDTO.password = "password"
        userDTO.phones = []
        return userDTO
    }

    User userData(){
        new User(1, "","","", "", new Date(), new Date(), new Date(), true, [] as Set<Phone>)
    }

    List<User> userListData(){
        List<User> list = new ArrayList<>()
        3.times {
            list.add(new User(it, "","","","", new Date(), new Date(), new Date(), true, [] as Set<Phone>))
        }
        return list
    }
}
