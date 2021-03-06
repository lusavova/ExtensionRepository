package telerikacademy.extensionrepository.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import telerikacademy.extensionrepository.data.ProductsRepository;
import telerikacademy.extensionrepository.enums.Roles;
import telerikacademy.extensionrepository.enums.UserStatus;
import telerikacademy.extensionrepository.mapper.UserDTOMapper;
import telerikacademy.extensionrepository.data.UserRepository;
import telerikacademy.extensionrepository.exceptions.UserNotFoundException;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.models.dto.UserDTO;
import telerikacademy.extensionrepository.services.base.ProductService;
import telerikacademy.extensionrepository.services.base.StorageService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceImplTests {
    private UserServiceImpl userService;
    private UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
    private UserDTOMapper mockMapper = Mockito.mock(UserDTOMapper.class);
    private StorageService storageService = Mockito.mock(StorageService.class);
    private ProductsRepository productsRepository = Mockito.mock(ProductsRepository.class);

    @Before
    public void setUp() {
        userService = new UserServiceImpl(mockUserRepository, mockMapper, storageService, productsRepository);
    }

    @Test
    public void findById_should_returnOnlyOneUser_when_idExists() {
        long id = 1;
        User expectedUser = new User();
        Mockito.when(mockUserRepository.findById(id)).thenReturn(java.util.Optional.of(expectedUser));
        User user = userService.findById(id);
        Assert.assertSame(expectedUser, user);
    }

    @Test(expected = UserNotFoundException.class)
    public void findById_should_throwExeption_when_idDoesNotExists() {
        Mockito.when(mockUserRepository.findById(1L)).thenReturn(Optional.empty());
        userService.findById(1);
    }

    @Test
    public void addUser_should_saveUserIntoDB_nominalCase() {
        User user = new User();
        Mockito.when(mockUserRepository.save(user)).thenReturn(user);

        UserDTO userDTO = new UserDTO();
        Mockito.when(mockMapper.mapUserDTOToUser(userDTO)).thenReturn(user);

        User addedUser = userService.addUser(userDTO);
        Assert.assertSame(user, addedUser);
    }

    @Test
    public void addUser_should_setUserStatusToEnabled() {
        UserDTO userDTO = new UserDTO();
        User expectedUser = new User();

        Mockito.when(mockMapper.mapUserDTOToUser(userDTO)).thenReturn(expectedUser);
        Mockito.when(mockUserRepository.save(expectedUser)).thenReturn(expectedUser);

        User actualUser = userService.addUser(userDTO);
        Assert.assertEquals(actualUser.getUserStatus(), UserStatus.ENABLED.name());
    }

    @Test
    public void addUser_shouldSetRoleToUser() {
        UserDTO userDTO = new UserDTO();
        User expectedUser = new User();

        Mockito.when(mockMapper.mapUserDTOToUser(userDTO)).thenReturn(expectedUser);
        Mockito.when(mockUserRepository.save(expectedUser)).thenReturn(expectedUser);

        User actualUser = userService.addUser(userDTO);
        Assert.assertEquals(actualUser.getRole(), Roles.USER.name());
    }

//    @Test
//    public void updateUser_should_saveUpdatedUserIntoDB_nominalCase() {
//        User user = new User();
//        Mockito.when(mockUserRepository.save(user)).thenReturn(user);
//
//        UserDTO updateUserDTO = new UserDTO();
//        Mockito.when(mockMapper.mapUserDTOToUser(updateUserDTO)).thenReturn(user);
//
//        User updateUser = userService.updateUser(updateUserDTO);
//        Assert.assertSame(user, updateUser);
//    }

//    @Test
//    public void deleteUser_should_deleteUserFromDB_when_userIdExists() {
//        long id = 1;
//        User user = new User();
//        Mockito.when(mockUserRepository.findById(id)).thenReturn(java.util.Optional.of(user));
//
//        Mockito.doAnswer(invocation -> {
//            Object arg0 = invocation.getArgument(0);
//            Assert.assertEquals(id, arg0);
//            return null;
//        }).when(mockUserRepository).deleteById(id);
//
//        userService.deleteUser(id);
//    }
//
//    @Test(expected = UserNotFoundException.class)
//    public void deleteUser_should_throwException_when_userIdDoesNotExists() {
//        long id = 1;
//        Mockito.when(mockUserRepository.findById(id)).thenReturn(Optional.empty());
//        userService.deleteUser(id);
//    }

    @Test
    public void listAllUsers_should_return3_when_countEquals3() {
        List<User> listOfUsers = Arrays.asList(
                new User(),
                new User(),
                new User()
        );

        Mockito.when(mockUserRepository.findAll())
                .thenReturn(listOfUsers);

        List<User> resultUsers = userService.listAllUsers();

        Assert.assertEquals(3, resultUsers.size());
    }
}