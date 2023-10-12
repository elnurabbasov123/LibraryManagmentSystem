package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.impl.IBookService;
import service.impl.IOrderService;
import service.inter.BookService;
import service.inter.OrderService;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    private int id;
    private String name;
    private String adress;
    private String phoneNumber;

    private List<Book> books;
    private List<Order> orders;

    BookService bookService=new IBookService();
    OrderService orderService=new IOrderService();
    public Library(int id, String name, String adress, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }
    private List<Book> getBooks(){
        List<Book> libraryBooks=new ArrayList<>();

        for (int i = 0; i < bookService.getAll().size(); i++) {
            if (bookService.getAll().get(i).getLibraryId()==id){
                libraryBooks.add(bookService.getAll().get(i));
            }
        }
        return libraryBooks;
    }

    private List<Order> getOrders(){
        List<Order> orders=new ArrayList<>();

        for (int i = 0; i < orderService.getAll().size(); i++) {
            if (orderService.getAll().get(i).getLibraryId()==id){
                orders.add(orderService.getAll().get(i));
            }
        }
        return orders;
    }

    @Override
    public String toString() {
        return "["+id+"].Library{" +
                "Name :" + name +
                ", Adress :" + adress +
                ", PhoneNumber :" + phoneNumber +
                '}'+"\n";
    }
}
