package mk.ukim.finki.emt.library.model.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id){
        super(String.format("Book with id: %d not found", id));
    }
}
