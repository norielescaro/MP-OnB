package com.sample.view.backing;

import com.sample.model.Category;
import com.sample.model.Product;
import com.sample.view.MyAttribute;
import com.sample.view.MyModel;

import com.sample.view.backing.helper.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import javax.faces.validator.ValidatorException;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class UserForm {
    private MyModel model = new MyModel();

    private String username;
    private List<SelectItem> codeSelectItems;
    private List<MyModel> models;
    private RichTable myTable;
    private List<Product> products;
    private Product product;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        username = (String)adfFacesContext.getPageFlowScope().get("username");
        return username;
    }

    public String logoutCommand() {
        return "logout";
    }

    public List<SelectItem> getCodeSelectItems() {
        if (codeSelectItems == null) {
            codeSelectItems = new ArrayList<SelectItem>();
            codeSelectItems.add(new SelectItem(new MyAttribute("001"),
                                               "My Code 1"));
            codeSelectItems.add(new SelectItem(new MyAttribute("002"),
                                               "My Code 2"));
            codeSelectItems.add(new SelectItem(new MyAttribute("003"),
                                               "My Code 3"));
            codeSelectItems.add(new SelectItem(new MyAttribute("004"),
                                               "My Code 4"));
        }
        return codeSelectItems;
    }

    public void setModel(MyModel model) {
        this.model = model;
    }

    public MyModel getModel() {
        return model;
    }

    public void onAddButtonPressedListener(ActionEvent actionEvent) {
        ServiceProvider.getProductService().persistProduct(product);
        products = ServiceProvider.getProductService().findAllProduct();
        product = null;
    }
    
    public void onUpdateButtonPressedListener(ActionEvent actionEvent) {
        ServiceProvider.getProductService().mergeProduct(product);
        products = ServiceProvider.getProductService().findAllProduct();
    }
    
    public void onRemoveButtonPressedListener(ActionEvent actionEvent) {
        ServiceProvider.getProductService().removeProduct(product);
        products = ServiceProvider.getProductService().findAllProduct();
    }

    public void validateId(javax.faces.context.FacesContext facesContext,
                           UIComponent uIComponent, Object object) {
        Integer value = (Integer)object;
        if (value > 50) {
            throw new ValidatorException(new FacesMessage("Error occured!"));
        }
    }

    public void dialogListener(DialogEvent dialogEvent) {
       
    }

    public void setModels(List<MyModel> models) {
        this.models = models;
    }

    public List<MyModel> getModels() {
        if (models == null) {
            models = new ArrayList<MyModel>();
            MyModel model1 = new MyModel();
            model1.setCode("001");
            model1.setDescription("desc001");
            models.add(model1);
            MyModel model2 = new MyModel();
            model2.setCode("002");
            model2.setDescription("desc002");
            models.add(model2);
        }
        return models;
    }

    public void setMyTable(RichTable myTable) {
        this.myTable = myTable;
    }

    public RichTable getMyTable() {
        return myTable;
    }

    public List<Product> getProducts() {
        if (products == null) {
            products = ServiceProvider.getProductService().findAllProduct();
        }
        return products;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        if(product == null){
            product = new Product();
        }
        return product;
    }

    public void onSelect(SelectionEvent selectionEvent) {
        myTable = (RichTable)selectionEvent.getSource();
        product = (Product) myTable.getSelectedRowData();
        
        List<Category> sample = ServiceProvider.getCategoryService().getCategoryFindAll();
        
        System.out.println(sample.get(0).getProducts().size());
        
        Category c = new Category();
        c.setCategoryDescription("Food");
        c.setCategoryName("Food");
        List<Product> lis = new ArrayList<Product>();
        Product p1 = new Product();
        p1.setProductId(6L);
        lis.add(p1);
        c.setProducts(lis);
        ServiceProvider.getCategoryService().persistCategory(c);
    }
}
