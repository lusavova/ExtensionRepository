package telerikacademy.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import telerikacademy.extensionrepository.mapper.UserDTOMapper;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.data.UserRepository;
import telerikacademy.extensionrepository.exceptions.UserNotFoundException;
import telerikacademy.extensionrepository.models.dto.UserDTO;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.UserService;
import telerikacademy.extensionrepository.enums.UserStatus;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserDTOMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserDTOMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }
    @Override
    public User findById(long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("Can't find user with id = %d", id)));
    }

    @Override
    public User findByUsername(String username){
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UserNotFoundException(String.format("Can't find user with username: %s", username));
        }
        return user;
    }

    @Override
    public User addUser(UserDTO userDTO) {
        User user = mapper.mapUserDTOToUser(userDTO);
        user.setUserStatus(UserStatus.ENABLED.name());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDTO updateUser) {
        User user = mapper.mapUserDTOToUser(updateUser);
        return saveUser(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = findById(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> listAllBlockedUsers() {
        return userRepository.listAllBlockedUsers();
    }

    @Override
    public List<User> listAllActiveUsers() {
        return userRepository.listAllActiveUsers();
    }

    @Override
    public List<Product> listAllProducts(long id) {
        return findById(id).getProducts();
    }

    @Override
    public boolean usernameAlreadyExists(String username) {
        Set<String> allUsernames = userRepository.findAll()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toSet());
        return allUsernames.contains(username);
    }

    @Override
    public boolean emailAlreadyExists(String email) {
        Set<String> allEmails = userRepository.findAll()
                .stream()
                .map(User::getEmail)
                .collect(Collectors.toSet());
        return allEmails.contains(email);
    }

    @Override
    public void changeUserStatus(long id, String status) {
        User user = findById(id);
        user.setUserStatus(status);
        saveUser(user);
    }

    @Override
    public boolean fieldValueExists(Object value, String fieldName) {
        Assert.notNull(fieldName, String.format("%s already exist.", formatField(fieldName)));

        if (value == null) {
            return false;
        }

        return setOfValues(fieldName).contains(value);
    }

    private Set<Object> setOfValues(Object fieldName) {
//        Field[] fields = User.class.getFields();
//        Set<String> fieldNames = Arrays.stream(fields).map(Field::getName).collect(Collectors.toSet());
//
//        if (!fieldNames.contains(fieldName)){
//            throw new IllegalArgumentException("Cannot find field with name: " + fieldName);
//        }

        Set<Object> values;
        if (fieldName.equals("username")) {
            values = userRepository.findAll().stream()
                    .map(User::getUsername)
                    .collect(Collectors.toSet());
        } else if (fieldName.equals("email")) {
            values = userRepository.findAll().stream()
                    .map(User::getEmail)
                    .collect(Collectors.toSet());
        } else {
            throw new IllegalArgumentException("No such user field.");
        }

        return values;
    }

    private String formatField(String fieldName) {
        char firstLetter = fieldName.toUpperCase().charAt(0);
        String resultLetters = fieldName.substring(1).toLowerCase();
        return firstLetter + resultLetters;
    }

    private User saveUser(User user) {
        return userRepository.save(user);
    }


}


