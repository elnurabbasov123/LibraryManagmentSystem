package repository.impl;

import connection.ConnectionConfig;
import model.Library;
import repository.inter.LibraryRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ILibraryRepository implements LibraryRepository {
    @Override
    public List<Library> getAll() {
        List<Library> libraries = new ArrayList<>();
        Library library = null;

        try {
            Connection connection = ConnectionConfig.connection();
            String sql = "select * from library_data.library";
            Statement statement = connection.createStatement();
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String adress = resultSet.getString(3);
                String phoneNumber = resultSet.getString(4);

                library = new Library(id, name, adress, phoneNumber);
                libraries.add(library);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return libraries;
    }
    }