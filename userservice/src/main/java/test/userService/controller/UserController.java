package test.userService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.userService.dto.User;

import java.util.List;

@RequestMapping("/users")
public interface UserController {
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user);

    @GetMapping
    public ResponseEntity<List<User>> getUsers();

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id);

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable(value = "id") Long id, @RequestBody User user);

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Long id);

}
