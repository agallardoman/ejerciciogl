package com.globallogic.ejerciciogl.entity;

import com.globallogic.ejerciciogl.dto.PhonesDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone implements Serializable {
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long number;

    private Long citycode;

    private Long contrycode;

    @ManyToOne
    private User user;

}
