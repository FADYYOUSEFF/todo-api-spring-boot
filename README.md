# 📝 To-Do API (Spring Boot)

A secure and scalable **To-Do REST API** built with **Spring Boot**, featuring JWT authentication, PostgreSQL persistence, and user-specific task management.

---

## 🚀 Features

- 🔒 **Authentication & Authorization**
  - Spring Security with **JWT (JSON Web Tokens)**.
  - Custom **JWT Service** and **JWT Filter** integrated in the Security Filter Chain.
  - Passwords stored securely using **HSA-256 hashing**.

- 📌 **To-Do Management**
  - CRUD operations: create, read, update, delete todos.
  - Each user can only manage their own todos (foreign key relation in PostgreSQL).

- 📦 **DTOs & Mapping**
  - DTO layer for clean API responses/requests.
  - **MapStruct** used for mapping entities to DTOs.
  - Sensitive fields (e.g., password, secrets) hidden from responses.

- 🗄 **Database**
  - **PostgreSQL** relational database.
  - Users and Todos linked via `user_id`.

- 🧪 **Testing**
  - Unit tests for repositories (including custom query methods).
  - Unit tests for services and controllers.

---

## 🛠 Tech Stack

- **Spring Boot**
- **Spring Security + JWT**
- **MapStruct**
- **PostgreSQL**
- **JUnit & Mockito**

