package service.inter;

import model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();
    void add(Order order);
    void cancelById(int id);
    void changeStatus();

}
