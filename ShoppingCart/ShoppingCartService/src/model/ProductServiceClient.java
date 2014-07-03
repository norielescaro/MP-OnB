package model;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ProductServiceClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            ProductService productService = (ProductService)context.lookup("ShoppingCart-ShoppingCartService-ProductService#model.ProductService");
            for (Product product : (List<Product>)productService.getProductFindAll()) {
                printProduct(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printProduct(Product product) {
        System.out.println( "price = " + product.getPrice() );
        System.out.println( "productId = " + product.getProductId() );
        System.out.println( "productName = " + product.getProductName() );
        System.out.println( "qtyOnHand = " + product.getQtyOnHand() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
