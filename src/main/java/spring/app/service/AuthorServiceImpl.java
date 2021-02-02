package spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.app.constants.FilePaths;
import spring.app.constants.Messages;
import spring.app.domain.entity.Author;
import spring.app.repository.AuthorRepository;
import spring.app.service.contract.AuthorService;
import spring.app.util.contract.FileUtil;

import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(FileUtil fileUtil, AuthorRepository authorRepository) {
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T findEntityById(Long id) {
        return (T) this.authorRepository.findById(id).orElse(null);
    }

    @Override
    public void seedEntities() {
        if(this.getCount() != 0){
            System.out.println(Messages.FILLED_AUTHORS);
            return;
        }

        this.fileUtil.readFileContent(FilePaths.AUTHORS_FILE_PATH)
                .forEach(element -> {
                    String[] fullName = element.split("\\s+");
                    String firstName = fullName[0];
                    String lastName = fullName[1];
                    Author author = new Author(firstName, lastName);
                    this.authorRepository.saveAndFlush(author);
                });
    }

    @Override
    public int getCount() {
        return Math.toIntExact(this.authorRepository.count());
    }

    @Override
    public Set<Author> authorsEndingWith(String endStr) {
        return this.authorRepository.findAllByFirstNameEndingWith(endStr);
    }

    @Override
    public Set<String> authorWithTotalBookCopies() {
        return this.authorRepository.authorsWithTotalBookCopies();
    }

    @Override
    public void createProcedure() {
        this.authorRepository.createProcedure();
    }

    @Override
    public Integer amountOfBooksAuthorHasWritten(String firstName, String lastName) {
        return this.authorRepository.P_amountOfBooksAuthorHasWritten(firstName, lastName);
    }
}
