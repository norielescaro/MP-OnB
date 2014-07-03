package com.shopping.cart;

import com.shopping.cart.view.helper.ServiceProvider;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.event.ActionEvent;

import model.Category;
import model.Product;

import model.ProductService;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.model.ListOfValuesModel;

import soadev.ext.adf.query.AttributeDef;
import soadev.ext.adf.query.ListOfValuesModelImpl;
import soadev.ext.adf.query.QueryModelImpl;
import soadev.ext.adf.query.helper.EntityDefUtil;
import soadev.ext.trinidad.model.JpqlLazyDataModel;

public class ProductEditForm {
    private Product product;
    private static final String EDIT_MODE = "editMode";
    private static final String IDENTIFIER = "productId";
    private static final Long NEW_OBJECT = -1L;
    private ListOfValuesModel categoryLOV;
    private Map<String, AttributeDef> categoryAttributes;

    @PostConstruct
    public void initialize() {
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        Long productId =
            (Long)adfFacesContext.getPageFlowScope().get(IDENTIFIER);
        if (NEW_OBJECT.equals(productId)) {
            product = new Product();
        } else {
            ProductService productService =
                ServiceProvider.getProductService();
            product = productService.findProductById(productId);
        }
        categoryAttributes =
                EntityDefUtil.getAttributeDefs(Category.class, "ShoppingCartBundle",
                                               "categoryId", "categoryName",
                                               "categoryDescription");
    }

    public String cancel() {
        setEditing(false);
        return null;
    }

    public void save(ActionEvent actionEvent) {
        product = ServiceProvider.getProductService().mergeProduct(product);
        setEditing(false);
    }

    public void edit(ActionEvent actionEvent) {
        setEditing(true);
    }

    private void setEditing(boolean editing) {
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.getPageFlowScope().put(EDIT_MODE, editing);
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public ListOfValuesModel getCategoryLOV() {
        if (categoryLOV == null) {
            categoryLOV =
                    new ListOfValuesModelImpl(new String[] { "categoryId",
                                                             "categoryName" },
                                              new QueryModelImpl("categoryQuery",
                                                                 categoryAttributes,
                                                                 null, null),
                                              new JpqlLazyDataModel(Category.class,
                                                                    20) {
                        protected List queryByRange(String jpqlStmt, int first,
                                                    int maxResult) {
                            return ServiceProvider.getCategoryService().findCategoryByCriteria(jpqlStmt,
                                                                                               first,
                                                                                               maxResult);
                        }
                    });
        }
        return categoryLOV;
    }
}
