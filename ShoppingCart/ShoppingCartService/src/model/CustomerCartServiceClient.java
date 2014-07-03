package model;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CustomerCartServiceClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            CustomerCartService customerCartService = (CustomerCartService)context.lookup("ShoppingCart-ShoppingCartService-CustomerCartService#model.CustomerCartService");
            for (CustomerCart customercart : (List<CustomerCart>)customerCartService.getCustomerCartFindAll()) {
                printCustomerCart(customercart);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printCustomerCart(CustomerCart customercart) {
        System.out.println( "cartId = " + customercart.getCartId() );
        System.out.println( "productId = " + customercart.getProductId() );
        System.out.println( "quantity = " + customercart.getQuantity() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
