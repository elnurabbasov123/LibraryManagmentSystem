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
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String password;
    private double capital;

    private List<Order> orders;

    OrderService orderService=new IOrderService();
    BookService bookService=new IBookService();

    public Customer(String name, String surname, String email, String phoneNumber, String password, double capital) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.capital = capital;
    }

    public Customer(int id, String name, String surname, String email, String phoneNumber, String password, double capital, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.capital = capital;
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public List<Order> getOrders() {
        List<Order> customerOrders =orderService.getAll().stream().filter(order->order.getCustomerId()==id)
                .collect(Collectors.toList());

        return customerOrders;
    }
    public List<Book> getBooks(){
        List<Book> books=new ArrayList<>();

        List<Order> orders= getOrders();
        List<Integer> booksId = orders.stream().map(order -> order.getBookId())
                .collect(Collectors.toList());
        for (int i = 0; i < booksId.size(); i++) {
            for (int j = 0; j < bookService.getAll().size(); j++) {
                if (bookService.getAll().get(j).getId()==booksId.get(i)){
                    books.add(bookService.getAll().get(j));
                }
            }
        }
        return books;
    }
}

