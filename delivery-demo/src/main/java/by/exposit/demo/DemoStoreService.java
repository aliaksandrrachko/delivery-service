package by.exposit.demo;

import by.exposit.delivery.api.services.IStoreService;
import by.exposit.delivery.api.utils.IDemo;
import by.exposit.delivery.core.annotations.InjectByType;
import by.exposit.delivery.entities.Store;
import by.exposit.delivery.entities.metadata.Address;
import by.exposit.delivery.entities.metadata.PhoneNumber;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DemoStoreService implements IDemo {

    @InjectByType
    private IStoreService storeService;

    public void demo() {
        log.info("Find all stores: '{}' stores found.", storeService.findAll().size());

        Store createdStore = storeService.create(getTestStore());
        log.info("Create store: {}.", createdStore);
        log.info("Find created store by id='{}': {}.", createdStore.getId(), storeService.findById(createdStore.getId()));

        Store storeForUpdate = createdStore;
        storeForUpdate.setEmail("newStoreEmail@email.ru");
        log.info("Store for update: {}.", storeForUpdate);
        Store updatedStore = storeService.update(storeForUpdate.getId(), storeForUpdate);
        log.info("Get updated store by id='{}': {}.", updatedStore.getId(), storeService.findById(updatedStore.getId()));

        log.info("Delete created store with id='{}'.", updatedStore.getId());
        storeService.delete(updatedStore.getId());
        log.info("Find deleted store by id='{}' :{}", updatedStore.getId(), storeService.findById(updatedStore.getId()));

    }

    private Store getTestStore() {
        return Store.builder()
                .email("superStore@mail.ru")
                .name("Some")
                .phoneNumber(PhoneNumber.builder().countryCode("375").nationalNumber("123456789").build())
                .address(Address.builder()
                        .apartmentNumber("14")
                        .cityName("Hrodna")
                        .country("Belarus")
                        .district("Hrodna")
                        .streetName("safd")
                        .build())
                .additionalInfo(new HashMap<>(Map.of("reviews", "14", "clients", "208")))
                .build();
    }
}
