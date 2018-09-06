package telerikacademy.extensionrepository.areas.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import telerikacademy.extensionrepository.areas.files.services.base.StorageService;
import telerikacademy.extensionrepository.areas.mapper.UserDTOMapper;
import telerikacademy.extensionrepository.areas.products.models.Product;
import telerikacademy.extensionrepository.areas.users.data.UserRepository;
import telerikacademy.extensionrepository.areas.users.exeptions.UserNotFoundExeption;
import telerikacademy.extensionrepository.areas.users.models.UserDTO;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.users.services.base.UserService;
import telerikacademy.extensionrepository.constants.Constants;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserDTOMapper mapper;
    private StorageService storageService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserDTOMapper mapper,
                           StorageService storageService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.storageService = storageService;
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundExeption(
                        String.format("Can't find user with id = %d", id)));
    }

    @Override
    public User addUser(UserDTO userDTO) {
        User user = mapper.mapUserDTOToUser(userDTO);
        user.setUserStatus(Constants.PENDING_USER_STATUS);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(long id, User updateUser) {
        findById(id);
        return userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(long id) {
        User user = findById(id);
        storageService.deleteAllUserFilesFromSystem(user);
        userRepository.deleteById(id);
    }

    @Override
    public List<Product> listAllProducts(long id) {
        return findById(id).getProducts();
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
}