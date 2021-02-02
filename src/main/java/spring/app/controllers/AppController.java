package spring.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import spring.app.manager.QueryManager;
import spring.app.service.contract.AuthorService;
import spring.app.service.contract.BookService;
import spring.app.service.contract.CategoryService;

@Controller
public class AppController implements CommandLineRunner {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final QueryManager queryManager;

    @Autowired
    public AppController(BookService bookService, AuthorService authorService,
                         CategoryService categoryService, QueryManager queryManager) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.queryManager = queryManager;
    }

    @Override
    public void run(String... args) {
        this.categoryService.seedEntities();
        this.authorService.seedEntities();
        this.bookService.seedEntities();

        this.queryManager.run();
    }
}
