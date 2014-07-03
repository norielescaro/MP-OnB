package com.shopping.cart.model;

import java.math.BigDecimal;

import model.Product;

public class CartItem {
    private BigDecimal price;
    private Product product;
    private Long quantity;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CartItem)) {
            return false;
        }
        final CartItem other = (CartItem)object;
        if (!(product == null ? other.product == null : product.equals(other.product))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((product == null) ? 0 : product.hashCode());
        return result;
    }
}
