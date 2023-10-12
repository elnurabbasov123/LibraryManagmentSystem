package repository.impl;

import connection.ConnectionConfig;
import helper.serviceHelper.CourierServiceHelper;
import model.Courier;
import repository.inter.CourierRepository;
import response.GeneralResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ICourierRepository implements CourierRepository {
    CourierServiceHelper courierServiceHelper = new CourierServiceHelper();

    @Override
    public GeneralResponse<Courier> add() {
        Courier courier = courierServiceHelper.fill();

        try {
            Connection connection = ConnectionConfig.connection();
            String sql = "insert into library_data.courier(name,vehicle_type,vehicle_plate,is_empty,phone_number)" +
                    " values(?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, courier.getName());
            preparedStatement.setString(2, courier.getVehicleType());
            preparedStatement.setString(3, courier.getVehiclePlate());
            preparedStatement.setBoolean(4, courier.isEmpty());
            preparedStatement.setString(5,courier.getPhoneNumber());

            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new GeneralResponse<Courier>().of("Courier Added Succesfully", courier);
    }

    @Override
    public List<Courier> getAll() {
        List<Courier> couriers = new ArrayList<>();
        Courier courier = null;

        try {
            Connection connection = ConnectionConfig.connection();
            String sql = "select * from library_data.courier";
            Statement statement = connection.createStatement();
            statement.execute(sql);

            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String vehicleType = rs.getString(3);
                String vehiclePlate = rs.getString(4);
                boolean isEmpty = rs.getBoolean(5);
                String phoneNumber = rs.getString(6);

                courier = new Courier(id, name, vehicleType, vehiclePlate, phoneNumber,isEmpty);
                couriers.add(courier);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return couriers;
    }

    @Override
    public List<Courier> emptyCouriers() {
        List<Courier> emptyCouriers=new ArrayList<>();
        Courier courier=null;

        try {
            Connection connection=ConnectionConfig.connection();
            String sql="select * from library_data.courier where is_empty=true;";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String vehicleType = resultSet.getString(3);
                String vehiclePlate = resultSet.getString(4);
                boolean isEmpty = resultSet.getBoolean(5);
                String phoneNumber = resultSet.getString(6);

                courier=new Courier(id,name,vehicleType,vehiclePlate,phoneNumber,isEmpty);
                emptyCouriers.add(courier);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emptyCouriers;
    }

    @Override
    public void changeStatus(boolean status, int id) {
        try {
            Connection connection = ConnectionConfig.connection();
            String sql="update library_data.courier set is_empty=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1,status);
            preparedStatement.setInt(2,id);
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
