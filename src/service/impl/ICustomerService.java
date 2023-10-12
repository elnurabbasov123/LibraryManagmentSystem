package service.impl;

import enums.Exception;
import exception.GeneralException;
import helper.menuHelper.CustomerMenuHelper;
import helper.menuHelper.MenuHelper;
import helper.serviceHelper.CustomerServiceHelper;
import model.Customer;
import model.Order;
import repository.impl.ICustomerRepository;
import repository.inter.CustomerRepository;
import service.inter.CustomerService;
import service.inter.OrderService;

import java.util.List;

public class ICustomerService implements CustomerService {
    CustomerRepository customerRepository=new ICustomerRepository();
    OrderService orderService=new IOrderService();
    static MenuHelper helper=new MenuHelper();
    CustomerServiceHelper customerServiceHelper=new CustomerServiceHelper();

    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public void updateCapital(Customer customer,double capital) {
        customerRepository.updateCapital(customer,capital);
    }

    @Override
    public Customer getById(int id) {
        Customer customer=null;

        boolean found=false;

        for (int i = 0; i < customerRepository.getAll().size(); i++) {
            if (customerRepository.getAll().get(i).getId()==id){
                customer=customerRepository.getAll().get(i);
                found=true;
            }
        }
        if(found) {
            return customer;
        }else {
            throw new GeneralException(Exception.OBJECT_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public Order placeOrder(Customer logginedCustomer) {
        return customerServiceHelper.placeOrder(logginedCustomer);
    }

    @Override
    public Customer signUp() {
       return customerRepository.signUp();
    }
}

