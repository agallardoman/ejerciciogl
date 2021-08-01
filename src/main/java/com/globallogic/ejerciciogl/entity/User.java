package com.globallogic.ejerciciogl.entity;

import com.globallogic.ejerciciogl.dto.PhonesDto;
import com.globallogic.ejerciciogl.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany
    private Set<Phone> phones = new HashSet<>();

    public UserDto toDTO() {
        UserDto userDto = new UserDto();

        return userDto;

    }

}
