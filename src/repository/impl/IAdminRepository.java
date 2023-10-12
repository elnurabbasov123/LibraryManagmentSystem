package repository.impl;

import connection.ConnectionConfig;
import model.Admin;
import repository.inter.AdminRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IAdminRepository implements AdminRepository {
    @Override
    public List<Admin> getAdmins() {
        List<Admin> admins=new ArrayList<>();
        try {
            Connection connection= ConnectionConfig.connection();
            String sql="select * from library_data.admin";
            Statement statement=connection.createStatement();
            statement.execute(sql);
            ResultSet resultSet=statement.getResultSet();

            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String name=resultSet.getString(2);
                String post=resultSet.getString(3);
                String login=resultSet.getString(4);
                String password=resultSet.getString(5);

                Admin admin=new Admin(id,name,post,login,password);

                admins.add(admin);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }
}
