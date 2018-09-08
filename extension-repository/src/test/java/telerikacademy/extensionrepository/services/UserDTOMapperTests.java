//package telerikacademy.extensionrepository.services;
//
//import org.junit.Assert;
//import org.junit.Test;
//import telerikacademy.extensionrepository.areas.users.models.User;
//import telerikacademy.extensionrepository.areas.users.models.UserDTO;
//
//public class UserDTOMapperTests {
//    @Test
//    public void mapUserDTOToUser_shouldMapAndCreateNewUser(){
//        String firstName = "Test";
//        String lastName = "Testov";
//        String username = "test";
//        String password = "Test123";
//        String email = "test@gmail.com";
//
//        User user = new User();
//        UserDTO userDTO = new UserDTO(firstName, lastName, username, password, email);
//        user.setFirstName(userDTO.getFirstName());
//        user.setLastName(userDTO.getLastName());
//        user.setEmail(userDTO.getEmail());
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(userDTO.getPassword());
//
//        Assert.assertEquals(firstName, user.getFirstName());
//        Assert.assertEquals(lastName, user.getLastName());
//        Assert.assertEquals(username, user.getUsername());
//        Assert.assertEquals(password, user.getPassword());
//        Assert.assertEquals(email, user.getEmail());
//    }
//}
