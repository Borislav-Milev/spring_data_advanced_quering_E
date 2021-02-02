package spring.app.domain.entity;

import lombok.*;
import spring.app.domain.BaseEntity;
import spring.app.domain.entity.enums.AgeRestriction;
import spring.app.domain.entity.enums.EditionType;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    public Book() {
        this.categories = new HashSet<>();
    }

    public Book(@NonNull String title, EditionType editionType,
                @NonNull @DecimalMin(value = "0", message = "Price cannot be lower than 0") BigDecimal price,
                @NonNull Integer copies, LocalDate releaseDate, AgeRestriction ageRestriction, Author author) {
        this();
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
    }

    public Book(@NonNull String title, String description, EditionType editionType,
                @NonNull @DecimalMin(value = "0", message = "Price cannot be lower than 0") BigDecimal price,
                @NonNull Integer copies, LocalDate releaseDate, AgeRestriction ageRestriction, Author author) {
        this(title, editionType, price, copies, releaseDate, ageRestriction, author);
        this.description = description;
    }


    @NonNull
    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "TEXT", length = 1000)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private EditionType editionType;

    @NonNull
    @DecimalMin(value = "0", message = "Price cannot be lower than 0")
    @Column(nullable = false)
    private BigDecimal price;

    @NonNull
    @Min(value = 0)
    @Column(nullable = false)
    private Integer copies;

    private LocalDate releaseDate;

    @Enumerated(EnumType.ORDINAL)
    private AgeRestriction ageRestriction;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Category> categories;

    @NonNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Author author;
}
