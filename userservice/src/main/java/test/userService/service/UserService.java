package test.userService.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.userService.dto.User;
import test.userService.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll().stream().sorted().collect(Collectors.toList());
    }

    public String registerUser(User user) {
        userRepository.save(user);
        return "Пользователь зарегистрирован";
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Пользователь не найден"));
    }

    public void deleteUser (Long id){
        userRepository.deleteById(id);
    }

    public User updateUser (Long id, User user){
        User tempUser = getUserById(id);
        tempUser.setAge(user.getAge());
        tempUser.setName(user.getName());
        tempUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(tempUser);
        return tempUser;
    }

}
