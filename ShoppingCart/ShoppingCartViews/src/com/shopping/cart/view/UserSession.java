package com.shopping.cart.view;

import com.shopping.cart.model.CartItem;
import com.shopping.cart.view.helper.ServiceProvider;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.validator.ValidatorException;

import model.Customer;
import model.CustomerCart;
import model.CustomerCartService;
import model.CustomerOrder;
import model.OrderItem;
import model.Product;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;

public class UserSession {
    private List<CartItem> cartItemList;
    private Customer customer;
    private String cardType;
    private String shipper;
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public List<CartItem> getCartItemList() {
        if(cartItemList == null){
            cartItemList = new ArrayList<CartItem>();
        }
        return cartItemList;
    }

//    private void getToCart() {
//        for(CustomerCart cart : getCustomerCart()){
//            productList.add(getProduct(cart));
//        }
//    }

//    public void setCustomerCart(List<CustomerCart> customerCart) {
//        this.customerCart = customerCart;
//    }

//    public List<CustomerCart> getCustomerCart() {
//        if(customerCart == null){
//            customerCart = new ArrayList<CustomerCart>();
//            List<CustomerCart> carts = ServiceProvider.getCustomerCartService().getCustomerCartFindAll();
//            for(CustomerCart cart : carts){
//                if(cart.getCustomer().equals(getCustomer())){
//                    customerCart.add(cart);
//                }
//            }
//        }
//        return customerCart;
//    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        if(customer == null){
            customer = ServiceProvider.getCustomerService().getCustomer();
        }
        return customer;
    }

    private Product getProduct(CustomerCart cart) {
        Product result = new Product();
        result.setProductId(cart.getProductId());
        final int INDEX = ServiceProvider.getProductService().getProductFindAll().indexOf(result);
        result = ServiceProvider.getProductService().getProductFindAll().get(INDEX);
        result.setQtyOnHand(cart.getQuantity());
        return result;
    }

    public void onClickedDeleteListener(ActionEvent actionEvent) {
        CartItem cartItem = (CartItem)actionEvent.getComponent().getAttributes().get("data");
        cartItemList.remove(cartItem);
    }

    public String goToHomePageCommand() {
        return "back";
    }

    public void onClickClearListener(ActionEvent actionEvent) {
        cartItemList.clear();
    }

    public String finalizeOrderCommand() {
        submitOrder();
        return "finalize";
    }

    private void submitOrder() {
        CustomerOrder order = new CustomerOrder();
        order.setCardType(cardType);
        order.setShipper(shipper);
        order.setOrderPrice(totalPrice);
        order.setCustomer(ServiceProvider.getCustomerService().getCustomer());
        order.setOrderDate(new Timestamp(new Date().getTime()));
        
        order = ServiceProvider.getCustomerOrderService().persistCustomerOrder(order);
        
        order.setOrderItemList(getOrderItem(order));
        
        saveOrderItem(order.getOrderItemList());
        
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.getPageFlowScope().put("order", order);
        
        updateProducts();
        reset();
    }

    public void validateQuantities(FacesContext facesContext,
                                   UIComponent uIComponent, Object object) {
        Product product = ((CartItem)uIComponent.getAttributes().get("data")).getProduct();
        if(product.getQtyOnHand() < 1){
            throw new ValidatorException(new FacesMessage("Quantity should be greater than zero!"));
        }
        List<Product> databaseProducts = ServiceProvider.getProductService().getProductFindAll();
        
        Product tempProduct = databaseProducts.get(databaseProducts.indexOf(product));
        if(product.getQtyOnHand() > tempProduct.getQtyOnHand()){
            throw new ValidatorException(new FacesMessage(tempProduct.getQtyOnHand() + " " + product.getProductName() + " is/are only available!"));
        }
    }

    private void updateProducts() {
        List<Product> databaseProducts = ServiceProvider.getProductService().getProductFindAll();
        for(CartItem cartItem : cartItemList){
            Product product = databaseProducts.get(databaseProducts.indexOf(cartItem.getProduct()));
            product.setQtyOnHand(product.getQtyOnHand() - cartItem.getQuantity());
            ServiceProvider.getProductService().mergeProduct(product);
        }
    }

    public BigDecimal getTotalPrice() {
        for(CartItem cartItem : cartItemList){
            totalPrice = totalPrice.add(cartItem.getPrice());
        }
        return totalPrice;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getShipper() {
        return shipper;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void saveOrderItem(List<OrderItem> orderItemList) {
        for(OrderItem orderItem : orderItemList){
            ServiceProvider.getOrderItemService().persistOrderItem(orderItem);
        }
    }

    private List<OrderItem> getOrderItem(CustomerOrder order) {
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for(CartItem cartItem : cartItemList){
            OrderItem orderItem = new OrderItem();
            orderItem.setCustomerOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    private void reset() {
        cartItemList.clear();
        totalPrice = BigDecimal.ZERO;
        cardType = null;
        shipper = null;
    }
}
