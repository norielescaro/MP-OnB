package model;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CustomerOrderService {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    CustomerOrder persistCustomerOrder(CustomerOrder customerOrder);

    CustomerOrder mergeCustomerOrder(CustomerOrder customerOrder);

    void removeCustomerOrder(CustomerOrder customerOrder);

    List<CustomerOrder> getCustomerOrderFindAll();
    
    List<CustomerOrder> getCustomerOrderByCustomer(Customer customer);
}
