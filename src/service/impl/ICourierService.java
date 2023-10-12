package service.impl;
import connection.ConnectionConfig;
import exception.GeneralException;
import model.Courier;
import repository.impl.ICourierRepository;
import repository.inter.CourierRepository;
import response.GeneralResponse;
import service.inter.CourierService;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ICourierService implements CourierService {
    CourierRepository courierRepository=new ICourierRepository();

    @Override
    public GeneralResponse<Courier> add() {
        return courierRepository.add();
    }

    @Override
    public List<Courier> getAll() {
        return courierRepository.getAll();
    }

    @Override
    public List<Courier> emptyCouriers() {
       return courierRepository.emptyCouriers();
    }

    @Override
    public void changeStatus(boolean status,int id) {
        courierRepository.changeStatus(status,id);
    }
}
