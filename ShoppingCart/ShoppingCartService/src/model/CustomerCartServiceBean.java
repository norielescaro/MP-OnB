package model;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "CustomerCartService", mappedName = "ShoppingCart-ShoppingCartService-CustomerCartService")
public class CustomerCartServiceBean implements CustomerCartService,
                                                CustomerCartServiceLocal {
    @PersistenceContext(unitName="ShoppingCartService")
    private EntityManager em;

    public CustomerCartServiceBean() {
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

    public CustomerCart persistCustomerCart(CustomerCart customerCart) {
        em.persist(customerCart);
        return customerCart;
    }

    public CustomerCart mergeCustomerCart(CustomerCart customerCart) {
        return em.merge(customerCart);
    }

    public void removeCustomerCart(CustomerCart customerCart) {
        customerCart = em.find(CustomerCart.class, customerCart.getCartId());
        em.remove(customerCart);
    }

    /** <code>select o from CustomerCart o</code> */
    public List<CustomerCart> getCustomerCartFindAll() {
        return em.createNamedQuery("CustomerCart.findAll").getResultList();
    }
}
