package ru.sidorov.nexigntaskservice.models.exception;

public class NotFoundTaskException extends NotFoundException {

  public static final String ERROR_MSG = "Задача не найдена";

  public static final String ERROR_MSG_USER_WITH_ID = "Задача с id = %d не найдена";
  public static final String ERROR_MSG_USER_WITH_KEY = "Задача с key = %s не найдена";

  public NotFoundTaskException() {
    super(ERROR_MSG);
  }

  public NotFoundTaskException(Integer taskId) {
    super(String.format(ERROR_MSG_USER_WITH_ID, taskId));
  }

  public NotFoundTaskException(String key) {
    super(String.format(ERROR_MSG_USER_WITH_KEY, key));
  }
}
