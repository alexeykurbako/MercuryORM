package repository;

import annotations.Repo;
import query.User;

@Repo
public interface UserRepository extends BaseRepository<User, Long> {
    User getByNameAndSurname(String name, String surname);
}
