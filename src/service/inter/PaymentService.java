package service.inter;

import model.Book;
import model.Courier;
import model.Customer;

public interface PaymentService {
    double payment(Customer customer, int rentDays, Book book, Courier courier);
}
