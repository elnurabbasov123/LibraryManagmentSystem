package repository.impl;
import connection.ConnectionConfig;
import model.Order;
import repository.inter.OrderRepository;
import response.GeneralResponse;
import utility.DateChanger;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IOrderRepository implements OrderRepository {
    @Override
    public List<Order> getAll() {

        List<Order> orders=new ArrayList<>();
        Order order=null;

        try {
            Connection connection= ConnectionConfig.connection();
            String sql="select * from library_data.order";
            Statement statement=connection.createStatement();
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int customerId = resultSet.getInt(2);
                int courierId = resultSet.getInt(3);
                int libraryId = resultSet.getInt(4);
                int bookId = resultSet.getInt(5);


                String orderDateStr = resultSet.getString(6);
                String deliveryDateStr = resultSet.getString(7);
                String startDateStr = resultSet.getString(8);
                String endDateStr = resultSet.getString(9);

                double paymentAmountDouble = resultSet.getDouble(10);
                String status = resultSet.getString(11);


                LocalDateTime orderDate = DateChanger.change(orderDateStr);
                LocalDateTime deliveryDate = DateChanger.change(deliveryDateStr);
                LocalDateTime startDate = DateChanger.change(startDateStr);
                LocalDateTime endDate = DateChanger.change(endDateStr);

                order=new Order(id,customerId,courierId,libraryId,bookId,orderDate,deliveryDate,paymentAmountDouble,startDate,endDate,status);

                orders.add(order);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return orders;
    }

    @Override
    public GeneralResponse addOrder(Order order) {

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy-HH':'mm");

        try {
            Connection connection = ConnectionConfig.connection();
            String sql="insert into library_data.order(customer_id,courier_id,library_id,book_id,order_date,delivery_date,start_date,end_date,payment_amount,status)\n" +
                    "values(?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,order.getCustomerId());
            preparedStatement.setInt(2,order.getCourierId());
            preparedStatement.setInt(3,order.getLibraryId());
            preparedStatement.setInt(4,order.getBookId());
            preparedStatement.setString(5,formatter.format(order.getOrderDate()).toString());
            preparedStatement.setString(6,formatter.format(order.getDeliveryDate()).toString());
            preparedStatement.setString(7,formatter.format(order.getStartDate()).toString());
            preparedStatement.setString(8,formatter.format(order.getEndDate()).toString());
            preparedStatement.setDouble(9,order.getPaymentAmount());
            preparedStatement.setString(10,order.getStatus());
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new GeneralResponse().of("Order Added Successfully");
    }

    @Override
    public void cancelById(int id) {
        try {
            Connection connection = ConnectionConfig.connection();
            String sql="delete from library_data.order where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void changeStatus() {
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < getAll().size(); i++) {
            if (getAll().get(i).getDeliveryDate().isBefore(now)){
                int id = getAll().get(i).getId();
                try {
                    Connection connection = ConnectionConfig.connection();
                    String sql="update library_data.order set status=? where id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1,"delivered");
                    preparedStatement.setInt(2,id);
                    preparedStatement.execute();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
