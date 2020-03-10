package repository;

import annotations.Repo;

@Repo
public interface UserRepository extends BaseRepository<String, Long> {
    String getStringById(Long id);
}
