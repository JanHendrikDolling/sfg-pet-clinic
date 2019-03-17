package it.dolling.tryout.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address extends BaseEntity {

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

}
