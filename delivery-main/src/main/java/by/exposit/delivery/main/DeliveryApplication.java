package by.exposit.delivery.main;

import by.exposit.delivery.dao.ClientDao;
import by.exposit.delivery.entities.Client;
import by.exposit.delivery.entities.metadata.Address;
import by.exposit.delivery.entities.metadata.Gender;
import by.exposit.delivery.entities.metadata.PhoneNumber;
import by.exposit.service.services.ClientService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class DeliveryApplication {
    public static void main(String[] args) {
        ClientService service = new ClientService(new ClientDao());
        List<Client> clients = service.findAll();
        log.info("Find all clients:{}", clients.toString());
        Client foundedClient = service.findById(1L);
        log.info("Find client with id:1, {}", foundedClient.toString());
        Client newClient = Client.builder()
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
        Client createdClient = service.create(newClient);
        log.info("Created client: {}", createdClient.toString());
        createdClient.setEmail("CHANGEDEMAIL@mail.ru");
        Client updatedClient = service.update(createdClient.getId(), createdClient);
        log.info("Updated client: {}", updatedClient);
        service.delete(updatedClient.getId());
        log.info("Find deleted client: {}", service.findById(updatedClient.getId()));
    }
}
