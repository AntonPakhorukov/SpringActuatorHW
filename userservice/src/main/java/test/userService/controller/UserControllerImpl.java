package test.userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.userService.dto.User;
import test.userService.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {
    //    @PostMapping("/register")
//    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable(name = "id") Long id, @RequestBody User user) {
        String updateUser = this.userService.getUserById(id).getName();
        userService.updateUser(id, user);
        return ResponseEntity.ok(String.format("Пользователь \"%s\" был изменен", updateUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id) {
        String delUserName = userService.getUserById(id).getName();
        this.userService.deleteUser(id);
        return ResponseEntity.ok(String.format("Пользователь \"%s\" удален", delUserName));
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok(String.format("Пользователь \"%s\" успешно зарегистрирован", user.getName()));
    }
}
