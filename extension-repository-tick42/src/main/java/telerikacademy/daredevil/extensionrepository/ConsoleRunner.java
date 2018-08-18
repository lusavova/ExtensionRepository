package telerikacademy.daredevil.extensionrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import telerikacademy.daredevil.extensionrepository.models.User;
import telerikacademy.daredevil.extensionrepository.services.base.ProductsService;
import telerikacademy.daredevil.extensionrepository.services.base.UsersService;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private UsersService usersService;

    @Override
    public void run(String... args) throws Exception {
//        testSaveUser("firstUser", "somePass");
//        testSaveUser("secondUser", "123");
//        testSaveUser("thirdUser", "blabla");
//        testSaveUser("fourthUser", "user");

//        testUpdateUser();

//        testUpdateUser("user4", "newPass", 4);
//
//        System.out.println(testFindUserById(1));
//
//        System.out.println("-------------");
//
//        for (User user : usersService.listAllUsers()) {
//            System.out.println(user);
//        }
//
//        System.out.println("-----------");
//
//        testDeleteUser(1);
//        testDeleteUser(2);
//        testDeleteUser(3);
//
//        System.out.println(usersService.listAllUsers().size());

    }
    // TEST USER


    private void testUpdateUser() {
        User user = usersService.findUserById(1);
        user.setPassword("NEW PASSWORD TEST");
        System.out.println(user);
        usersService.updateUser(1,user);
    }

    private void testUpdateUser(String username, String password, long id) {
        User user = new User(username, password);
        User updateuser = usersService.findUserById(id);
        System.out.println(updateuser);

        usersService.updateUser(1, user);
        System.out.println(usersService.findUserById(1));
    }

    private User testSaveUser(String username, String password) {
        User user = new User(username, password);
        usersService.saveUserIntoDB(user);
        return user;
    }

    private void testDeleteUser(long id) {
        usersService.deleteUser(id);
    }

    private User testFindUserById(long id) {
        return usersService.findUserById(id);
    }

    //TEST PRODUCTS

    private void testSaveProductIntoDb() {

//        GithubInfo githubInfo = new GithubInfo();
//        githubInfo.setRepositoryLink("repository link");
//
//        Product product = new Product();
//        product.setName("TestProductName");
//        product.setDescription("some desc");
//        product.setVersion("1.0");
//        product.setUploadDate(new Date());
//        product.setDownloadLink("some download link");
//        product.setOwner(user);
//        product.setGithubInfo(githubInfo);

//        productsService.saveProductIntoDB(product);
    }
}
