package model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries({
  @NamedQuery(name = "Customer.findAll", query = "select o from Customer o")
})
@SequenceGenerator(sequenceName = "CUSTOMER_SEQUENCE", name = "customerSequence", allocationSize = 1)
public class Customer implements Serializable {
    @Id
    @Column(name="CUSTOMER_ID", nullable = false)
    @GeneratedValue(generator = "customerSequence", strategy = GenerationType.SEQUENCE)
    private Long customerId;
    @Column(name="CUSTOMER_PASSWORD", length = 20)
    private String customerPassword;
    @Column(name="CUSTOMER_USERNAME", length = 20)
    private String customerUsername;
    @OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
    private List<CustomerCart> cart;

    public Customer() {
    }

    public Customer(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public void setCart(List<CustomerCart> cart) {
        this.cart = cart;
    }

    public List<CustomerCart> getCart() {
        return cart;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Customer)) {
            return false;
        }
        final Customer other = (Customer)object;
        if (!(customerId == null ? other.customerId == null : customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((customerId == null) ? 0 : customerId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return customerUsername;
    }
}
