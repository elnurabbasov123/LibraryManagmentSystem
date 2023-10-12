package service.impl;

import enums.Exception;
import exception.GeneralException;
import model.Book;
import repository.impl.IBookRepository;
import repository.inter.BookRepository;
import response.GeneralResponse;
import service.inter.BookService;

import java.util.List;

public class IBookService implements BookService {

    BookRepository bookRepository = new IBookRepository();

    @Override
    public GeneralResponse<Book> add() {
        return bookRepository.add();
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public void changeCount(Book book,String field,String sign) {
        bookRepository.changeCount(book,field,sign);
    }
    @Override
    public List<Book> topBooks() {
        return bookRepository.topBooks();
    }
}


