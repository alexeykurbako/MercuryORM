package repository;

import annotations.Repo;

@Repo
public interface UserRepository extends AutoRepository<String, Long> {
    String getStringById(Long id);
}
