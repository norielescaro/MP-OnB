package model;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "CategoryService",
           mappedName = "ShoppingCart-ShoppingCartService-CategoryService")
public class CategoryServiceBean implements CategoryService,
                                            CategoryServiceLocal {
    @PersistenceContext(unitName = "ShoppingCartService")
    private EntityManager em;

    public CategoryServiceBean() {
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

    public Category persistCategory(Category category) {
        em.persist(category);
        return category;
    }

    public Category mergeCategory(Category category) {
        return em.merge(category);
    }

    public void removeCategory(Category category) {
        category = em.find(Category.class, category.getCategoryId());
        em.remove(category);
    }

    /** <code>select o from Category o</code> */
    public List<Category> getCategoryFindAll() {
        return em.createNamedQuery("Category.findAll").getResultList();
    }

    public List<Category> findCategoryByCriteria(String jpqlStmt,
                                                 int firstResult,
                                                 int maxResult) {
        return (List<Category>)queryByRange(jpqlStmt, firstResult, maxResult);
    }
}
