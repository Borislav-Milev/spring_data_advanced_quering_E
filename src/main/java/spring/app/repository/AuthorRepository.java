package spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import spring.app.domain.entity.Author;

import java.util.Set;


public interface AuthorRepository extends JpaRepository<Author, Long> {

    Set<Author> findAllByFirstNameEndingWith(String endStr);

    @Query("select concat(a.firstName, ' ', a.lastName, ' - ', sum(b.copies)) "
    + "from Author a join a.books b group by a.id order by sum(b.copies) desc ")
    Set<String> authorsWithTotalBookCopies();

    @Modifying
    @Query(
    value = "create procedure udp_find_books_by_author(first_name varchar(20), last_name varchar(20)) " +
    "begin " +
    "select count(b.id) from authors a "+
    "join books b on a.id = b.author_id "+
    "where a.first_name like first_name "+
    "and a.last_name like last_name "+
    "group by a.id; "+
    "end; ", nativeQuery = true)
    void createProcedure();

    @Query(value = "call udp_find_books_by_author(?1, ?2)", nativeQuery = true)
    Integer P_amountOfBooksAuthorHasWritten(String firstName, String lastName);
}
