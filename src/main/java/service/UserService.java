package service;

import annotations.Bean;
import annotations.Injected;
import annotations.Service;
import repository.UserRepository;

@Bean
public class UserService {
    @Injected
    private UserRepository userRepository;

    public void sssss() {

       userRepository.getStringById(1L);
    }
}
