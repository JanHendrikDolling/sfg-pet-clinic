package it.dolling.tryout.sfgpetclinic.bootstrap;

import it.dolling.tryout.sfgpetclinic.model.*;
import it.dolling.tryout.sfgpetclinic.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final AddressService addressService;
    private final ContactInformationService contactInformationservice;
    private final PetService petService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, AddressService addressService, ContactInformationService contactInformationservice, PetService petService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.addressService = addressService;
        this.contactInformationservice = contactInformationservice;
        this.petService = petService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) {
        final int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    @SuppressWarnings("unused")
    private void loadData() {
        PetType dog = createPetType("Dog");
        PetType cat = createPetType("Cat");
        LOGGER.info("Loaded PetTypes...");

        Speciality radiology = getSpeciality("Radiology");
        Speciality surgery = getSpeciality("Surgery");
        Speciality dentistry = getSpeciality("Dentistry");
        LOGGER.info("Loaded Specialities...");

        Address hamburg = createAddress("street hh", "HH");
        Address berlin = createAddress("street Berlin", "Berlin");
        LOGGER.info("Loaded Addresses...");

        ContactInformation michaelContactInfo = createContactInformation(hamburg, "1223");
        ContactInformation fionaContactInfo = createContactInformation(berlin, "45678");
        LOGGER.info("Loaded ContactInformations...");

        Pet rosco = createPet(dog, "Rosco", LocalDate.now());
        Pet selly = createPet(cat, "Selly", LocalDate.now());
        LOGGER.info("Create Pets...");

        Owner michael = createOwner(michaelContactInfo, "Michael", "Weston", Stream.of(rosco, selly).collect(Collectors.toSet()));
        Owner fiona = createOwner(fionaContactInfo, "Fiona", "Glenanne");
        LOGGER.info("Loaded Owners...");

        Pet catty = createPet(cat, "Catty", LocalDate.now());
        catty.setOwner(fiona);
        petService.save(catty);
        LOGGER.info("Loaded Pets...");

        Vet sam = createVet("Sam", "Axe", Stream.of(radiology).collect(Collectors.toSet()));
        Vet jessie = createVet("Jessie", "Porter", Stream.of(surgery).collect(Collectors.toSet()));
        LOGGER.info("Loaded Vets...");


        Visit catVisit = new Visit();
        catVisit.setPet(catty);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        visitService.save(catVisit);
        LOGGER.info("Loaded Visits...");
    }

    private Speciality getSpeciality(String radiology) {
        Speciality speciality = new Speciality();
        speciality.setDescription(radiology);
        return specialityService.save(speciality);
    }


    private Pet createPet(PetType dog, String name, LocalDate birthDate) {
        Pet pet = new Pet();
        pet.setPetType(dog);
        pet.setBirthDate(birthDate);
        pet.setName(name);
        return pet;
    }

    private Vet createVet(String firstName, String lastName, Set<Speciality> specialities) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.setSpecialities(specialities);
        return vetService.save(vet);
    }

    private Owner createOwner(ContactInformation contactInformation, String firstName, String lastName) {
        return createOwner(contactInformation, firstName, lastName, Collections.emptySet());
    }

    private Owner createOwner(ContactInformation contactInformation, String firstName, String lastName, Set<Pet> pets) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setContactInformation(contactInformation);
        owner.setPets(pets);
        pets.forEach(pet -> pet.setOwner(owner));
        return ownerService.save(owner);
    }

    private ContactInformation createContactInformation(Address address, String telephon) {
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setAddress(address);
        contactInformation.setTelephone(telephon);
        return contactInformationservice.save(contactInformation);
    }

    private Address createAddress(String street, String city) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        return addressService.save(address);
    }

    private PetType createPetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);
        return petTypeService.save(petType);
    }
}
