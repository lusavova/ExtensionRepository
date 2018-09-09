package telerikacademy.extensionrepository.mapper;

import org.springframework.stereotype.Component;
import telerikacademy.extensionrepository.models.User;
import telerikacademy.extensionrepository.models.dto.UserDTO;

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
