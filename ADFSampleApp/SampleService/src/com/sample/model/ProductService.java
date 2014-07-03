package com.sample.model;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ProductService {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Product persistProduct(Product product);

    Product mergeProduct(Product product);

    void removeProduct(Product product);

    List<Product> findAllProduct();
}
