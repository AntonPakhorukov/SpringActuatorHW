package test.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.userService.dto.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
