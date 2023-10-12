package repository.impl;
import connection.ConnectionConfig;
import helper.serviceHelper.BookServiceHelper;
import model.Book;
import repository.inter.BookRepository;
import response.GeneralResponse;
import service.impl.ICommentService;
import service.inter.CommentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IBookRepository implements BookRepository {
    BookServiceHelper bookServiceHelper=new BookServiceHelper();
    CommentService commentService=new ICommentService();


    @Override
    public GeneralResponse<Book> add() {
        Book book = bookServiceHelper.fillBook();

        try {
            Connection connection= ConnectionConfig.connection();
            String sql="insert into library_data.book(name,author,copies_available,rent_price,genre,likes,dislakes,buy_count,library_id) \n" +
                    "values(?,?,?,?,?,?,?,?,?);";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,book.getName());
            statement.setString(2,book.getAuthor());
            statement.setInt(3,book.getCopiesAvailable());
            statement.setDouble(4,book.getRentPrice());
            statement.setString(5,book.getGenre());
            statement.setInt(6,book.getLikes());
            statement.setInt(7,book.getDislakes());
            statement.setInt(8,book.getBuyCount());
            statement.setInt(9,book.getLibraryId());

            statement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new GeneralResponse<Book>().of("Book Added Succesfully",book);
    }

    @Override
    public List<Book> getAll() {

        List<Book> books=new ArrayList<>();
        Book book=null;

        try {
            Connection connection=ConnectionConfig.connection();
            String sql="select * from library_data.book";
            Statement statement=connection.createStatement();
            statement.execute(sql);

            ResultSet rs = statement.getResultSet();

            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String author = rs.getString(3);
                int copies = rs.getInt(4);
                int like = rs.getInt(5);
                int dislike = rs.getInt(6);
                int buyCount = rs.getInt(7);
                int libraryIdd = rs.getInt(8);
                double rentPrice = rs.getDouble(9);
                String genre = rs.getString(10);

                List<String> comments = commentService.getByBookId(id);

                book=new Book(id,libraryIdd,name,author,genre,copies,like,dislike,buyCount,rentPrice);
                book.setComments(comments);

                books.add(book);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return books;
    }

    @Override
    public void changeCount(Book book, String field,String sign) {
        int count = book.getField(field);
        switch (sign){
            case "plus":
                count++;
                break;
            case "minus":
                count--;
                break;
        }
        try {
            Connection connection = ConnectionConfig.connection();
            String sql="update library_data.book set "+field+"=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,count);
            preparedStatement.setInt(2,book.getId());
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> topBooks() {

        List<Book> books=new ArrayList<>();
        List<Book> topBooks=new ArrayList<>();
        Book book=null;

        try {
            Connection connection=ConnectionConfig.connection();
            String sql="select * from library_data.book bb order by bb.buy_count DESC ";
            Statement statement=connection.createStatement();
            statement.execute(sql);

            ResultSet rs = statement.getResultSet();

            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String author = rs.getString(3);
                int copies = rs.getInt(4);
                int like = rs.getInt(5);
                int dislike = rs.getInt(6);
                int buyCount = rs.getInt(7);
                int libraryId = rs.getInt(8);
                double rentPrice = rs.getDouble(9);
                String genre = rs.getString(10);

                List<String> comments = commentService.getByBookId(id);

                book=new Book(id,libraryId,name,author,genre,copies,like,dislike,buyCount,rentPrice);
                book.setComments(comments);

                books.add(book);


            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        topBooks.add(books.get(0));
        topBooks.add(books.get(1));
        topBooks.add(books.get(2));

        return topBooks;
    }
}
