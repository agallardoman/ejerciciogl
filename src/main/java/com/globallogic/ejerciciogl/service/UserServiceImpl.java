package com.globallogic.ejerciciogl.service;

import com.globallogic.ejerciciogl.dto.UserDto;
import com.globallogic.ejerciciogl.entity.User;
import com.globallogic.ejerciciogl.repository.UserRepository;
import com.globallogic.ejerciciogl.utils.DTOMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    final ModelMapper mapper;

    @Override
    public UserDto userSave(UserDto userDto) {

        User user1 = mapper.map(userDto, User.class);
        return mapper.map(userRepository.save(user1), UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user2 = mapper.map(userDto, User.class);
        return mapper.map(userRepository.save(user2), UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(User::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElse(new User());
        return user.toDTO();
    }
    @Override
    public void deleteById(Long id) {
        userRepository.findById(id);
    }
    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(new User());
        return user.toDTO();
    }

    @Override
    public Map<String, Object> getDetails(String email) {
        Map<String, Object> details = new HashMap<>();
        UserDetails userDetails = loadUserByUsername(email);
        if (userDetails != null) {
            UserDto userDto = findByEmail(email);
            details.put("user", userDto);
            details.put("userDetails", userDetails);
        }
        return details;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = DTOMapper.toEntity(findByEmail(email));
        if (user.getEmail() == null) {
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),email , emptyList());
    }
}
