package model;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface OrderItemService {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    OrderItem persistOrderItem(OrderItem orderItem);

    OrderItem mergeOrderItem(OrderItem orderItem);

    void removeOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemFindAll();
}
