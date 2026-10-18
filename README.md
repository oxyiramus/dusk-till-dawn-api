# Titty Twister Bar API

> **1995**: вышел Java. **1996**: вышел фильм «От заката до рассвета». 
> И где-то между ними - автор этого проекта. Три ровесника эпохи, которые переживут вампиров.

REST API для бронирования кабинок в баре «Titty Twister». 
Проект создан для отработки навыков промышленной разработки на Java: от настройки инфраструктуры в Docker до покрытия логики тестами и асинхронной обработки событий.

## Стек

*   **Java 17**
*   **Spring Boot 4.1.1**
*   **PostgreSQL 18** (База данных)
*   **Flyway** (Миграции базы данных)
*   **Apache Kafka** (Брокер сообщений)
*   **Docker / Docker Compose** (Контейнеризация)
*   **Swagger / OpenAPI 3** (Документация API)
*   **Maven** (Сборка проекта)

## Архитектура

```text
src/main/java/com/dusktilldawn/api/
├── config/         # Конфигурация Spring (Security, Kafka, OpenAPI)
├── controller/     # REST-контроллеры (HTTP-слой)
├── dto/            # Data Transfer Objects (запросы и ответы)
├── exception/      # Глобальные обработчики ошибок и кастомные исключения
├── kafka/          # Producer и Consumer для уведомлений
├── model/          # JPA-сущности (Patron, Booth, Shift, Reservation)
├── repository/     # Интерфейсы Spring Data JPA
├── security/       # JWT-фильтры и логика безопасности
└── service/        # Бизнес-логика (Stream API, транзакции)
```

## Запуск

У вас должны быть установлены Docker и Docker Compose.
Создайте файл `.env` по шаблону и заполните переменные окружения.

```
cp .env.example .env
nano .env
```

После этого приложение готово к запуску

```
mvn clean package  # Если меняли исходники, перед запуском проект нужно пересобрать
docker compose up --build -d
```

Приложение будет доступно по адресу: http://localhost:8080 (или на переопределенном WEB_PORT).
