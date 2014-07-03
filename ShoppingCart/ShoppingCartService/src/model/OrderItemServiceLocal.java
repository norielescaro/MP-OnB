package model;

import java.util.List;

import javax.ejb.Local;

@Local
public interface OrderItemServiceLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    OrderItem persistOrderItem(OrderItem orderItem);

    OrderItem mergeOrderItem(OrderItem orderItem);

    void removeOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemFindAll();
}
