package spring.app.domain.entity;

import lombok.*;
import spring.app.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @NonNull
    @Column(nullable = false, unique = true, length = 20)
    private String name;

}
