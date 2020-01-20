package repository;

import annotations.Repo;

@Repo
public interface UserRepository {
    String getStringById(Long id);
}
