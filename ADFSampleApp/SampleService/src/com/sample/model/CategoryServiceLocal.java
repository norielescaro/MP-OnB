package com.sample.model;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CategoryServiceLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Category persistCategory(Category category);

    Category mergeCategory(Category category);

    void removeCategory(Category category);

    List<Category> getCategoryFindAll();
}
