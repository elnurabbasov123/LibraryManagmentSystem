package service.inter;

import model.Courier;
import response.GeneralResponse;

import java.util.List;

public interface CourierService {
    GeneralResponse<Courier> add();
    List<Courier> getAll();
    List<Courier> emptyCouriers();
    void changeStatus(boolean status, int id);
}
