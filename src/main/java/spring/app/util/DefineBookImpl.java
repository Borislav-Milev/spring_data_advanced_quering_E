package spring.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.app.domain.entity.Author;
import spring.app.domain.entity.Book;
import spring.app.domain.entity.Category;
import spring.app.domain.entity.enums.AgeRestriction;
import spring.app.domain.entity.enums.EditionType;
import spring.app.service.contract.AuthorService;
import spring.app.service.contract.CategoryService;
import spring.app.util.contract.DefineBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class DefineBookImpl implements DefineBook {

    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public DefineBookImpl(AuthorService authorService, CategoryService categoryService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt(this.authorService.getCount()) + 1;

        return this.authorService.findEntityById((long) randomId);
    }

    private Book setBookValues(String[] elements){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        Author author = this.getRandomAuthor();
        EditionType editionType = EditionType.values()[Integer.parseInt(elements[0])];
        LocalDate releaseDate = LocalDate.parse(elements[1], formatter);
        Integer copies = Integer.valueOf(elements[2]);
        BigDecimal price = new BigDecimal(elements[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(elements[4])];
        String title = this.getTitle(elements);

        Book book = new Book(title, editionType, price, copies, releaseDate, ageRestriction, author);

        book.setCategories(this.setRandomCategories());
        return book;
    }

    private Set<Category> setRandomCategories(){
        Set<Category> result = new HashSet<>();
        Random random = new Random();

        int categoryCount = this.categoryService.getCount();

        for (int i = 0; i < random.nextInt(categoryCount) + 1; i++) {
            result.add(this.categoryService.findEntityById((long) random.nextInt(categoryCount) + 1));
        }
        return result;
    }

    private String getTitle(String[] params){
        StringBuilder builder = new StringBuilder();
        for (int i = 5; i < params.length; i++) {
            builder.append(params[i]).append(" ");
        }
        return builder.toString().trim();
    }

    public Book getBookValues(String[] elements){
        return this.setBookValues(elements);
    }
}
