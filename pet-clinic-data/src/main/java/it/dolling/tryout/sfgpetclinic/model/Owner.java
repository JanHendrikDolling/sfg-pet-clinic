package it.dolling.tryout.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @OneToOne(cascade = CascadeType.REMOVE)
    private ContactInformation contactInformation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets;

    @Builder
    public Owner(Long id, String firstName, String lastName, ContactInformation contactInformation, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.contactInformation = contactInformation;
        this.pets = pets;
    }

    boolean isInvalid() {
        return getId() == null;
    }
}
