package com.globallogic.ejerciciogl.entity;

import com.globallogic.ejerciciogl.dto.PhonesDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="phone")
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long number;

    private Long citycod;

    private Long contrycode;

    @ManyToOne
    private User user;

    public PhonesDto toDTO(){
        PhonesDto phonesDto = new PhonesDto();
        return phonesDto;
    }

}
