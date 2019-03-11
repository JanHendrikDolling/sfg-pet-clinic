package it.dolling.tryout.sfgpetclinic.model;

public class Speciality extends BaseEntity<Long> {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


