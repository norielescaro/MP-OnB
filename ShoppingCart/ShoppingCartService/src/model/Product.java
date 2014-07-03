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
import javax.persistence.Transient;

@Entity
@NamedQueries( { @NamedQuery(name = "Product.findAll",
                             query = "select o from Product o"),
                 @NamedQuery(name = "findProductById",
                             query = "select o from Product o where o.productId = :productId") })
@SequenceGenerator(sequenceName = "PRODUCT_SEQUENCE", name = "productSequence",
                   allocationSize = 1)
public class Product implements Serializable {

    private BigDecimal price;
    @Id
    @GeneratedValue(generator = "productSequence",
                    strategy = GenerationType.SEQUENCE)
    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;
    @Column(name = "PRODUCT_NAME", length = 64)
    private String productName;
    @Column(name = "QTY_ON_HAND")
    private Long qtyOnHand;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Product)) {
            return false;
        }
        final Product other = (Product)object;
        if (!(productId == null ? other.productId == null :
              productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result =
                PRIME * result + ((productId == null) ? 0 : productId.hashCode());
        return result;
    }
}
