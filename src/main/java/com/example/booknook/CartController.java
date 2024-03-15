package com.example.booknook;

import com.example.booknook.model.Book;
import com.example.booknook.model.Cart;
import com.example.booknook.model.User;
import com.example.booknook.repo.BookRepo;
import com.example.booknook.repo.CartRepo;
import com.example.booknook.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class CartController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    BookRepo bookRepo;


    @PostMapping("/cart/{userId}")
    public ResponseEntity<String> addToCart(@PathVariable long userId, @RequestBody String bookName){
        try {
            Book book = bookRepo.findIdByTitle(bookName);
            System.out.println(book);

            if (book == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
            }

            User user = userRepo.findById(userId)
                    .orElseThrow(()->new RuntimeException("user not found"));

            Cart cart = user.getCart();

            if(cart == null){
                cart = new Cart();
                user.setCart(cart);
            }

            String bookId = String.valueOf(book.getId());

            String bfk = cart.getBfk();
            if(bfk == null || bfk.isEmpty()){
                bfk = bookId;
            }
            else{
                bfk += "," + bookId;
            }
            cart.setBfk(bfk);

            cartRepo.save(cart);
            return ResponseEntity.ok("Book added to cart");
        }
        catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("could not add book to cart" + ex.getMessage());
        }
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<List<Book>> fetchBook(@PathVariable long userId){
        try{
            User user = userRepo.findById(userId)
                    .orElseThrow(()->new RuntimeException("User not found"));

            Cart cart = user.getCart();

            if(cart == null){
                return ResponseEntity.ok(new ArrayList<>()); // not cart found
            }

            String bfk = cart.getBfk();

            if(bfk == null || bfk.isEmpty()){
                return ResponseEntity.ok(new ArrayList<>()); // cart is empty
            }
            List<Long> bookIds = Arrays.stream(bfk.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());

            List<Book> booksInCart = bookRepo.findAllById(bookIds);

            return ResponseEntity.ok(booksInCart);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ArrayList<>());
        }
    }

    @DeleteMapping("/book/{userId}/{bookId}")
    public ResponseEntity<String> deleteBookFromCart(@PathVariable long userId, @PathVariable long bookId){
        try{
            User user = userRepo.findById(userId)
                    .orElseThrow(()->new RuntimeException("user not found"));

            Cart cart = user.getCart();

            String bfk = cart.getBfk();

            List<String> bookIds = new ArrayList<>(Arrays.asList(bfk.split(",")));

            bookIds.remove(String.valueOf(bookId));

            String updatedBfk = String.join(",",bookIds);

            cart.setBfk(updatedBfk);

            cartRepo.save(cart);
            return ResponseEntity.ok("Book deleted from cart");
        }
        catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Could not delete book from cart : "+ex.getMessage());
        }
    }
}
