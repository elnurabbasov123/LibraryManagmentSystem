package helper.serviceHelper;

import model.Book;
import utility.InputUtil;

public class BookServiceHelper {
    public Book fillBook(){
        String title = InputUtil.getInstance().inputString("Name:");
        String author = InputUtil.getInstance().inputString("Author:");
        String genre=InputUtil.getInstance().inputString("Genre:");
        int copies = InputUtil.getInstance().inputInt("Copies available:");
        int likes = InputUtil.getInstance().inputInt("Likes:");
        int dislakes = InputUtil.getInstance().inputInt("Dilakes:");
        int buyCount = 0;
        int libraryId = InputUtil.getInstance().inputInt("Library Id:");
        double price=InputUtil.getInstance().inputDouble("Price:");


        Book book =new Book(libraryId,title,author,genre,copies,likes,dislakes,buyCount,price);

        return book;
    }

}
