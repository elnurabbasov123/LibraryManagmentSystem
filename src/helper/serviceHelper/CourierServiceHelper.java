package helper.serviceHelper;

import helper.menuHelper.CustomerMenuHelper;
import model.Courier;
import model.Customer;
import model.Order;
import service.impl.IOrderService;
import service.inter.OrderService;
import utility.InputUtil;

public class CourierServiceHelper {


    public Courier fill(){
        Courier courier=null;

        String name = InputUtil.getInstance().inputString("Name:");
        String vehicleType = InputUtil.getInstance().inputString("Vehicle type:");
        String vehiclePlate = InputUtil.getInstance().inputString("Vehicle Plate:");
        String phoneNumber = InputUtil.getInstance().inputString("PhoneNumber :");

        courier=new Courier(name,vehicleType,vehiclePlate, phoneNumber,true);

        return courier;
    }

}
