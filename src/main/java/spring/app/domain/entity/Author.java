package spring.app.domain.entity;

import lombok.*;
import spring.app.domain.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

    public Author() {
        this.books = new HashSet<>();
    }

    public Author(@NonNull String lastName) {
        this();
        this.lastName = lastName;
    }

    public Author(String firstName, @NonNull String lastName) {
        this(lastName);
        this.firstName = firstName;
    }

    @Column(length = 20)
    private String firstName;

    @NonNull
    @Column(nullable = false, length = 20)
    private String lastName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Book> books;
}
