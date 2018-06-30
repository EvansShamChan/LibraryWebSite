import com.softserve.edu.library.dao.BookDao;
import com.softserve.edu.library.entity.Book;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
//        Book book = bookDao.getById("4");
//        System.out.println(book);

        List<Book> books = bookDao.getAllBooks();
        System.out.println(books);

//
//        AuthorToBookDao authorToBookDao = new AuthorToBookDao();
//        List <AuthorToBook> authorToBook = authorToBookDao.getAuthorByBookId("4");
//        System.out.println(authorToBook);
//
//        AuthorDao authorDao = new AuthorDao();
//        Author author = authorDao.getById("4");
//        System.out.println(author);



    }
}
