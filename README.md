# Task Service Consumer

Этот проект представляет собой сервис-консьюмер, который обрабатывает задачи, получаемые через Kafka. Он регистрирует задачи в базе данных, запускает их выполнение, обновляет их статус и сохраняет результаты.

## Описание

Сервис прослушивает сообщения из Kafka, сохраняет полученные задачи в базе данных, а затем выполняет их асинхронно, обновляя статус задачи в процессе. Статус задачи может быть `PENDING`, `IN_PROGRESS` и `COMPLETED`.

## Требования

- Java 17
- Spring Boot
- PostgreSQL
- Apache Kafka
- Docker

## Стек технологий

- **Spring Boot**
- **Kafka**
- **Spring Data JPA**
- **Flyway**
- **Swagger**

## Установка и запуск

### 1. Клонирование репозитория

```bash
git clone https://github.com/Vitaliy5796/NexignTaskService.git
cd task-service-consumer
```