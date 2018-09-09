package telerikacademy.extensionrepository.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import telerikacademy.extensionrepository.constants.Constants;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, message = "First name should have at least 2 characters.")
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 2, message = "Last name should have at least 2 characters.")
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String username;

    @NotNull
    @Pattern(regexp = Constants.PASSWORD_PATTERN, message = "Invalid password address!")
    @Column(nullable = false)
    private String password;

    @Email
    @NotNull
    @Column(nullable = false)
    private String email;

    @Column
    private String userStatus;

    private String role;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner")
    @JsonBackReference
    private List<Product> products;

    public User() {
        products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}