# 🌮 Taco Cloud - Spring Boot Application

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-42.6.0-blue)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](https://opensource.org/licenses/MIT)

Полнофункциональное приложение для заказа такос с двумя интерфейсами:
- **Веб-интерфейс** (Spring MVC + Thymeleaf) для пользователей
- **REST API** для интеграций и администрирования

## 🌟 Основной функционал

### Веб-интерфейс (MVC)
- ✅ Регистрация и аутентификация пользователей
- ✅ Создание собственных такос через интерактивный конструктор
- ✅ Оформление заказов и передача через Kafka на терминалы "кухни"

### REST API
- 🛠️ Управление ингредиентами (CRUD операции)
- 📦 Работа с заказами (создание/просмотр)
- 🔄 Интеграция с Kafka для обработки заказов
- 📊 Мониторинг через Actuator endpoints

## 📦 Технологический стек

**Основные технологии**:
- Spring Boot 3.5.0
- Spring MVC + Thymeleaf
- Spring Data JPA + PostgreSQL
- Spring Security (OAuth2 + JWT)

**Интеграции**:
- Kafka для асинхронной обработки
- Spring Mail для уведомлений
- Spring Data REST для автоматического REST API
