package com.shopping.cart.view;

import com.shopping.cart.view.helper.ServiceProvider;

import model.Category;

public class CategoryForm {
    private String categoryName;
    private String categoryDescription;

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public String submitCommand() {
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryDescription(categoryDescription);
        ServiceProvider.getCategoryService().persistCategory(category);
        return "done";
    }
}
