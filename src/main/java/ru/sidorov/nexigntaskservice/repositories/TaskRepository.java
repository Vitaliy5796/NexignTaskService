package ru.sidorov.nexigntaskservice.repositories;

import org.springframework.stereotype.Repository;
import ru.sidorov.nexigntaskservice.models.entities.Task;

@Repository
public interface TaskRepository extends BaseRepository<Task> {
    boolean existsByIdempotencyKey(String idempotencyKey);
}
