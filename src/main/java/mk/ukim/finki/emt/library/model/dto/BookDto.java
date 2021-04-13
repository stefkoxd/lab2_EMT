package mk.ukim.finki.emt.library.model.dto;

import lombok.Data;
import mk.ukim.finki.emt.library.model.enumerations.Category;

@Data
public class BookDto {
    private String name;
    private Category category;
    private Integer availableCopies;
    private Long authorId;
}
