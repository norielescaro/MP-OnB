package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "CustomerCart.findAll", query = "select o from CustomerCart o")
})
@Table(name = "CUSTOMER_CART")
@SequenceGenerator(sequenceName = "CART_SEQUENCE", name = "cartSequence", allocationSize = 1)
public class CustomerCart implements Serializable {
    @Id
    @Column(name="CART_ID", nullable = false)
    @GeneratedValue(generator = "cartSequence", strategy = GenerationType.SEQUENCE)
    private Long cartId;
    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    @Column(name="PRODUCT_ID")
    private Long productId;
    private Long quantity;

    public CustomerCart() {
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CustomerCart)) {
            return false;
        }
        final CustomerCart other = (CustomerCart)object;
        if (!(customer == null ? other.customer == null : customer.equals(other.customer))) {
            return false;
        }
        if (!(productId == null ? other.productId == null : productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((customer == null) ? 0 : customer.hashCode());
        result = PRIME * result + ((productId == null) ? 0 : productId.hashCode());
        return result;
    }
}
