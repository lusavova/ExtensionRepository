package telerikacademy.extensionrepository.areas.mapper;

import org.springframework.stereotype.Component;
import telerikacademy.extensionrepository.areas.users.models.User;
import telerikacademy.extensionrepository.areas.users.models.UserDTO;

@Component
public class UserDTOMapper {
    public User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
