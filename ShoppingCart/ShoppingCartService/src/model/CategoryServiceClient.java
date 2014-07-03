package model;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CategoryServiceClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            CategoryService categoryService = (CategoryService)context.lookup("ShoppingCart-ShoppingCartService-CategoryService#model.CategoryService");
            for (Category category : (List<Category>)categoryService.getCategoryFindAll()) {
                printCategory(category);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printCategory(Category category) {
        System.out.println( "categoryDescrption = " + category.getCategoryDescription() );
        System.out.println( "categoryId = " + category.getCategoryId() );
        System.out.println( "categoryName = " + category.getCategoryName() );
        System.out.println( "products = " + category.getProducts() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
