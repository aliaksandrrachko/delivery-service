package by.exposit.delivery.dao;

import by.exposit.delivery.FileNameConstants;
import by.exposit.delivery.api.dao.IClientDao;
import by.exposit.delivery.core.annotations.Singleton;
import by.exposit.delivery.entities.Client;

@Singleton
public class ClientDao extends AGenericJsonFileDao<Client, Long> implements IClientDao {

    public ClientDao() {
        super(Client.class, FileNameConstants.CLIENT_FOLDER_PATH);
        super.initCacheMethod();
    }
}
