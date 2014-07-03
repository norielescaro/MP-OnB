package model;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CustomerService {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Customer persistCustomer(Customer customer);

    Customer mergeCustomer(Customer customer);

    void removeCustomer(Customer customer);

    List<Customer> getCustomerFindAll();
    
    Customer getCustomer();
    
    List<Customer> findCustomerByCriteria(String jpqlStmt, int first, int last);
}
