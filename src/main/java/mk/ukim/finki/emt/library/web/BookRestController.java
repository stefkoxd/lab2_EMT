package mk.ukim.finki.emt.library.web;

import mk.ukim.finki.emt.library.model.Book;
import mk.ukim.finki.emt.library.model.dto.BookDto;
import mk.ukim.finki.emt.library.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return this.bookService.findById(id).
                map(book -> ResponseEntity.ok().body(book)).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto){
        return this.bookService.save(bookDto).map(book -> ResponseEntity.ok().body(book)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id, @RequestBody BookDto bookDto){
        return this.bookService.edit(id, bookDto.getName(), bookDto.getCategory(), bookDto.getAvailableCopies(), bookDto.getAuthorId())
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable Long id){
        this.bookService.deleteBook(id);
        if ( this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/markAsTaken/{id}")
    public ResponseEntity<Book> markTaken(@PathVariable Long id){
        Integer availableCopies = this.bookService.findById(id).orElseThrow(() -> new BookNotFoundException(id)).getAvailableCopies();
        this.bookService.markAsTaken(id);
        if (!availableCopies.equals(this.bookService.findById(id).orElseThrow(() -> new BookNotFoundException(id)).getAvailableCopies()))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
