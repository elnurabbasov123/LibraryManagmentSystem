package repository.impl;
import connection.ConnectionConfig;
import helper.menuHelper.CustomerMenuHelper;
import model.Customer;
import model.Order;
import repository.inter.CustomerRepository;
import service.impl.IOrderService;
import service.inter.OrderService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ICustomerRepository implements CustomerRepository {
    OrderService orderService=new IOrderService();
    CustomerMenuHelper customerMenuHelper=new CustomerMenuHelper();


    @Override
    public List<Customer> getAll() {
        List<Customer> customers=new ArrayList<>();
        Customer customer=null;

        try {
            Connection connection= ConnectionConfig.connection();
            String sql="select * from library_data.customer";
            Statement statement=connection.createStatement();
            statement.execute(sql);

            ResultSet rs = statement.getResultSet();

            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String surname = rs.getString(3);
                String email = rs.getString(4);
                String phoneNumber=rs.getString(5);
                String password=rs.getString(6);
                double capital=rs.getDouble(7);

                List<Order> orders = orderService.getAll();
                List<Order> cstOrders=new ArrayList<>();
                Order order=null;

                for (int i = 0; i < orders.size(); i++) {
                    if (orders.get(i).getCustomerId()==id){
                        order=orders.get(i);
                        cstOrders.add(order);
                    }
                }

                customer=new Customer(id,name,surname,email,phoneNumber,password,capital,cstOrders);

                customers.add(customer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return customers;
    }

    @Override
    public void updateCapital(Customer customer, double capital) {

        try {
            Connection connection=ConnectionConfig.connection();
            String sql="update library_data.customer set capital=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1,capital);
            preparedStatement.setInt(2,customer.getId());
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer signUp() {
        Customer customer = customerMenuHelper.fillCustomer();

        try {
            Connection connection = ConnectionConfig.connection();
            String sql="insert into library_data.customer(name,surname,email,phone_number,password,capital)" +
                    "values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getSurname());
            preparedStatement.setString(3,customer.getEmail());
            preparedStatement.setString(4,customer.getPhoneNumber());
            preparedStatement.setString(5,customer.getPassword());
            preparedStatement.setDouble(6,customer.getCapital());
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }
}

