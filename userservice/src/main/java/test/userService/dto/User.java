package test.userService.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "users")
public class User implements Comparable<User>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    private int age;
    @Column(nullable = false)
    private String phoneNumber;
    @Override
    public int compareTo(User o) {
        return this.getId().compareTo(o.getId());
    }
}
