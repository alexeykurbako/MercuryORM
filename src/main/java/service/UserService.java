package service;

import annotations.Bean;
import annotations.Injected;
import repository.UserRepository;

@Bean
public class UserService {
    @Injected
    private UserRepository userRepository;

    public void sssss() {

       userRepository.getByNameAndSurname("alex", "kurbako");
    }
}
