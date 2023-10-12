package repository.inter;

import model.Order;
import response.GeneralResponse;

import java.util.List;

public interface OrderRepository {
    List<Order> getAll();
    GeneralResponse addOrder(Order order);
    void cancelById(int id);
    void changeStatus();
}
