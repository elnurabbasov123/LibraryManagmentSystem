package service.impl;

import enums.Exception;
import exception.GeneralException;
import helper.serviceHelper.PaymentServiceHelper;
import model.Book;
import model.Courier;
import model.Customer;
import service.inter.BookService;
import service.inter.CustomerService;
import service.inter.PaymentService;
import utility.InputUtil;

public class IPaymentService implements PaymentService {
    PaymentServiceHelper paymentServiceHelper=new PaymentServiceHelper();
    @Override
    public double payment(Customer customer, int rentDays, Book book, Courier courier) {
       return paymentServiceHelper.payment(customer,rentDays,book,courier);
    }
}
