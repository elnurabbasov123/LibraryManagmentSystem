package repository.inter;

import model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getAll();
    void updateCapital(Customer customer,double capital);
    Customer signUp();
}
