package model;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ProductService {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Product persistProduct(Product product);

    Product mergeProduct(Product product);

    void removeProduct(Product product);

    List<Product> getProductFindAll();
    
    List<Product> findProductByCriteria(String jpqlStmt, int first, int last);
    
    Product findProductById(Long id);
}
