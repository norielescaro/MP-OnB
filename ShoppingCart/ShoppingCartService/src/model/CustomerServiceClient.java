package model;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CustomerServiceClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            CustomerService customerService = (CustomerService)context.lookup("ShoppingCart-ShoppingCartService-CustomerService#model.CustomerService");
            for (Customer customer : (List<Customer>)customerService.getCustomerFindAll()) {
                printCustomer(customer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printCustomer(Customer customer) {
        System.out.println( "customerId = " + customer.getCustomerId() );
        System.out.println( "customerPassword = " + customer.getCustomerPassword() );
        System.out.println( "customerUsername = " + customer.getCustomerUsername() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
