package service;

import annotations.Injected;
import annotations.Service;
import repository.UserRepository;

@Service
public class UserService {
    @Injected
    private UserRepository userRepository;

    public void sssss() {

       userRepository.getStringById(1L);
    }
}
