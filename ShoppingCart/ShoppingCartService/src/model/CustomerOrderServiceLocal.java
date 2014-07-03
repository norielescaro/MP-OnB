package model;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CustomerOrderServiceLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    CustomerOrder persistCustomerOrder(CustomerOrder customerOrder);

    CustomerOrder mergeCustomerOrder(CustomerOrder customerOrder);

    void removeCustomerOrder(CustomerOrder customerOrder);

    List<CustomerOrder> getCustomerOrderFindAll();
}
