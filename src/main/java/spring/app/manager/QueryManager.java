package spring.app.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.app.domain.entity.Book;
import spring.app.service.contract.AuthorService;
import spring.app.service.contract.BookService;
import spring.app.util.contract.InputReader;

import static spring.app.constants.Messages.*;


@Component
@Transactional(readOnly = true)
public class QueryManager implements Runnable {
    private final AuthorService authorService;
    private final BookService bookService;
    private final InputReader reader;

    @Autowired
    public QueryManager(AuthorService authorService, BookService bookService, InputReader reader) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.reader = reader;

    }

    @Override
    @Transactional
    public void run() {
        while (true) {
            System.out.println(INSTRUCTIONS);
            int input;
            try {
                input = Integer.parseInt(this.reader.read());
            }catch (NumberFormatException e){
                System.out.println("Not a number.");
                continue;
            }
            if (input == 0) break;
            switch (input) {
                case 1:
                    System.out.println(INPUT_BOOKS_AGE_RESTRICTION);
                    this.bookService.getAllBooksByAgeRestriction(this.reader.read())
                            .forEach(book -> System.out.println(book.getTitle()));
                    break;
                case 2:
                    this.bookService.goldenEditionBooks()
                            .forEach(book -> System.out.println(book.getTitle()));
                    break;
                case 3:
                    this.bookService.booksByPrice()
                            .forEach(book -> System.out.printf("%s - %s%n",
                                    book.getTitle(), book.getPrice()));
                    break;
                case 4:
                    System.out.println(INPUT_YEAR);
                    this.bookService.booksNotInYear(this.reader.read())
                            .forEach(book -> System.out.println(book.getTitle()));
                    break;
                case 5:
                    System.out.println(INPUT_DATE);
                    this.bookService.booksBeforeReleaseDate(this.reader.read())
                            .forEach(book -> System.out.printf("%s %s %s%n",
                                    book.getTitle(), book.getEditionType(), book.getPrice()));
                    break;
                case 6:
                    System.out.println(INPUT_STRING);
                    this.authorService.authorsEndingWith(this.reader.read())
                            .forEach(author -> System.out.printf("%s %s%n",
                                    author.getFirstName(), author.getLastName()));
                    break;
                case 7:
                    System.out.println(INPUT_STRING);
                    this.bookService.booksContainingStr(this.reader.read())
                            .forEach(book -> System.out.println(book.getTitle()));
                    break;
                case 8:
                    System.out.println(INPUT_STRING);
                    this.bookService.authorsLastNameStartWith(this.reader.read())
                            .forEach(book -> System.out.printf("%s (%s %s)%n",
                                    book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName()));
                    break;
                case 9:
                    System.out.println(INPUT_TITLE_LENGTH);
                    input = Integer.parseInt(this.reader.read());
                    System.out.printf(OUTPUT_STRING,
                            this.bookService.getCountOfBooksByTitleLength(input), input);
                    break;
                case 10:
                    this.authorService.authorWithTotalBookCopies()
                            .forEach(System.out::println);
                    break;
                case 11:
                    System.out.println(INPUT_TITLE);
                    Book book = this.bookService.getBookByTitle(this.reader.read());
                    System.out.printf("%s %s %s %s%n",
                            book.getTitle(), book.getEditionType(), book.getAgeRestriction(), book.getPrice());
                    break;
                case 12:
                    System.out.println(INPUT_DATE_AFTER);
                    String date = this.reader.read();
                    System.out.println(INPUT_COPIES_TO_ADD);
                    input = Integer.parseInt(this.reader.read());
                    Integer output = this.bookService.updateBookCopiesAfterReleaseDate(input, date);
                    System.out.printf(OUTPUT_INFORMATION, output, date.trim(), input * output);
                    break;
                case 13:
                    System.out.println(INPUT_AMOUNT_OF_COPIES_FOR_REMOVAL);
                    input = Integer.parseInt(reader.read());
                    Integer booksRemoved = this.bookService.removeBooksWhereCopiesAreLessThan(input);
                    System.out.printf(RESULT_BOOKS_REMOVED, booksRemoved);
                    System.out.println(EXIT_APP);
                    return;
                case 14:
                    System.out.println(INPUT_AUTHOR_FIRST_NAME);
                    String firstName = this.reader.read();
                    System.out.println(INPUT_AUTHOR_LAST_NAME);
                    String lastName = this.reader.read();
                    Integer count = this.authorService.amountOfBooksAuthorHasWritten(firstName, lastName);
                    if(count == null) {
                        System.out.printf(NO_AUTHOR_FOUND, firstName, lastName);
                        continue;
                    }
                    System.out.printf(AUTHOR_BOOKS_COUNT, firstName, lastName, count);
                    break;
                case 15:
                    try {
                        this.authorService.createProcedure();
                    }catch (Exception e){
                        System.out.println(PROCEDURE_ALREADY_CREATED);
                        continue;
                    }
                    System.out.println(CREATED_PROCEDURE);
                    break;
                default:
                    System.out.println(NO_SUCH_EXERCISE);
            }
        }
    }
}
