package spring.app.service.contract;

import spring.app.domain.entity.Book;

import java.util.Set;

public interface BookService extends BaseInterface {

    Set<Book> getAllBooksByAgeRestriction(String ageRestriction);

    Set<Book> goldenEditionBooks();

    Set<Book> booksByPrice();

    Set<Book> booksNotInYear(String year);

    Set<Book> booksBeforeReleaseDate(String beforeDate);

    Set<Book> booksContainingStr(String str);

    Set<Book> authorsLastNameStartWith(String endStr);

    Integer getCountOfBooksByTitleLength(int length);

    Book getBookByTitle(String title);

    Integer updateBookCopiesAfterReleaseDate(int copies, String date);

    Integer removeBooksWhereCopiesAreLessThan(int amount);
}
