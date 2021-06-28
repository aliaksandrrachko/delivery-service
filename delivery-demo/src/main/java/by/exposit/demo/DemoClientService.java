package by.exposit.demo;

import by.exposit.delivery.api.services.IClientService;
import by.exposit.delivery.api.utils.IDemo;
import by.exposit.delivery.core.annotations.InjectByType;
import by.exposit.delivery.entities.Client;
import by.exposit.delivery.entities.metadata.Address;
import by.exposit.delivery.entities.metadata.Gender;
import by.exposit.delivery.entities.metadata.PhoneNumber;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class DemoClientService implements IDemo {

    @InjectByType
    private IClientService clientService;

    public void demo() {
        log.info("Find all clients: '{}' clients found.", clientService.findAll().size());

        final Client clientForCreating = getTestClient();
        log.info("Create client: {}.", clientForCreating);
        Client createdClient = clientService.create(clientForCreating);
        log.info("Find created client by id='{}': {}.", createdClient.getId(), clientService.findById(createdClient.getId()));

        log.info("Client for creating and created client equals: {}", clientForCreating.equals(createdClient));

        createdClient.setEmail("newClientEmail@email.ru");
        log.info("Update created client: {}.", createdClient);
        createdClient = clientService.update(createdClient.getId(), createdClient);
        log.info("Updated client: {}", createdClient);

        log.info("Delete client by id='{}.", createdClient.getId());
        clientService.delete(createdClient.getId());
        log.info("Find deleted client with id='{}': {}", createdClient.getId(), clientService.findById(createdClient.getId()));
    }

    private Client getTestClient() {
        return Client.builder()
                .email("superClient@mail.ru")
                .firstName("Bill")
                .lastName("Some")
                .middleName("Who")
                .phoneNumber(PhoneNumber.builder().countryCode("375").nationalNumber("123456789").build())
                .address(Address.builder()
                        .apartmentNumber("14")
                        .cityName("Hrodna")
                        .country("Belarus")
                        .district("Hrodna")
                        .streetName("safd")
                        .build())
                .gender(Gender.MALE)
                .birthday(LocalDate.now())
                .build();
    }
}

