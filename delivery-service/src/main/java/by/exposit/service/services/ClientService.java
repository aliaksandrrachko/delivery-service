package by.exposit.service.services;

import by.exposit.delivery.api.dao.IClientDao;
import by.exposit.delivery.api.services.IClientService;
import by.exposit.delivery.core.annotations.InjectByType;
import by.exposit.delivery.core.annotations.Singleton;
import by.exposit.delivery.entities.Client;

import java.util.List;

@Singleton
public class ClientService implements IClientService {

    @InjectByType
    private IClientDao clientDao;

    @Override
    public List<Client> findAll() {
        return clientDao.getAll();
    }

    @Override
    public Client findById(Long id) {
        return clientDao.get(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        clientDao.delete(id);
    }

    @Override
    public Client create(Client entity) {
        Long lastKey = clientDao.getLastKey();
        lastKey = lastKey == null ? 1L : lastKey + 1;
        entity.setId(lastKey);
        return clientDao.create(entity).orElse(null);
    }

    @Override
    public Client update(Long id, Client entity) {
        return clientDao.update(id, entity).orElse(null);
    }
}
