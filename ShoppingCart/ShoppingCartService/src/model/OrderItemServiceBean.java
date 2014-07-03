package model;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "OrderItemService", mappedName = "ShoppingCart-ShoppingCartService-OrderItemService")
public class OrderItemServiceBean implements OrderItemService,
                                             OrderItemServiceLocal {
    @PersistenceContext(unitName="ShoppingCartService")
    private EntityManager em;

    public OrderItemServiceBean() {
    }

    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public OrderItem persistOrderItem(OrderItem orderItem) {
        em.persist(orderItem);
        return orderItem;
    }

    public OrderItem mergeOrderItem(OrderItem orderItem) {
        return em.merge(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItem = em.find(OrderItem.class, orderItem.getOrderItemId());
        em.remove(orderItem);
    }

    /** <code>select o from OrderItem o</code> */
    public List<OrderItem> getOrderItemFindAll() {
        return em.createNamedQuery("OrderItem.findAll").getResultList();
    }
}
