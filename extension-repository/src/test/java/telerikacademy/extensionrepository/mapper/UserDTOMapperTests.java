package telerikacademy.extensionrepository.mapper;

import org.junit.Assert;
import org.junit.Test;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.models.dto.UserDTO;

public class UserDTOMapperTests {
    private UserDTOMapper userMapper = new UserDTOMapper();

    @Test
    public void mapUserDTOToUser_shouldMapAndCreateNewUser(){
        String firstName = "Test";
        String lastName = "Testov";
        String username = "test";
        String password = "Test123";
        String email = "test@gmail.com";

        UserDTO userDTO = new UserDTO(firstName, lastName, username, password, email);

        User expectedUser = new User();
        expectedUser.setFirstName(userDTO.getFirstName());
        expectedUser.setLastName(userDTO.getLastName());
        expectedUser.setEmail(userDTO.getEmail());
        expectedUser.setUsername(userDTO.getUsername());
        expectedUser.setPassword(userDTO.getPassword());

        User actualUser = userMapper.mapUserDTOToUser(userDTO);
        Assert.assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
        Assert.assertEquals(expectedUser.getLastName(), actualUser.getLastName());
        Assert.assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        Assert.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        Assert.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    }
}
