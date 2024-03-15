package com.example.booknook;

import com.example.booknook.model.Book;
import com.example.booknook.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class BookController {
    @Autowired
    BookRepo bookRepo;

    @PostMapping("/book")
    public ResponseEntity<String> addBooks(@RequestBody List<Book> bookList) {
        try {
            bookRepo.saveAll(bookList);
            return ResponseEntity.ok("Books added successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add books to the database: " + ex.getMessage());
        }
    }

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getBook() {
        try {
            List<Book> bookList = bookRepo.findAll();
            return ResponseEntity.ok(bookList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/book/search")
    public ResponseEntity<List<Book>> getBookBySearch(@RequestParam("category") String category){
        try{
            System.out.println(category);
            List<Book> bookListBySearch = bookRepo.findAllByCategory(category);
            return ResponseEntity.ok(bookListBySearch);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
