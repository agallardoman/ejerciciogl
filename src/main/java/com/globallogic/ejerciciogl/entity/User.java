package com.globallogic.ejerciciogl.entity;

import com.globallogic.ejerciciogl.dto.PhonesDto;
import com.globallogic.ejerciciogl.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String token;

    private LocalDate created;

    private LocalDate modified;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Phone> phones = new HashSet<>();

    @PrePersist
    private void prePersist(){
        this.created=LocalDate.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.modified=LocalDate.now();
    }


}
