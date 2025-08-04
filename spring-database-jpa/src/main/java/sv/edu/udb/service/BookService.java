package sv.edu.udb.service;

import sv.edu.udb.domain.Book;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book saveBook(Book book);
    List<Book> findBooksByTitle(String title);
    void deleteBookById(Long id);
}
