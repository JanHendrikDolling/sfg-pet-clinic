package it.dolling.tryout.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @OneToOne
    private ContactInformation contactInformation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets;

    @Builder
    public Owner(String firstName, String lastName, ContactInformation contactInformation, Set<Pet> pets) {
        super(firstName, lastName);
        this.contactInformation = contactInformation;
        this.pets = pets;
    }

    boolean isValid() {
        return getId() != null;
    }
}
