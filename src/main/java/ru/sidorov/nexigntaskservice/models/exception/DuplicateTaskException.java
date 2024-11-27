package ru.sidorov.nexigntaskservice.models.exception;

public class DuplicateTaskException extends NotFoundException {

  public static final String ERROR_MSG = "Задача уже существует";

  public static final String ERROR_MSG_USER_WITH_ID = "Задача с id = %d уже существует";

  public DuplicateTaskException() {
    super(ERROR_MSG);
  }

  public DuplicateTaskException(Integer taskId) {
    super(String.format(ERROR_MSG_USER_WITH_ID, taskId));
  }
}
