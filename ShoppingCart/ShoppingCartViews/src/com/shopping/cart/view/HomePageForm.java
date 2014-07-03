package com.shopping.cart.view;

import com.shopping.cart.model.CartItem;
import com.shopping.cart.view.helper.ServiceProvider;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.faces.validator.ValidatorException;

import model.Category;
import model.CustomerCart;
import model.OrderItem;
import model.Product;

import oracle.adf.view.rich.component.rich.RichPopup;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;

import soadev.view.utils.JSFUtils;

public class HomePageForm {
    private Long categoryId = 0L;
    private List<Product> productList;
    private List<Category> categoryList;
    private List<SelectItem> categoryCodeSelectItems;
    private Product selectedProduct;
    private Long quantity = 0L;
    private RichPopup popupDialog;
    private CustomerCart customerCart;
    private String username;
    private String password;

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        if (productList == null) {
            productList =
                    ServiceProvider.getProductService().getProductFindAll();
        }
        return productList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getCategoryList() {
        if (categoryList == null) {
            categoryList =
                    ServiceProvider.getCategoryService().getCategoryFindAll();
        }
        return categoryList;
    }

    public void setCategoryCodeSelectItems(List<SelectItem> categoryCodeSelectItems) {
        this.categoryCodeSelectItems = categoryCodeSelectItems;
    }

    public List<SelectItem> getCategoryCodeSelectItems() {
        if (categoryCodeSelectItems == null) {
            categoryCodeSelectItems = new ArrayList<SelectItem>();
            
            categoryCodeSelectItems.add(new SelectItem(0L, "All"));
            
            for (Category categoryItem : getCategoryList()) {
                categoryCodeSelectItems.add(new SelectItem(categoryItem.getCategoryId(),
                                                           categoryItem.getCategoryName()));
            }
        }
        return categoryCodeSelectItems;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void onCategoryChangeListener(ValueChangeEvent valueChangeEvent) {
        categoryId = (Long)valueChangeEvent.getNewValue();
        productList.clear();
        for (Product product :
             ServiceProvider.getProductService().getProductFindAll()) {
            if ((categoryId == 0L) || (product.getCategory().getCategoryId().equals(categoryId))) {
                productList.add(product);
            }
        }
    }

    public void addToCartListener(ActionEvent actionEvent) {
        selectedProduct = (Product)actionEvent.getComponent().getAttributes().get("data");
        getPopupDialog().show(new RichPopup.PopupHints());
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void submitButtonListener(ActionEvent actionEvent) {
        addToCart(quantity);
        quantity = 0L;
        popupDialog.cancel();
    }

    public void setPopupDialog(RichPopup popupDialog) {
        this.popupDialog = popupDialog;
    }

    public RichPopup getPopupDialog() {
        return popupDialog;
    }

    private void addToCart(Long quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(selectedProduct);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(selectedProduct.getPrice().multiply(new BigDecimal(quantity)));
        if(isExist(cartItem)){
            //final CustomerCart TEMP_CART = getCart(cart);
            //cart.setCartId(TEMP_CART.getCartId());
            cartItem = getCartItem(cartItem);
            cartItem.setQuantity(quantity + cartItem.getQuantity());
            cartItem.setPrice(selectedProduct.getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
        }else{
            ((UserSession)ServiceProvider.getSessionObject("userSession")).getCartItemList().add(cartItem);
        }
    }

    private boolean isExist(CartItem cartItem) {
        return ((UserSession)JSFUtils.getManagedBeanValue("userSession")).getCartItemList().contains(cartItem);
    }
    
//    private CustomerCart getCart(CustomerCart cart) {
//        final int INDEX =  ServiceProvider.getCustomerCartService().getCustomerCartFindAll().indexOf(cart);
//        return ServiceProvider.getCustomerCartService().getCustomerCartFindAll().get(INDEX);
//    }

    public void validateQuantity(FacesContext facesContext,
                                 UIComponent uIComponent, Object object) {
        if((Long)object < 1){
            throw new ValidatorException(new FacesMessage("Quantity should be greater than zero!"));
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(selectedProduct);
        Long databaseQuantity = 0L;
        if(isExist(cartItem)){
            databaseQuantity = getCartItem(cartItem).getQuantity();
        }
        Long quantity = (Long) object;
        if ((quantity + databaseQuantity) > selectedProduct.getQtyOnHand()) {
            throw new ValidatorException(new FacesMessage(selectedProduct.getQtyOnHand() + " " + selectedProduct.getProductName() + " is/are only available!"));
        }
    }
    
    private CustomerCart getCustomerCart() {
        if(customerCart == null){
            customerCart = new CustomerCart();
        }
        customerCart.setCustomer(ServiceProvider.getCustomerService().getCustomer());
        customerCart.setProductId(selectedProduct.getProductId());
        
        return customerCart;
    }

    public String goToCartCommand() {
        return "view";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String goToAdminPageCommand() {
        return "go_to_admin";
    }

    private CartItem getCartItem(CartItem cartItem) {
        final int INDEX = ((UserSession)ServiceProvider.getSessionObject("userSession")).getCartItemList().indexOf(cartItem);
        return ((UserSession)ServiceProvider.getSessionObject("userSession")).getCartItemList().get(INDEX);
    }
}
