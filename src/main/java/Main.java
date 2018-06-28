import com.softserve.edu.library.dao.BookDao;
import com.softserve.edu.library.entity.Book;

public class Main {
    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        Book book = bookDao.getById("4");
        System.out.println(book);

    }
}
