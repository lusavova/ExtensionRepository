package telerikacademy.extensionrepository.areas.users.models;

import org.hibernate.validator.constraints.UniqueElements;
import telerikacademy.extensionrepository.areas.products.models.Product;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
//    @Size(min = 2, message = "First name should be atleast 2 characters long")
    @Column(nullable = false)
    private String firstName;

//    @NotNull
//    @Size(min = 2, message = "Last name should be atleast 2 characters long")
    @Column(nullable = false)
    private String lastName;

//    @NotNull
//    @Size(min = 3, message = "Username should be atleast 3 characters long")
    @Column(nullable = false)
    private String username;

//    @NotNull
//    @Size(min = 6, message = "Password should be atleast 6 characters long")
    @Column(nullable = false)
    private String password;

//    @Email
    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT 'pending'")
    private String userStatus;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
}
