package by.exposit.service.services;

import by.exposit.delivery.api.dao.IStoreDao;
import by.exposit.delivery.api.services.IStoreService;
import by.exposit.delivery.core.annotations.InjectByType;
import by.exposit.delivery.core.annotations.Singleton;
import by.exposit.delivery.entities.Store;

import java.util.List;

@Singleton
public class StoreService implements IStoreService {

    @InjectByType
    private IStoreDao storeDao;

    @Override
    public List<Store> findAll() {
        return storeDao.getAll();
    }

    @Override
    public Store findById(Long id) {
        return storeDao.get(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        storeDao.delete(id);
    }

    @Override
    public Store create(Store entity) {
        Long lastKey = storeDao.getLastKey();
        lastKey = lastKey == null ? 1L : lastKey + 1;
        entity.setId(lastKey);
        return storeDao.create(entity).orElse(null);
    }

    @Override
    public Store update(Long id, Store entity) {
        return storeDao.update(id, entity).orElse(null);
    }
}
