package com.shopping.cart.view;

import com.shopping.cart.view.helper.ServiceProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.event.ActionEvent;

import javax.faces.model.SelectItem;

import model.Category;
import model.Product;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.QueryEvent;
import oracle.adf.view.rich.model.FilterableQueryDescriptor;
import oracle.adf.view.rich.model.QueryModel;

import org.apache.myfaces.trinidad.model.CollectionModel;

import soadev.ext.adf.query.AttributeDef;
import soadev.ext.adf.query.FilterableQueryDescriptorImpl;
import soadev.ext.adf.query.OperatorDef;
import soadev.ext.adf.query.QueryModelImpl;
import soadev.ext.adf.query.SavedSearchDef;
import soadev.ext.adf.query.SearchFieldDef;
import soadev.ext.adf.query.helper.EntityDefUtil;
import soadev.ext.trinidad.model.JpqlLazyDataModel;

public class ProductListForm {

    private QueryModel queryModel;
    private Map<String, AttributeDef> productAttributes;
    private JpqlLazyDataModel collectionModel;
    private RichTable productTable;
    private FilterableQueryDescriptor productFilterModel;
    private List<SelectItem> categorySelectItems;

    @PostConstruct
    public void initialize() {
        productAttributes =
                EntityDefUtil.getAttributeDefs(Product.class, "ShoppingCartBundle",
                                               "productId", "productName",
                                               "qtyOnHand",
                                               "category.categoryName");
    }


    public QueryModel getQueryModel() {
        if (queryModel == null) {
            queryModel =
                    new QueryModelImpl("productQuery", productAttributes, null,
                                       null);
        }
        return queryModel;
    }

    public JpqlLazyDataModel getCollectionModel() {
        if (collectionModel == null) {
            collectionModel = new JpqlLazyDataModel(Product.class, 20, true) {

                    protected List<Product> queryByRange(String jpqlStmt,
                                                         int first,
                                                         int result) {
                        return ServiceProvider.getProductService().findProductByCriteria(jpqlStmt,
                                                                                         first,
                                                                                         result);
                    }
                };
        }
        return collectionModel;
    }
    
    public void onSearchButtonClick_(QueryEvent queryEvent) {
        getCollectionModel().setFilterCriteria(queryEvent.getDescriptor().getConjunctionCriterion());
    }
    
    public void onSearchButtonClick(QueryEvent queryEvent) {
        FilterableQueryDescriptorImpl descriptor = (FilterableQueryDescriptorImpl)queryEvent.getDescriptor();
        getCollectionModel().setFilterCriteria(descriptor.getFilterConjunctionCriterion_());
    }

    public String viewDetailsListener() {
        Product selectedProduct =
            (Product)getProductTable().getSelectedRowData();
        AdfFacesContext facesContext = AdfFacesContext.getCurrentInstance();
        facesContext.getPageFlowScope().put("selectedProductId",
                                            selectedProduct.getProductId());
        return "to_edit";
    }

    public String createNewProduct() {
        AdfFacesContext facesContext = AdfFacesContext.getCurrentInstance();
        facesContext.getPageFlowScope().put("selectedProductId", -1L);
        return "to_edit";
    }

    public void setProductTable(RichTable productTable) {
        this.productTable = productTable;
    }

    public RichTable getProductTable() {
        return productTable;
    }
    
    public FilterableQueryDescriptor getProductFilterModel() {
        if(productFilterModel == null){
            SavedSearchDef ssd = new SavedSearchDef();
            ssd.setName("ItemStockFilterModel");
            ssd.setReadOnly(true);
            ssd.setDefaultSearch(true);
            for (Map.Entry<String, AttributeDef> entry :
                 productAttributes.entrySet()) {
                AttributeDef attr = entry.getValue();
                SearchFieldDef sfd = new SearchFieldDef();
                sfd.setAttrName(attr.getName());
                if("category.categoryName".equals(attr.getName())){
                    sfd.setOperator(OperatorDef.IN);
                }
                else{
                    sfd.setOperator(attr.getDefaultOperator());
                }
                ssd.addSearchFieldDef(sfd);
            }
            
            productFilterModel = new FilterableQueryDescriptorImpl(ssd, productAttributes);
        }
        return productFilterModel;
    }

    public List<SelectItem> getCategorySelectItems() {
        if(categorySelectItems == null){
            categorySelectItems = new ArrayList<SelectItem>();

            
            for (Category categoryItem : getCategoryList()) {
                categorySelectItems.add(new SelectItem(categoryItem.getCategoryName(),
                                                           categoryItem.getCategoryName()));
            }
        }
        return categorySelectItems;
    }
    
    public List<Category> getCategoryList() {
        return ServiceProvider.getCategoryService().getCategoryFindAll();
    }
}
