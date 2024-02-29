package test.taskService.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Task implements Comparable<Task> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private String date;
//    @OneToMany
//    @JoinColumn(name = "users_id")
//    private List<User> users;


    @Override
    public int compareTo(Task o) {
        return this.getId().compareTo(o.getId());
    }

    public enum Status {
        Todo, InProgress, Done
    }
}
