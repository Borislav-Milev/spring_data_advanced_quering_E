package spring.app.service.contract;

import spring.app.domain.entity.Author;

import java.util.Set;

public interface AuthorService extends BaseInterface {

    Set<Author> authorsEndingWith(String endStr);

    Set<String> authorWithTotalBookCopies();

    void createProcedure();

    Integer amountOfBooksAuthorHasWritten(String firstName, String lastName);
}
