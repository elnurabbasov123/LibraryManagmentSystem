package helper.menuHelper;

import enums.Exception;
import exception.GeneralException;
import model.*;
import service.impl.*;
import service.inter.*;
import utility.BookComparable;
import utility.InputUtil;
import utility.Searcher;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerMenuHelper {
    static BookService bookService = new IBookService();
    static MenuHelper menuServiceHelper = new MenuHelper();
    static CustomerService customerService = new ICustomerService();
    static PaymentService paymentService = new IPaymentService();
    static CourierService courierService = new ICourierService();
    static LibraryService libraryService = new ILibraryService();
    CommentService commentService=new ICommentService();
    OrderService orderService=new IOrderService();
    static MenuService menuService=new IMenuService();

    public void customerLoginMenu() {

        int option = selectCustomerLoginMenuOption();

        switch (option) {
            case 0:
                System.exit(0);
                break;
            case 1:
                Customer logginedCustomer = customerLogin();

                int selectedOption = customerOrderMenuSelect();

                customerOrderMenu(logginedCustomer, selectedOption);
                break;
            case 2:
                Customer logined= customerService.signUp();
                Customer search = Searcher.search(customerService.getAll(), customer -> customer.getEmail().equals(logined.getEmail()));

                int option2= customerOrderMenuSelect();

                customerOrderMenu(search,option2);
                break;
            case 3:
                menuService.menu();
                break;
            default:
                throw new GeneralException(Exception.OPERATION_NOT_FOUND_EXCEPTION);
        }
    }

    private void customerOrderMenu(Customer logginedCustomer, int selectedOption) {
        Order order=null;
        switch (selectedOption) {
            case 0:
                System.exit(0);
                break;
            case 1:
                customerLoginMenu();
                break;
            case 2:
                orderService.changeStatus();
                menuServiceHelper.checkCourier();
                customerService.placeOrder(logginedCustomer);
                customerOrderMenu(logginedCustomer,customerOrderMenuSelect());
                break;
            case 3:
                addComment(logginedCustomer);
                customerOrderMenu(logginedCustomer,customerOrderMenuSelect());
                break;
            case 4:
                likeOrDislake(logginedCustomer);
                customerOrderMenu(logginedCustomer,customerOrderMenuSelect());
                break;
            case 5:
                showBooks(logginedCustomer);
                customerOrderMenu(logginedCustomer,customerOrderMenuSelect());
            case 6:
                searchBook();
                customerOrderMenu(logginedCustomer,customerOrderMenuSelect());
                break;
            case 7:
                orderService.changeStatus();
                trackOrders(logginedCustomer);
                customerOrderMenu(logginedCustomer,customerOrderMenuSelect());
                break;
            case 8:
                orderService.changeStatus();
                cancelOrder(logginedCustomer);
                customerOrderMenu(logginedCustomer,customerOrderMenuSelect());
                break;
            default:
                throw new GeneralException(Exception.OPERATION_NOT_FOUND_EXCEPTION);
        }
    }

    private void showBooks(Customer logginedCustomer) {
        int option = InputUtil.getInstance().inputInt("[0]->Back\n" +
                "[1]->All books\n" +
                "[2]->Top 3 books\n" +
                "Select:");
        switch (option){
            case 0:
                customerOrderMenu(logginedCustomer,customerOrderMenuSelect());
                break;
            case 1:
                bookService.getAll().forEach(System.out::println);
                break;
            case 2:
//                bookService.topBooks().forEach(System.out::println);
                bookService.getAll().stream().sorted(new BookComparable())
                        .limit(3).forEach(System.out::println);
                break;
            default:
                throw new GeneralException(Exception.OPERATION_NOT_FOUND_EXCEPTION);
        }

    }

    private void cancelOrder(Customer customer) {
        List<Order> orders = customer.getOrders();
        List<Order> onTheWay=new ArrayList<>();

        int count=0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getStatus().equals("on the way")){
                count++;
                System.out.println("["+count+"]."+orders.get(i));
                onTheWay.add(orders.get(i));
            }
        }
        if (count==0){
            throw new GeneralException(Exception.DONT_HAVE_ORDER_EXCEPTION);
        }
        int orderNum = InputUtil.getInstance().inputInt("Which order do you want to cancel ? :");

        if (orderNum<=0||orderNum>count){
            throw  new GeneralException(Exception.OPERATION_NOT_FOUND_EXCEPTION);
        }

        Order canceledOrder=onTheWay.get(orderNum-1);
        int cancelOrderId = onTheWay.get(orderNum-1).getId();

        int bookId = canceledOrder.getBookId();
        Book searchedBook = Searcher.search(bookService.getAll(), book -> book.getId() == bookId);

        double capital=customer.getCapital()+ canceledOrder.getPaymentAmount();

        LocalDateTime now=LocalDateTime.now();
        LocalDateTime localDateTime = canceledOrder.getDeliveryDate().minusMinutes(30);
        if(now.isBefore(localDateTime)){
            customerService.updateCapital(customer,capital);
            orderService.cancelById(cancelOrderId);
            System.out.println("Order Canceled Successfully");
            bookService.changeCount(searchedBook,"copies_available","plus");
            bookService.changeCount(searchedBook,"buy_count","minus");
            courierService.changeStatus(true,canceledOrder.getCourierId());
        }else {
            throw new GeneralException(Exception.DONT_CANCELED_EXCEPTION);
        }
    }

    private void showCustomerBooks(List<Book> books) {
        books.forEach(System.out::println);
    }

    private void trackOrders(Customer logginedCustomer) {
        List<Order> orders = logginedCustomer.getOrders();
        LocalDateTime now = LocalDateTime.now();



        for (int i = 0; i < orders.size(); i++) {
            if (now.isBefore(orders.get(i).getDeliveryDate())){
                Duration between = Duration.between(now, orders.get(i).getDeliveryDate());
                long seconds = between.getSeconds();
                long minutes=seconds/60;
                System.out.println("You order is on the way ,"+"It will be delivered in "+minutes+" minutes");
            }else {
                System.out.println("Delivered ");
            }
            System.out.println(orders.get(i));
        }
    }

    private void searchBook() {
        String bookName = InputUtil.getInstance().inputString("Book Name:");
        Book book = Searcher.search(bookService.getAll(),book1 -> book1.getName().equals(bookName));
        System.out.println(book);
    }

    private void likeOrDislake(Customer logginedCustomer) {
        logginedCustomer.getBooks().forEach(System.out::println);

        int idBook = InputUtil.getInstance().inputInt("Which id book do you want to rate ?:");

        boolean ok=false;
        for (int i = 0; i < logginedCustomer.getBooks().size(); i++) {
            if (logginedCustomer.getBooks().get(i).getId()==idBook){
                ok=true;
            }
        }
        if (!ok){
            throw  new GeneralException(Exception.OPERATION_NOT_FOUND_EXCEPTION);
        }
        Book searchedBook = Searcher.search(bookService.getAll(), book -> book.getId() == idBook);
        System.out.println("Book Name:"+searchedBook.getName()+"\n" +
                "Book Author :"+searchedBook.getName()+"\n");

        int option = InputUtil.getInstance().inputInt("[1]->Like\n" +
                "[2]->Dislake\n" +
                "Select :");
        switch (option){
            case 1:
                bookService.changeCount(searchedBook,"likes","plus");
                break;
            case 2:
                bookService.changeCount(searchedBook,"dislikes","minus");
                break;
            default:
                throw new GeneralException(Exception.OPERATION_NOT_FOUND_EXCEPTION);
        }
    }




    private Customer checkCustomer(String login, String password) {

        List<Customer> customers = customerService.getAll();
        Customer customer = null;

        boolean isCustomer = false;

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmail().equals(login)
                    && customers.get(i).getPassword().equals(password)) {
                isCustomer = true;
                customer = customers.get(i);
            }
        }
        if (isCustomer) {
            return customer;
        } else {
            throw new GeneralException(Exception.PASSWORD_OR_LOGIN_IS_WRONG_EXCEPTION);
        }
    }

    private Customer customerLogin() {
        String email = InputUtil.getInstance().inputString("Email :");
        String password = InputUtil.getInstance().inputString("Password :");

        Customer logginedCustomer = checkCustomer(email, password);

        return logginedCustomer;
    }

    private static Courier getCourier() {
        List<Courier> emptyCouriers = courierService.emptyCouriers();

        if(emptyCouriers.size()>0) {
            Random random = new Random();
            int randomNum = random.nextInt(emptyCouriers.size());
            Courier courier = emptyCouriers.get(randomNum);

            return courier;
        }else {
            throw new GeneralException(Exception.DONT_HAVE_EMPTY_COURIER_EXCEPTION);
        }
    }

    public static Library chooseLibrary() {
        List<Library> libraries = libraryService.getAll();
        libraries.forEach(System.out::println);
        Library choosedLibrary = Searcher.search(libraries, library -> library.getId() == InputUtil.getInstance().inputInt("Library Id:"));

        return choosedLibrary;
    }

    public static Order makeOrder(Customer logginedCustomer) {
        Library library = chooseLibrary();
        List<Book> books = menuServiceHelper.showLibraryBooks(library);
        Book selectedBook = menuServiceHelper.selectBook(books);
        System.out.println("This Book Comments :");
        selectedBook.getComments().stream().forEach(System.out::println);

        System.out.println();
        int rentDays = InputUtil.getInstance().inputInt("How many days do you want rent a book ? :");

        Courier courier = getCourier();
        courier.setEmpty(false);
        courierService.changeStatus(false, courier.getId());

        double paymentAmount = paymentService.payment(logginedCustomer, rentDays, selectedBook,courier);

        LocalDateTime orderDate = LocalDateTime.now();
        LocalDateTime deliveryDate = orderDate.plusMinutes(60);
        LocalDateTime startDate = orderDate;
        LocalDateTime endDate = startDate.plusMinutes(rentDays * 24 * 60);

        String status="on the way";



        Order order = new Order(logginedCustomer.getId(), courier.getId(), library.getId()
                , selectedBook.getId(), orderDate, deliveryDate, paymentAmount,
                startDate, endDate,status);

        return order;
    }

    private void addComment(Customer customer){
        List<Book> books = customer.getBooks();

        books.forEach(System.out::println);
        int bookId = InputUtil.getInstance().inputInt("Select Book :");
        String comment = InputUtil.getInstance().inputString("Your comment :");
        Comment comment1=new Comment(comment,bookId);
        commentService.add(comment1);
    }
    private int selectCustomerLoginMenuOption() {
        int option = InputUtil.getInstance().inputInt("---------------- Customer Menu ----------------\n" +
                "[0]->Exit\n" +
                "[1]->Login\n" +
                "[2]->Sign Up\n" +
                "[3]->Back\n" +
                "Select :");

        return option;
    }

    private int customerOrderMenuSelect() {
        int option = InputUtil.getInstance().inputInt("---------------- Customer Order Menu ----------------\n" +
                "[0]->Exit\n" +
                "[1]->Back\n" +
                "[2]->Place Order\n" +
                "[3]->Add a comment\n" +
                "[4]->Like or Dislake\n" +
                "[5]->View Books\n" +
                "[6]->Search Book\n" +
                "[7]->Track Orders\n" +
                "[8]->Cancel Order\n" +
                "Select :");

        return option;
    }
    public static void cachReceipt(Order order) {
        Customer customer = customerService.getById(order.getCustomerId());
        Book book = Searcher.search(bookService.getAll(), book1 -> book1.getId() == order.getBookId());
        Courier courier = Searcher.search(courierService.getAll(), courier1 -> courier1.getId() == order.getCourierId());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yy-HH':'mm");
        System.out.println("---------------- Cash Receipt ----------------\n" +
                "Start Date :" + formatter.format(order.getStartDate()) + "\n" +
                "End Date :" + formatter.format(order.getEndDate()) + "\n" +
                "Username :" + customer.getEmail() + "\n" +
                "\n" +
                "Book name :" + book.getName() + "\n" +
                "Book author :" + book.getAuthor() + "\n" +
                "\n" +
                "Courier name :" + courier.getName() +"\n" +
                "Courier PhoneNumber :" + courier.getPhoneNumber() + "\n" +
                "Courier VehicleType :" + courier.getVehicleType() + "\n" +
                "Courier VehiclePlate :" + courier.getVehiclePlate() + "\n" +
                "\n" +
                "Cash :" + order.getPaymentAmount() + "$\n");
    }

    public Customer fillCustomer() {

        String name = InputUtil.getInstance().inputString("Name :");
        String surname = InputUtil.getInstance().inputString("Surname :");
        String email = InputUtil.getInstance().inputString("Email :");
        String phone = InputUtil.getInstance().inputString("Phone :");
        String password = InputUtil.getInstance().inputString("Password :");
        Double capital = InputUtil.getInstance().inputDouble("Capital :");

        return new Customer(name,surname,email,phone,password,capital);
    }
}
