package telerikacademy.extensionrepository.models.dto;

import telerikacademy.extensionrepository.anotations.Unique;
import telerikacademy.extensionrepository.services.UserServiceImpl;
import telerikacademy.extensionrepository.constants.Constants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotNull
    @Size(min = 2, message = "First name should have at least 2 characters.")
    private String firstName;

    @NotNull
    @Size(min = 2, message = "Last name should have at least 2 characters.")
    private String lastName;

    @NotNull
    @Size(min = 3, message = "Username should have at least 3 characters.")
    @Unique(service = UserServiceImpl.class, fieldName = "username", message = "Username already exists.")
    private String username;

    @NotNull
    @Pattern(regexp = Constants.PASSWORD_PATTERN, message = "Invalid data!")
    private String password;

    @Email
    @Unique(service = UserServiceImpl.class, fieldName = "email", message = "Email already exists.")
    private String email;

    public UserDTO(){

    }

    public UserDTO(String firstName, String lastName, String username, String password, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setEmail(email);
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
}
