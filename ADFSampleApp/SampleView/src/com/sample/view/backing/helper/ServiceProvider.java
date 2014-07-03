package com.sample.view.backing.helper;

import com.sample.model.ProductService;
import com.sample.model.CategoryService;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceProvider {

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext(env);
    }

    public static ProductService getProductService() {
        ProductService productService = null;
        try {
            productService = (ProductService)getInitialContext().lookup("ADFSampleApp-SampleService-ProductService#com.sample.model.ProductService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return productService;
    }
    
    public static CategoryService getCategoryService() {
        CategoryService categoryService = null;
        try {
            categoryService = (CategoryService)getInitialContext().lookup("ADFSampleApp-SampleService-CategoryService#com.sample.model.CategoryService");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return categoryService;
    }
}
