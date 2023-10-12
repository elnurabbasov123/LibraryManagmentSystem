package service.impl;

import model.Order;
import repository.impl.IOrderRepository;
import repository.inter.OrderRepository;
import response.GeneralResponse;
import service.inter.OrderService;

import java.util.List;

public class IOrderService implements OrderService {
    OrderRepository orderRepository=new IOrderRepository();

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public void add(Order order) {
        orderRepository.addOrder(order);
    }

    @Override
    public void cancelById(int id) {
        orderRepository.cancelById(id);
    }

    @Override
    public void changeStatus() {
        orderRepository.changeStatus();
    }
}
