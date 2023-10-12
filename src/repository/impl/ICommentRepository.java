package repository.impl;

import connection.ConnectionConfig;
import model.Comment;
import repository.inter.CommentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ICommentRepository implements CommentRepository {
    @Override
    public void add(Comment comment) {
        try {
            Connection connection = ConnectionConfig.connection();
            String sql="insert into library_data.comment(comment,book_id)" +
                    "values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,comment.getComment());
            preparedStatement.setInt(2,comment.getBookId());
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getByBookId(int id) {
        List<String> comments=new ArrayList<>();

        try {
            Connection connection = ConnectionConfig.connection();
            String sql="select * from library_data.comment where book_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()){
                String comment = resultSet.getString(2);
                comments.add(comment);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }
}

