package service.inter;

import model.Customer;
import model.Order;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    void updateCapital(Customer customer,double capital);
    Customer getById(int id);
    Order placeOrder(Customer customer);
    Customer signUp();
}
