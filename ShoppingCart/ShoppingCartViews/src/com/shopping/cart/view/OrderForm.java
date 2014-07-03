package com.shopping.cart.view;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

import model.CustomerOrder;
import model.Product;

import oracle.adf.view.rich.context.AdfFacesContext;

public class OrderForm {
    private Timestamp dateTime;
    private CustomerOrder customerOrder;

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Timestamp getDateTime() {
        if(dateTime == null){
            dateTime = new Timestamp(new Date().getTime());
        }
        return dateTime;
    }

    public String homePageCommand() {
        return "home";
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public CustomerOrder getCustomerOrder() {
        if(customerOrder == null){
            AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
            customerOrder = (CustomerOrder)adfFacesContext.getPageFlowScope().get("order");
        }
        return customerOrder;
    }
}
