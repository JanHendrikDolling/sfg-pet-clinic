package it.dolling.tryout.sfgpetclinic.bootstrap;

import it.dolling.tryout.sfgpetclinic.model.Owner;
import it.dolling.tryout.sfgpetclinic.model.Vet;
import it.dolling.tryout.sfgpetclinic.services.OwnerService;
import it.dolling.tryout.sfgpetclinic.services.VetService;
import it.dolling.tryout.sfgpetclinic.services.map.OwnerServiceMap;
import it.dolling.tryout.sfgpetclinic.services.map.VetServiceMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner michael = new Owner();
        michael.setId(1L);
        michael.setFirstName("Michael");
        michael.setLastName("Weston");
        ownerService.save(michael);

        Owner fiona = new Owner();
        fiona.setId(2L);
        fiona.setFirstName("Fiona");
        fiona.setLastName("Glenanne");
        ownerService.save(fiona);

        LOGGER.info("Loaded Owners...");

        Vet sam = new Vet();
        sam.setId(1L);
        sam.setFirstName("Sam");
        sam.setLastName("Axe");
        vetService.save(sam);

        Vet jessie = new Vet();
        jessie.setId(2L);
        jessie.setFirstName("Jessie");
        jessie.setLastName("Porter");
        vetService.save(jessie);

        LOGGER.info("Loaded Vets...");
    }
}
