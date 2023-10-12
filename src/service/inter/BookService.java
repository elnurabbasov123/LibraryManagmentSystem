package service.inter;

import model.Book;
import response.GeneralResponse;

import java.util.List;

public interface BookService {
    GeneralResponse<Book> add();
    List<Book> getAll();
    void changeCount(Book book,String field,String sign);
    List<Book> topBooks();
}
