package helper.serviceHelper;

import helper.menuHelper.AdminMenuHelper;
import helper.menuHelper.CustomerMenuHelper;
import helper.menuHelper.MenuHelper;
import model.Admin;

public class MenuServiceHelper {
    AdminMenuHelper adminServiceHelper=new AdminMenuHelper();
    CustomerMenuHelper customerServiceHelper=new CustomerMenuHelper();
    public void menu(){
        int option = MenuHelper.selectMenu();

        switch (option){
            case 0:
                System.exit(0);
                break;
            case 1:
                Admin admin = adminServiceHelper.adminLogin();
                adminServiceHelper.adminMenu(admin);
                break;
            case 2:
                customerServiceHelper.customerLoginMenu();
                break;
            default: throw new RuntimeException();
        }
    }
}
