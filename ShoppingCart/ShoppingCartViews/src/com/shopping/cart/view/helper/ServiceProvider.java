package com.shopping.cart.view.helper;

import java.util.Hashtable;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.CategoryService;
import model.CustomerCartService;
import model.CustomerOrderService;
import model.CustomerService;
import model.OrderItemService;
import model.ProductService;

public class ServiceProvider {
    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
    
    public static CategoryService getCategoryService() {
        CategoryService categoryService = null;
        try {
            categoryService = (CategoryService)getInitialContext().lookup("ShoppingCart-ShoppingCartService-CategoryService#model.CategoryService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return categoryService;
    }
    
    public static CustomerService getCustomerService() {
        CustomerService customerService = null;
        try {
            customerService = (CustomerService)getInitialContext().lookup("ShoppingCart-ShoppingCartService-CustomerService#model.CustomerService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return customerService;
    }
    
    public static CustomerCartService getCustomerCartService() {
        CustomerCartService customerCartService = null;
        try {
            customerCartService = (CustomerCartService)getInitialContext().lookup("ShoppingCart-ShoppingCartService-CustomerCartService#model.CustomerCartService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return customerCartService;
    }
    
    public static ProductService getProductService() {
        ProductService productService = null;
        try {
            productService = (ProductService)getInitialContext().lookup("ShoppingCart-ShoppingCartService-ProductService#model.ProductService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return productService;
    }
    
    public static CustomerOrderService getCustomerOrderService() {
        CustomerOrderService customerOrderService = null;
        try {
            customerOrderService = (CustomerOrderService)getInitialContext().lookup("ShoppingCart-ShoppingCartService-CustomerOrderService#model.CustomerOrderService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return customerOrderService;
    }
    
    public static OrderItemService getOrderItemService() {
        OrderItemService orderItemService = null;
        try {
            orderItemService = (OrderItemService)getInitialContext().lookup("ShoppingCart-ShoppingCartService-OrderItemService#model.OrderItemService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return orderItemService;
    }
    
    public static Object getSessionObject(String objName) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extCtx = ctx.getExternalContext();
        Map<String, Object> sessionMap = extCtx.getSessionMap();
        return sessionMap.get(objName);
    }
}
