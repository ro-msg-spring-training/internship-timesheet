package ro.msg.internship.timesheet.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class User {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false, columnDefinition = "int")
    @Enumerated
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "program_id", referencedColumnName = "program_id")
    private Program program;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Booking> bookings;

    public User(Integer userId, String firstName, String lastName, String username, String password, Role role, Program program, Set<Booking> bookings) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        setPassword(password);
        this.role = role;
        this.program = program;
        this.bookings = bookings;
    }

    public void setPassword(String password) {
//        this.password = PASSWORD_ENCODER.encode(password);
        this.password = password;
    }
}
