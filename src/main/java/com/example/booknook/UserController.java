package com.example.booknook;

import com.example.booknook.model.Cart;
import com.example.booknook.model.User;
import com.example.booknook.repo.CartRepo;
import com.example.booknook.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserRepo repo;

    @Autowired
    CartRepo cartRepo;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user){
        try{
            Cart cart=new Cart();
            cart = cartRepo.save(cart);

            user.setCart(cart);

            User savedUser = repo.save(user);
            return ResponseEntity.ok(savedUser);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @PostMapping("/authenticate")
    public ResponseEntity<Map<String,Object>> authenticate(@RequestBody User user){
        User authenticatedUser = repo.findByNameAndPassword(user.getName(), user.getPassword());
        if (authenticatedUser != null) {
            long userId = authenticatedUser.getId();
            Map<String,Object> response = new HashMap<>();
            response.put("authenticated",true);
            response.put("userId",userId);
            return ResponseEntity.ok(response);
        } else {
            Map<String,Object> response =  new HashMap<>();
            response.put("authenticated",false);
            response.put("userId",-1);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PatchMapping("/user/{userId}")
    public ResponseEntity<String> updateUsername(@PathVariable long userId,@RequestBody String username){
        try{
            System.out.println(username);
            User user = repo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));

            user.setName(username);

            repo.save(user);

            return ResponseEntity.ok("username updated");
        }
        catch(Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("update operation failed");
        }
    }

}
