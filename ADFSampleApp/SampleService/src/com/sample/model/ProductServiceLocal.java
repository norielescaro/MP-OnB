package com.sample.model;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ProductServiceLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Product persistProduct(Product product);

    Product mergeProduct(Product product);

    void removeProduct(Product product);

    List<Product> findAllProduct();
}
