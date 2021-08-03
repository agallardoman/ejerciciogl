package com.globallogic.ejerciciogl.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String token;
    private LocalDate created;
    private LocalDate modified;
    private boolean isActive;
    private Set<PhonesDto> phones = new HashSet<>();

}
