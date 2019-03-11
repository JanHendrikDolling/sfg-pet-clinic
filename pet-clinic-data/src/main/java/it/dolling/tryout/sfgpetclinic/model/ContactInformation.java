package it.dolling.tryout.sfgpetclinic.model;

public class ContactInformation extends BaseEntity {

    private Address address;
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
