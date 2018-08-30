package telerikacademy.extensionrepository.areas.users.validators;

import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.exceptions.FormatExeption;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    //private static final String PASSWORD_PATTERN = "";
    private static final String USERNAME_PATTERN = "^[A-Z0-9a-z_][A-Za-z0-9_.-]+$";
    private static final String EMAIL_PATTERN = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    private static final String NAME_PATTERN = "^[A-Z][a-z]+$";

    public void checkUserDataFormat(User user) {
//        checkUsernameAndPassword(user.getUsername(), user.getPassword());
        checkEmail(user.getEmail());
        checkName(user.getFirstName());
        checkName(user.getLastName());
    }

    private void checkUsernameAndPassword(String username, String password) {
        if (!isUsernameValid(username) || password.length() < 6) {
            throw new FormatExeption("Invalid username or password");
        }
    }

    //(?=.*[0-9])       # a digit must occur at least once
//  (?=.*[a-z])       # a lower case letter must occur at least once
//  (?=.*[A-Z])       # an upper case letter must occur at least once
//  (?=\S+$)          # no whitespace allowed in the entire string
//    private boolean isPasswordValid(String password) {
//        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
//        Matcher matcher = pattern.matcher(password);
//        System.out.println(matcher.find() + " pass");
//        return matcher.find();
//    }

    private boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(username);
        System.out.println(matcher.find() + "usrname");
        return matcher.find();
    }

    private void checkEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            throw new FormatExeption("Invalid email.");
        }
    }

    private void checkName(String name) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.find()) {
            throw new FormatExeption("Invalid name.");
        }
    }
}
