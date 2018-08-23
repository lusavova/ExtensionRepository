package telerikacademy.extensionrepository.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.data.UserRepository;
import telerikacademy.extensionrepository.services.base.AdminService;

import javax.transaction.Transactional;

@Transactional
@Service
public class AdminServiceImpl implements AdminService {
    private UserRepository userRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void changeUserStatus(boolean status, long id) {
        userRepository.chandeUserStatus(status, id);
    }

    @Override
    public void changeUserStatus(boolean status, String username) {
        userRepository.chandeUserStatus(status, username);
    }
}
