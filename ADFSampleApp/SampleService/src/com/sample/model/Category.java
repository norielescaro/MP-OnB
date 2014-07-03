package com.sample.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries({
  @NamedQuery(name = "Category.findAll", query = "select o from Category o")
})
@SequenceGenerator(sequenceName = "CATEGORY_SEQUENCE", name = "categorySequence", allocationSize = 1)
public class Category implements Serializable {
    @Column(name="CATEGORY_DESCRPTION", length = 50)
    private String categoryDescription;
    @Id
    @GeneratedValue(generator = "categorySequence", strategy = GenerationType.SEQUENCE)
    @Column(name="CATEGORY_ID", nullable = false)
    private Long categoryId;
    @Column(name="CATEGORY_NAME", length = 30)
    private String categoryName;
    @OneToMany(mappedBy="category", fetch=FetchType.EAGER)
    private List<Product> products;

    public Category() {
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
