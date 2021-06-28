package by.exposit.delivery.dao;

import by.exposit.delivery.FileNameConstants;
import by.exposit.delivery.api.dao.IOrderDao;
import by.exposit.delivery.entities.Order;

public class OrderDao extends AGenericJsonFileDao<Order, Long> implements IOrderDao {

    public OrderDao() {
        super(Order.class, FileNameConstants.ORDER_FOLDER_PATH);
        super.initCacheMethod();
    }
}
