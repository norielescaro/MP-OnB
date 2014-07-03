package model;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CustomerOrderServiceClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            CustomerOrderService customerOrderService = (CustomerOrderService)context.lookup("ShoppingCart-ShoppingCartService-CustomerOrderService#model.CustomerOrderService");
            for (CustomerOrder customerorder : (List<CustomerOrder>)customerOrderService.getCustomerOrderFindAll()) {
                printCustomerOrder(customerorder);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printCustomerOrder(CustomerOrder customerorder) {
        System.out.println( "cardType = " + customerorder.getCardType() );
        System.out.println( "customerId = " + customerorder.getCustomer() );
        System.out.println( "orderDate = " + customerorder.getOrderDate() );
        System.out.println( "orderId = " + customerorder.getOrderId() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
