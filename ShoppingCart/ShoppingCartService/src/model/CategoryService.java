package model;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CategoryService {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Category persistCategory(Category category);

    Category mergeCategory(Category category);

    void removeCategory(Category category);

    List<Category> getCategoryFindAll();
    
    List<Category> findCategoryByCriteria(String jpqlStmt,int firstResult,int maxResult);
}
