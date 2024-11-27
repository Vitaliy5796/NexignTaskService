package ru.sidorov.nexigntaskservice.service.abstracts;

import ru.sidorov.nexigntaskservice.models.entities.Task;

public interface TaskWorker {

    /**
     * Executes a task by simulating its execution via Thread.sleep.
     *
     * @param task {@link Task}
     */
    void executeTask(Task task);
}
