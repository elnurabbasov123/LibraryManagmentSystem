package helper.serviceHelper;

import enums.Exception;
import exception.GeneralException;
import model.Book;
import model.Courier;
import model.Customer;
import service.impl.IBookService;
import service.impl.ICourierService;
import service.impl.ICustomerService;
import service.inter.BookService;
import service.inter.CourierService;
import service.inter.CustomerService;
import utility.InputUtil;

public class PaymentServiceHelper {
    CustomerService customerService=new ICustomerService();
    BookService bookService=new IBookService();
    CourierService courierService=new ICourierService();

    public double payment(Customer customer, int rentDays, Book book, Courier courier){
        double rentPrice = book.getRentPrice() * rentDays;
        int option = InputUtil.getInstance().inputInt("You order amount " + rentPrice + "$\n" +
                "[1]->Make Payment.\n" +
                "[2]->Cancel.\n" +
                "Select :");

        if (option == 1) {
            if (customer.getCapital() > rentPrice) {
                double newCapital = customer.getCapital() - rentPrice;
                customer.setCapital(newCapital);
                customerService.updateCapital(customer, newCapital);

                bookService.changeCount(book, "buy_count", "plus");
                bookService.changeCount(book, "copies_available", "minus");
                System.out.println("Payment Comleted Successfully.");
            } else {
                throw new GeneralException(Exception.INSUFFCIENT_FOUNDS_EXCEPTION);
            }

        }else if(option==2){
            courier.setEmpty(true);
            courierService.changeStatus(true,courier.getId());
            System.exit(0);
        }else {
            throw new GeneralException(Exception.OPERATION_NOT_FOUND_EXCEPTION);
        }
        return rentPrice;
    }
}
