package com.shopping.cart.view;

import com.shopping.cart.view.helper.ServiceProvider;

import java.util.Collections;
import java.util.List;

import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.event.ActionEvent;

import model.Customer;
import model.CustomerOrder;

import model.Product;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.event.ReturnPopupEvent;
import oracle.adf.view.rich.model.ListOfValuesModel;

import soadev.ext.adf.query.AttributeDef;
import soadev.ext.adf.query.ListOfValuesModelImpl;
import soadev.ext.adf.query.QueryModelImpl;
import soadev.ext.adf.query.helper.EntityDefUtil;
import soadev.ext.trinidad.model.JpqlLazyDataModel;

public class TransactionForm {

    private RichTable customerOrderTable;
    private List<CustomerOrder> customerOrderList;
    private ListOfValuesModel customerLOV;
    private Map<String, AttributeDef> customerAttributes;
    private Customer customer;
    private CustomerOrder selectedCustomerOrder;
    private RichPopup popupDialog;

    @PostConstruct
    public void initialize(){
        customerAttributes = EntityDefUtil.getAttributeDefs(Customer.class, "ShoppingCartBundle", "customerId", "customerUsername");
    }

    public void setCustomerOrderTable(RichTable customerOrderTable) {
        this.customerOrderTable = customerOrderTable;
    }

    public RichTable getCustomerOrderTable() {
        return customerOrderTable;
    }

    public void setCustomerOrderList(List<CustomerOrder> customerOrder) {
        this.customerOrderList = customerOrder;
    }

    public List<CustomerOrder> getCustomerOrderList() {
        if(customerOrderList == null){
            customerOrderList = ServiceProvider.getCustomerOrderService().getCustomerOrderFindAll();
        }
        return customerOrderList;
    }

    public void setCustomerLOV(ListOfValuesModel customerOrderLOV) {
        this.customerLOV = customerOrderLOV;
    }

    public ListOfValuesModel getCustomerLOV() {
        if(customerLOV == null){
            customerLOV = new ListOfValuesModelImpl(
                                   new String[]{"customerUsername"}, 
                                   new QueryModelImpl("customerQuery", customerAttributes, null, null),
                                   new JpqlLazyDataModel(Customer.class, 20){
                                         protected List queryByRange(String jpqlStmt, int first,
                                                                    int last) {
                                            return ServiceProvider.getCustomerService().findCustomerByCriteria(jpqlStmt,first,last);
                                        }
                                    });
        }
        return customerLOV;
    }

    public void setCustomerAttributes(Map<String, AttributeDef> customerAttributes) {
        this.customerAttributes = customerAttributes;
    }

    public Map<String, AttributeDef> getCustomerAttributes() {
        return customerAttributes;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void returnPopUpListener(ReturnPopupEvent returnPopupEvent) {
        customerOrderList = ServiceProvider.getCustomerOrderService().getCustomerOrderByCustomer(customer);
    }

    public void setSelectedCustomerOrder(CustomerOrder selectedCustomerOrder) {
        this.selectedCustomerOrder = selectedCustomerOrder;
    }

    public CustomerOrder getSelectedCustomerOrder() {
        return selectedCustomerOrder;
    }

    public void orderLinkListener(ActionEvent actionEvent) {
        selectedCustomerOrder = (CustomerOrder)actionEvent.getComponent().getAttributes().get("data");
        getPopupDialog().show(new RichPopup.PopupHints());
    }

    public void setPopupDialog(RichPopup popupDialog) {
        this.popupDialog = popupDialog;
    }

    public RichPopup getPopupDialog() {
        return popupDialog;
    }
}
