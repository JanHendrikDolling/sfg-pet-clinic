package it.dolling.tryout.sfgpetclinic.model;

import javax.persistence.*;

@Entity
@Table(name ="ContactInformations")
public class ContactInformation extends BaseEntity {

    @OneToOne
    private Address address;

    @Column(name = "telefone")
    private String telephone;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
