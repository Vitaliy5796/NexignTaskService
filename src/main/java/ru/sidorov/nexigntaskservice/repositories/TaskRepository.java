package ru.sidorov.nexigntaskservice.repositories;

import org.springframework.stereotype.Repository;
import ru.sidorov.nexigntaskservice.models.entities.Task;

import java.util.Optional;

@Repository
public interface TaskRepository extends BaseRepository<Task> {
    boolean existsByIdempotencyKey(String idempotencyKey);
    Optional<Task> findByIdempotencyKey(String idempotencyKey);
}
