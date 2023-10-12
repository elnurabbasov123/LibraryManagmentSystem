package helper.menuHelper;

import enums.Exception;
import exception.GeneralException;
import model.*;
import service.impl.*;
import service.inter.*;
import utility.InputUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MenuHelper {
    AdminService adminService = new IAdminService();
    BookService bookService = new IBookService();
    CourierService courierService = new ICourierService();
    OrderService orderService = new IOrderService();
    CustomerService customerService = new ICustomerService();

    public static int selectMenu() {
        int option = InputUtil.getInstance().inputInt("---------------Library Managment System---------------\n" +
                "[0]->Exit\n" +
                "[1]->Admin\n" +
                "[2]->Customer\n" +
                "Select :");

        return option;
    }

    protected Book selectBook(List<Book> books) {
        Book book = null;

        int bookNum = InputUtil.getInstance().inputInt("Which book do you want to order ? :");

        boolean ok = false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == bookNum&&books.get(i).getCopiesAvailable()>0) {
                book = books.get(i);
                ok = true;
            }
        }
        if (ok) {
            return book;
        } else {
            throw new GeneralException(Exception.OBJECT_NOT_FOUND_EXCEPTION);
        }
    }

    protected List<Book> showLibraryBooks(Library library) {
        List<Book> allBooks = bookService.getAll();
        List<Book> libraryBooks=new ArrayList<>();

        for (int i = 0; i < allBooks.size(); i++) {
            if (allBooks.get(i).getLibraryId()==library.getId()){
                libraryBooks.add(allBooks.get(i));
            }
        }
        libraryBooks.forEach(System.out::println);

        return libraryBooks;
    }

    public void viewObjects(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public void trackOrders() {

        List<Order> orders = orderService.getAll();

        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i));
        }
    }
    void checkCourier(){
        for (int i = 0; i < orderService.getAll().size(); i++) {
            if(orderService.getAll().get(i).getStatus().equals("delivered")){
                courierService.changeStatus(true,orderService.getAll().get(i).getCourierId());
            }
        }
    }
}
