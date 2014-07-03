package model;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CustomerCartServiceLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    CustomerCart persistCustomerCart(CustomerCart customerCart);

    CustomerCart mergeCustomerCart(CustomerCart customerCart);

    void removeCustomerCart(CustomerCart customerCart);

    List<CustomerCart> getCustomerCartFindAll();
}
