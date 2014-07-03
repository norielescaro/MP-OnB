package com.sample.model;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "findAllProduct", query = "select o from Product o")
})
public class Product implements Serializable {
    private BigDecimal price;
    @Id
    @Column(name="PRODUCT_ID", nullable = false)
    private Long productId;
    @Column(name="PRODUCT_NAME", length = 64)
    private String productName;
    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;
    @Column(name="QTY_ON_HAND")
    private Long qtyOnHand;

    public Product() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(Long qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
