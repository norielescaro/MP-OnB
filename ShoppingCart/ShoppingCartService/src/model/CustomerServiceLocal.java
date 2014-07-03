package model;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CustomerServiceLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Customer persistCustomer(Customer customer);

    Customer mergeCustomer(Customer customer);

    void removeCustomer(Customer customer);

    List<Customer> getCustomerFindAll();
}
