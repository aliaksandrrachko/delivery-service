package by.exposit.delivery.dao;

import by.exposit.delivery.FileNameConstants;
import by.exposit.delivery.api.dao.IStoreDao;
import by.exposit.delivery.entities.Store;

public class StoreDao extends AGenericJsonFileDao<Store, Long> implements IStoreDao {

    public StoreDao() {
        super(Store.class, FileNameConstants.STORE_FOLDER_PATH);
        super.initCacheMethod();
    }
}
