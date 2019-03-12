package it.dolling.tryout.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Address extends BaseEntity {

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
