package helper.menuHelper;

import enums.Exception;
import exception.GeneralException;
import model.Admin;
import model.Book;
import model.Courier;
import model.Customer;
import service.impl.*;
import service.inter.*;
import utility.InputUtil;
import java.util.List;

public class AdminMenuHelper {
    AdminService adminService=new IAdminService();
    BookService bookService=new IBookService();
    CourierService courierService=new ICourierService();
    CustomerService customerService=new ICustomerService();

    static MenuService menuService=new IMenuService();
    MenuHelper menuServiceHelper=new MenuHelper();

    public void adminMenu(Admin admin) {
        while (true) {
            int option = selectAdminMenuOption();

            switch (option) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    menuService.menu();
                    break;
                case 2:
                    bookService.add();
                    break;
                case 3:
                    courierService.add();
                    break;
                case 4:
                    menuServiceHelper.trackOrders();
                    break;
                case 5:
                    List<Book> books = bookService.getAll();
                    menuServiceHelper.viewObjects(books);
                    break;
                case 6:
                    List<Customer> customers = customerService.getAll();
                    menuServiceHelper.viewObjects(customers);
                    break;
                case 7:
                    List<Courier> couriers = courierService.getAll();
                    menuServiceHelper.viewObjects(couriers);
                    break;
                default:
                    throw new GeneralException(Exception.OPERATION_NOT_FOUND_EXCEPTION);
            }
        }
    }

    private int selectAdminMenuOption(){
        int option = InputUtil.getInstance().inputInt("---------------- Admin Menu ----------------\n" +
                "[0]->Exit\n" +
                "[1]->Back\n" +
                "[2]->Add Book\n" +
                "[3]->Add Courier\n" +
                "[4]->Track Orders\n" +
                "[5]->View Books\n" +
                "[6]->View Customers\n" +
                "[7]->View Couriers\n" +
                "Select :");
        return option;
    }
    public Admin adminLogin(){
        String login= InputUtil.getInstance().inputString("Login :");
        String password=InputUtil.getInstance().inputString("Password :");

        Admin admin = checkAdmin(login, password);

        return admin;
    }
    private Admin checkAdmin(String login,String password){
        Admin admin = null;

        boolean isAdmin=false;

        for (int i = 0; i < adminService.getAdmins().size(); i++) {
            if (adminService.getAdmins().get(i).getLogin().equals(login)
                    &&adminService.getAdmins().get(i).getPassword().equals(password)){
                isAdmin=true;
                admin=adminService.getAdmins().get(i);
            }
        }
        if (isAdmin){
            System.out.println("Hi mr. "+admin.getName());
        }else {
            throw new GeneralException(Exception.PASSWORD_OR_LOGIN_IS_WRONG_EXCEPTION);
        }
        return admin;
    }
}
