package telerikacademy.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.data.UserRepository;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.UserService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Can't find user with id = %d", id)));
    }

    @Override
    public User addUser(User user) {
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email.");
        }

        if (!isUsernameAndPasswordValid(user)) {
            throw new IllegalArgumentException("Invalid username or password.");
        }

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email.");
        }

        if (!isUsernameAndPasswordValid(user)) {
            throw new IllegalArgumentException("Invalid username or password.");
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    private boolean isUsernameAndPasswordValid(User user) {
        return isUsernameValid(user.getUsername()) &&
                isPasswordValid(user.getPassword());
    }

    //  (?=.*[0-9])       # a digit must occur at least once
//  (?=.*[a-z])       # a lower case letter must occur at least once
//  (?=.*[A-Z])       # an upper case letter must occur at least once
//  (?=\S+$)          # no whitespace allowed in the entire string
    private boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    private boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile("^[A-Z0-9a-z_][A-Za-z0-9_\\.\\-]+$");
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
