package it.dolling.tryout.sfgpetclinic.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "owners")
public class Owner extends Person {

    @OneToOne(cascade = CascadeType.ALL)
    private ContactInformation contactInformation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets;

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
