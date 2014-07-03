package model;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class OrderItemServiceClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            OrderItemService orderItemService = (OrderItemService)context.lookup("ShoppingCart-ShoppingCartService-OrderItemService#model.OrderItemService");
            for (OrderItem orderitem : (List<OrderItem>)orderItemService.getOrderItemFindAll()) {
                printOrderItem(orderitem);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printOrderItem(OrderItem orderitem) {
        System.out.println( "orderItemId = " + orderitem.getOrderItemId() );
        System.out.println( "price = " + orderitem.getPrice() );
        System.out.println( "quantity = " + orderitem.getQuantity() );
        System.out.println( "customerOrder = " + orderitem.getCustomerOrder() );
        System.out.println( "product = " + orderitem.getProduct() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
