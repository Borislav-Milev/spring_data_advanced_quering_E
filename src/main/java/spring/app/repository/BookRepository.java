package spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import spring.app.domain.entity.Book;
import spring.app.domain.entity.enums.AgeRestriction;
import spring.app.domain.entity.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {


    Set<Book> getAllByAgeRestriction(AgeRestriction ageRestriction);

    Set<Book> getAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    Set<Book> getAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice);

    @Query(value = "select * from books where year(release_date) != ?1 ", nativeQuery = true)
    Set<Book> booksNotInYear(String year);

    Set<Book> getAllByReleaseDateBefore(LocalDate date);

    Set<Book> getAllByTitleContaining(String str);

    @Query("select b from Book b where b.author.lastName like concat(?1, '%')")
    Set<Book> getBooksWhereAuthorLastNameStartWithStr(String startStr);

    @Query("select count(b) from Book b where length(b.title) > ?1")
    int getAllByTitleLength(int length);

    Book getFirstByTitleLike(String title);

    @Modifying
    @Query("update Book b set b.copies = b.copies + ?1 where b.releaseDate > ?2")
    Integer increaseCopiesAfterReleaseDate(int copies, LocalDate date);

    @Modifying
    @Query("delete from Book b where b.copies < ?1")
    Integer removeBookByCopiesLessThan(int amount);
}