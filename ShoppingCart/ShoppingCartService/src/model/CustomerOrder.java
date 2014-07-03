package model;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "CustomerOrder.findAll", query = "select o from CustomerOrder o"),
  @NamedQuery(name = "findOrderByCustomer", query = "select o from CustomerOrder o where o.customer = :customer")
})
@Table(name = "CUSTOMER_ORDER")
@SequenceGenerator(sequenceName = "ORDER_SEQUENCE", name = "orderSequence", allocationSize = 1)
public class CustomerOrder implements Serializable {
    @Column(name="CARD_TYPE", length = 20)
    private String cardType;
    @Column(name="SHIPPER", length = 20)
    private String shipper;
    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    @Column(name="ORDER_DATE")
    private Timestamp orderDate;
    @Column(name="ORDER_PRICE")
    private BigDecimal orderPrice;
    @Id
    @Column(name="ORDER_ID", nullable = false)
    @GeneratedValue(generator = "orderSequence", strategy = GenerationType.SEQUENCE)
    private Long orderId;
    @OneToMany(mappedBy="customerOrder", fetch=FetchType.EAGER)
    private List<OrderItem> orderItemList;

    public CustomerOrder() {
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getShipper() {
        return shipper;
    }
}
