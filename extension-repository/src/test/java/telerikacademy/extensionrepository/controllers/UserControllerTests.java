package telerikacademy.extensionrepository.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import telerikacademy.extensionrepository.constants.Constants;
import telerikacademy.extensionrepository.enums.UserStatus;
import telerikacademy.extensionrepository.exceptions.UserNotFoundException;
import telerikacademy.extensionrepository.models.Product;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.services.base.ProductService;
import telerikacademy.extensionrepository.services.base.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    @MockBean
    private UserService mockUserService;
    @MockBean
    private ProductService mockProductService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findById_should_returnStatusOK_when_employeeExists() throws Exception {
        User returnedUser = new User();
        returnedUser.setUserStatus(UserStatus.ENABLED.name());
        Mockito.when(mockUserService.findById(1))
                .thenReturn(returnedUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}",1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findById_should_return4xx_when_employeeIsDisabled() throws Exception {
        User returnedUser = new User();
        returnedUser.setUserStatus(UserStatus.DISABLED.name());
        Mockito.when(mockUserService.findById(1))
                .thenReturn(returnedUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}",1))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

//    @Test
//    public void updateUser_should_returnOk_when_userServiceReturnsSuccess() throws Exception {
//        User givenUser = new User();
//        Mockito.when(mockUserService.updateUser(givenUser,1))
//                .thenReturn(givenUser);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/users/update/{id}",1)
//                        .content(new ObjectMapper().writeValueAsString(givenUser))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

    @Test
    public void deleteUser_should_returnOk_when_userServiceReturnsSuccess() throws Exception {
        long id = 1;
        final boolean[] wasDeleteInvoked = {false};
        Mockito.doAnswer(x -> {
            wasDeleteInvoked[0] = true;
            return null;
        }).when(mockUserService).deleteUser(id);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/users/delete/{id}",id))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assert.assertTrue(wasDeleteInvoked[0]);
    }

    @Test
    public void usernameAlreadyExists_should_returnOk_when_userServiceReturnsTrue() throws Exception {
        String username = "testName";
        Mockito.when(mockUserService.usernameAlreadyExists(username)).thenReturn(true);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/user/username")
                .param("username", username))
                .andExpect(MockMvcResultMatchers.content().string("true"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void usernameAlreadyExists_should_return4xx_when_userServiceReturnsFalse() throws Exception {
        String username = "testName";
        Mockito.when(mockUserService.usernameAlreadyExists(username)).thenReturn(false);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/user/username")
                        .param("username", username))
                .andExpect(MockMvcResultMatchers.content().string("false"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void emailAlreadyExists_should_returnOk_when_userServiceReturnsTrue() throws Exception {
        String email = "testEmail";
        Mockito.when(mockUserService.emailAlreadyExists(email)).thenReturn(true);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/user/email")
                        .param("email", email))
                .andExpect(MockMvcResultMatchers.content().string("true"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void emailAlreadyExists_should_returnOk_when_userServiceReturnsFalse() throws Exception {
        String email = "testEmail";
        Mockito.when(mockUserService.emailAlreadyExists(email)).thenReturn(false);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/user/email")
                        .param("email", email))
                .andExpect(MockMvcResultMatchers.content().string("false"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void listAllUserProducts_should_returnProductsOfUser_when_userIsOwner() throws Exception {
        User givenUser = new User();
        User otherUser = new User();
        otherUser.setId(1L);
        givenUser.setId(5L);

        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        Product product5 = new Product();

        product1.setOwner(givenUser);
        product2.setOwner(givenUser);
        product3.setOwner(givenUser);

        product4.setOwner(otherUser);
        product5.setOwner(otherUser);

        List<Product> allProducts = Arrays.asList(product1, product2, product3, product4, product5);
        List<Product> expectedProducts = Arrays.asList(product1, product2, product3);

        Mockito.when(mockProductService.listAllActiveProducts()).thenReturn(allProducts);
        Mockito.when(mockUserService.findById(givenUser.getId())).thenReturn(givenUser);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/products/{id}", givenUser.getId()))
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(expectedProducts)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
