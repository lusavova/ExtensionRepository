package telerikacademy.daredevil.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.daredevil.extensionrepository.models.User;
import telerikacademy.daredevil.extensionrepository.repositories.UsersRepository;
import telerikacademy.daredevil.extensionrepository.services.base.UsersService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository=usersRepository;
    }

    @Override
    public List<User> listAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUserIntoDB(User user) {
        this.usersRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public void updateUser(int id, User updateUser) {
        User user = usersRepository.getOne((long) id);
        user.setUsername(updateUser.getUsername());
        user.setPassword(updateUser.getPassword());

        usersRepository.saveAndFlush(user);
    }
}
