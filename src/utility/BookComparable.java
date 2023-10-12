package utility;
import model.Book;
import java.util.Comparator;
public class BookComparable implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getBuyCount() < o2.getBuyCount() ? 1 : 0;
    }
}
