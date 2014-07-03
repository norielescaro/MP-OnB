package model;

import java.io.Serializable;

import java.math.BigDecimal;

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
  @NamedQuery(name = "OrderItem.findAll", query = "select o from OrderItem o")
})
@Table(name = "ORDER_ITEM")
@SequenceGenerator(sequenceName = "ORDER_ITEM_SEQUENCE", name = "orderItemSequence",
                   allocationSize = 1)
public class OrderItem implements Serializable {
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private CustomerOrder customerOrder;
    @Id
    @Column(name="ORDER_ITEM_ID", nullable = false)
    @GeneratedValue(generator = "orderItemSequence",
                    strategy = GenerationType.SEQUENCE)
    private Long orderItemId;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
    private Long quantity;

    public OrderItem() {
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
