package mk.ukim.finki.emt.library.web;


import mk.ukim.finki.emt.library.model.Author;
import mk.ukim.finki.emt.library.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        return authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author)).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Author> findAll(){
        return this.authorService.findAll();
    }
}
