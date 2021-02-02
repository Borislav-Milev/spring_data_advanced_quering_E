package spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.app.constants.FilePaths;
import spring.app.constants.Messages;
import spring.app.domain.entity.Book;
import spring.app.domain.entity.enums.AgeRestriction;
import spring.app.domain.entity.enums.EditionType;
import spring.app.repository.BookRepository;
import spring.app.service.contract.BookService;
import spring.app.util.contract.DefineBook;
import spring.app.util.contract.FileUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final FileUtil fileUtil;
    private final BookRepository bookRepository;
    private final DefineBook defineBook;

    @Autowired
    public BookServiceImpl(FileUtil fileUtil, BookRepository bookRepository,
                           DefineBook defineBook) {
        this.fileUtil = fileUtil;
        this.bookRepository = bookRepository;
        this.defineBook = defineBook;
    }

    @Override
    public Set<Book> getAllBooksByAgeRestriction(String ageRestrictionStr) {
        AgeRestriction ageRestriction =  null;
        try {
            ageRestriction = AgeRestriction.valueOf(ageRestrictionStr.toUpperCase());
        }catch (IllegalArgumentException e){
            System.out.println(Messages.NO_SUCH_AGE_RESTRICTION);
        }
        return this.bookRepository.getAllByAgeRestriction(ageRestriction);
    }

    @Override
    public Set<Book> goldenEditionBooks() {
        EditionType editionType = EditionType.GOLD;
        int copies = 5000;
        return this.bookRepository.getAllByEditionTypeAndCopiesLessThan(editionType, copies);
    }

    @Override
    public Set<Book> booksByPrice() {
        BigDecimal lessThan = new BigDecimal("5");
        BigDecimal greaterThan = new BigDecimal("40");
        return this.bookRepository.getAllByPriceLessThanOrPriceGreaterThan(lessThan, greaterThan);
    }

    @Override
    public Set<Book> booksNotInYear(String year) {
        return this.bookRepository.booksNotInYear(year);
    }

    @Override
    public Set<Book> booksBeforeReleaseDate(String beforeDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate formatted = LocalDate.parse(beforeDate, formatter);
        return this.bookRepository.getAllByReleaseDateBefore(formatted);
    }

    @Override
    public Set<Book> booksContainingStr(String str) {
        return this.bookRepository.getAllByTitleContaining(str);
    }

    @Override
    public Set<Book> authorsLastNameStartWith(String endStr) {
        return this.bookRepository.getBooksWhereAuthorLastNameStartWithStr(endStr);
    }

    @Override
    public Integer getCountOfBooksByTitleLength(int length) {
        return this.bookRepository.getAllByTitleLength(length);
    }

    @Override
    public Book getBookByTitle(String title) {
        return this.bookRepository.getFirstByTitleLike(title);
    }

    @Override
    public Integer updateBookCopiesAfterReleaseDate(int copies, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy", Locale.ENGLISH);
        LocalDate inputDate = LocalDate.parse(date, formatter);
        return this.bookRepository.increaseCopiesAfterReleaseDate(copies, inputDate);
    }

    @Override
    public Integer removeBooksWhereCopiesAreLessThan(int amount) {
        return this.bookRepository.removeBookByCopiesLessThan(amount);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T findEntityById(Long id) {
        return (T) this.bookRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void seedEntities() {
        if (this.getCount() != 0) {
            System.out.println(Messages.FILLED_BOOKS);
            return;
        }
        for (String bookString : this.fileUtil.readFileContent(FilePaths.BOOKS_FILE_PATH)) {
            Book book = this.defineBook.getBookValues(bookString.split("\\s+"));
            this.bookRepository.saveAndFlush(book);
        }
    }

    @Override
    public int getCount() {
        return Math.toIntExact(this.bookRepository.count());
    }
}
