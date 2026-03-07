# Telegram Notification Service

## Overview

The Telegram Notification Service allows applications to send notifications to individual users on Telegram using their 
chat ID.

The chat ID can be obtained when a user subscribes to the Telegram bot. Storage of chat IDs is intentionally not 
handled by this service, as the storage strategy may vary depending on application requirements.

This design keeps the service flexible and reusable across different systems.

The service exposes REST endpoints built with Spring Boot, uses Jakarta Bean Validation for request validation, 
and integrates Springdoc OpenAPI to automatically generate Swagger UI documentation.
<br />
<br />

## The service supports:

- Structured and validated payloads
- Clear success/failure response models
- Interactive API documentation with Swagger
<br />
<br />

## Getting Started

⚠️ Note: You need to create a telegram bot token before moving forward. 
Check their official documentation [here](https://core.telegram.org/bots/tutorial).
You can also check out this video to create the token [here](https://www.youtube.com/shorts/2NFq8AaBWUc).

### 1. Running Locally

#### Prerequisites
- Java 25
- Maven 3.9+
- Telegram account


#### Configuration

Before running the application, set the environment variables TELEGRAM_BASE_URL and BOT_TOKEN.

```bash
export TELEGRAM_BASE_URL="${TELEGRAM_BASE_URL}"
export BOT_TOKEN="${YOUR_BOT_TOKEN}"
```

#### Steps

```bash
# Clone the repo
git clone -b main https://github.com/anish-anantharaman/telegram-notification-service.git

# Build the project
mvn clean install

# Run the unit and integration tests (Optional)
> Currently, the application does not include any automated tests.  
> You can add your own unit or integration tests as needed, and then run:
mvn test

# Run the application
mvn spring-boot:run
```

### 2. Running with Docker (Optional)

For a quick start without building locally, you can use Docker. No tests are included in this Docker build.

```bash
# Build Docker image
docker build -t telegram-notification-service .

# Run the container with the environment variables
docker run \
-e TELEGRAM_BASE_URL="${TELEGRAM_BASE_URL}" \
-e BOT_TOKEN="${BOT_TOKEN}" \
-p 8080:8080 telegram-notification-service
```

This is useful if you want to start the service quickly without installing Java or Maven locally.

### 3. Sending a Test Message

Once the service is running, you can send a test notification using the curl command below:

```bash
curl --location 'http://localhost:8080/api/v1/notifications' \
--header 'Content-Type: application/json' \
--data '{
"chatId": "<telegram_chat_id>",
"title": "Server Down",
"subtitle": "Production Alert",
"content": "The production server is down since 2 PM",
"buttonTitle": "View Details",
"url": "https://www.yahoo.com"
}'
```

### 4. Swagger API Documentation

Once the application is running, open your browser to:

http://localhost:8080/swagger-ui/index.html#/Notifications/sendTelegramNotification
<br />
<br />

## Contributing

This project is open for suggestions, improvements, and pull requests.
Feel free to fork the repo, make changes, and submit a PR. Your contributions are welcome!