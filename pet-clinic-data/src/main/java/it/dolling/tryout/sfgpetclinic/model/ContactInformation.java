package it.dolling.tryout.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="ContactInformations")
public class ContactInformation extends BaseEntity {

    @OneToOne
    private Address address;

    @Column(name = "telefone")
    private String telephone;

}
