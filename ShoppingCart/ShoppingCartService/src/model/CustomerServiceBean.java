package model;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "CustomerService", mappedName = "ShoppingCart-ShoppingCartService-CustomerService")
public class CustomerServiceBean implements CustomerService,
                                            CustomerServiceLocal {
    @PersistenceContext(unitName="ShoppingCartService")
    private EntityManager em;
    
    @Resource
    private SessionContext context;

    public CustomerServiceBean() {
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
    
    public Customer getCustomer(){
        Customer activeCustomer = null;
        long id = 0;
        if(context.getCallerPrincipal().getName().equals("nori")){
            id = 2;
        }else if(context.getCallerPrincipal().getName().equals("noriel")){
            id = 3;
        }
        for(Customer customer : getCustomerFindAll()){
            if(customer.getCustomerId() == id){
                activeCustomer = customer;
                break;
            }
        }
        return activeCustomer;
    }

    public Customer persistCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

    public Customer mergeCustomer(Customer customer) {
        return em.merge(customer);
    }

    public void removeCustomer(Customer customer) {
        customer = em.find(Customer.class, customer.getCustomerId());
        em.remove(customer);
    }

    /** <code>select o from Customer o</code> */
    public List<Customer> getCustomerFindAll() {
        return em.createNamedQuery("Customer.findAll").getResultList();
    }

    public List<Customer> findCustomerByCriteria(String jpqlStmt, int first,
                                                 int last) {
        Query query = em.createQuery(jpqlStmt);
        query.setFirstResult(first);
        query.setMaxResults(last);
        return query.getResultList();
    }
}
