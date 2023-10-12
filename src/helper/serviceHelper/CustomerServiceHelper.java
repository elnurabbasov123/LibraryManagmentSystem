package helper.serviceHelper;

import helper.menuHelper.CustomerMenuHelper;
import model.Customer;
import model.Order;
import service.impl.IOrderService;
import service.inter.OrderService;

public class CustomerServiceHelper {

    OrderService orderService=new IOrderService();
    public Order placeOrder(Customer logginedCustomer){
        Order order = CustomerMenuHelper.makeOrder(logginedCustomer);
        orderService.add(order);
        CustomerMenuHelper.cachReceipt(order);

        return order;
    }
}
