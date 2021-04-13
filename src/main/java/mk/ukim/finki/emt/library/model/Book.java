package mk.ukim.finki.emt.library.model;

import lombok.Data;
import mk.ukim.finki.emt.library.model.enumerations.Category;

import javax.persistence.*;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer availableCopies;

    @ManyToOne
    private Author author;

    public Book() {
    }

    public Book(String name, Category category, Integer availableCopies, Author author) {
        this.name = name;
        this.category = category;
        this.availableCopies = availableCopies;
        this.author = author;
    }
}
