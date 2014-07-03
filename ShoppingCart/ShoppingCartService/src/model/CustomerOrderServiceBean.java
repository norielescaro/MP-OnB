package model;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "CustomerOrderService", mappedName = "ShoppingCart-ShoppingCartService-CustomerOrderService")
public class CustomerOrderServiceBean implements CustomerOrderService,
                                                 CustomerOrderServiceLocal {
    @PersistenceContext(unitName="ShoppingCartService")
    private EntityManager em;

    public CustomerOrderServiceBean() {
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

    public CustomerOrder persistCustomerOrder(CustomerOrder customerOrder) {
        em.persist(customerOrder);
        return customerOrder;
    }

    public CustomerOrder mergeCustomerOrder(CustomerOrder customerOrder) {
        return em.merge(customerOrder);
    }

    public void removeCustomerOrder(CustomerOrder customerOrder) {
        customerOrder = em.find(CustomerOrder.class, customerOrder.getOrderId());
        em.remove(customerOrder);
    }

    /** <code>select o from CustomerOrder o</code> */
    public List<CustomerOrder> getCustomerOrderFindAll() {
        return em.createNamedQuery("CustomerOrder.findAll").getResultList();
    }

    public List<CustomerOrder> getCustomerOrderByCustomer(Customer customer) {
        Query query = em.createNamedQuery("findOrderByCustomer");
        query.setParameter("customer", customer);
        return query.getResultList();
    }
}
