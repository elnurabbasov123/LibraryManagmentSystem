package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.impl.IBookService;
import service.impl.ICourierService;
import service.impl.ICustomerService;
import service.inter.BookService;
import service.inter.CourierService;
import service.inter.CustomerService;
import utility.Searcher;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;
    private int customerId;
    private int courierId;
    private int libraryId;
    private int bookId;

    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private double paymentAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;

    CustomerService customerService=new ICustomerService();
    BookService bookService=new IBookService();
    CourierService courierService=new ICourierService();

    public Order(int customerId, int courierId, int libraryId, int bookId, LocalDateTime orderDate, LocalDateTime deliveryDate, double paymentAmount, LocalDateTime startDate, LocalDateTime endDate, String status) {
        this.customerId = customerId;
        this.courierId = courierId;
        this.libraryId = libraryId;
        this.bookId = bookId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.paymentAmount = paymentAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Order(int id, int customerId, int courierId, int libraryId, int bookId, LocalDateTime orderDate, LocalDateTime deliveryDate, double paymentAmount, LocalDateTime startDate, LocalDateTime endDate, String status) {
        this.id = id;
        this.customerId = customerId;
        this.courierId = courierId;
        this.libraryId = libraryId;
        this.bookId = bookId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.paymentAmount = paymentAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    @Override
    public String toString() {
        Customer customer = customerService.getById(customerId);
        Book book = Searcher.search(bookService.getAll(),book1 -> book1.getId()==bookId);
        Courier courier = Searcher.search(courierService.getAll(),courier1 -> courier1.getId()==courierId);


        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yy-HH':'mm");
        return "Order{\n"+
                "Start Date :"+formatter.format(startDate)+","+
                "End Date :"+formatter.format(endDate)+","+
                "Username :" + customer.getEmail() +"," +
                ","+
                "Book name :" +book.getName()+","+
                "Book author :" + book.getAuthor() +"," +
                "," +
                "Status:"+status+","+
                "Courier name :" + courier.getName() +","+
                "Courier PhoneNumber :" + courier.getPhoneNumber()+","+
                "Courier VehicleType :" + courier.getVehicleType() +","+
                "Courier VehiclePlate :" + courier.getVehiclePlate() +",n" +
                ","+
                "Cash :" + paymentAmount +"$\n"+
                '}';
    }
}
