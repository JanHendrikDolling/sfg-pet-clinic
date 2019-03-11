package it.dolling.tryout.sfgpetclinic.model;

import java.util.Set;

public class Owner extends Person {

    private ContactInformation contactInformation;
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
