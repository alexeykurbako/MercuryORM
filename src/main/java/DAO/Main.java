package DAO;

import DAO.factory.ConnectionPool;
import entity.User;
import exception.ServiceException;
import service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {
        try (ConnectionPool pool = ConnectionPool.getConnectionPool()) {
            UserService service = new UserService();
            List<User> users = service.getAll();
            System.out.println(users);

            String username = "root";
            String password = "pass";

            Optional<User> actual = service.login(username, password);

            if (actual.isPresent()) {
                switch (actual.get().getRole()) {
                    case USER:
                        System.out.println("You was logined as user");
                        break;
                    case ADMIN:
                        System.out.println("You was logined as admin");
                        break;
                    case BOOKMAKER:
                        System.out.println("You was logined as bookmaker");
                        break;
                }
            } else {
                System.out.println();
            }
        } catch (ServiceException | IOException e) {
            e.printStackTrace();
        }
    }
}
