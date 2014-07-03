package com.sample.model;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "ProductService", mappedName = "ADFSampleApp-SampleService-ProductService")
public class ProductServiceBean implements ProductService,
                                           ProductServiceLocal {
    @PersistenceContext(unitName="SampleService")
    private EntityManager em;

    public ProductServiceBean() {
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

    public Product persistProduct(Product product) {
        em.persist(product);
        return product;
    }

    public Product mergeProduct(Product product) {
        return em.merge(product);
    }

    public void removeProduct(Product product) {
        product = em.find(Product.class, product.getProductId());
        em.remove(product);
    }

    /** <code>select o from Product o</code> */
    public List<Product> findAllProduct() {
        return em.createNamedQuery("findAllProduct").getResultList();
    }
}
