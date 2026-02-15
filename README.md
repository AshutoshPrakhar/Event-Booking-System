# 🎟️ Event Booking System (Spring Boot + JWT)

A RESTful backend application for managing events and booking tickets.
Built using **Spring Boot, Spring Security, JWT Authentication, JPA, and MySQL**.

This project demonstrates real-world backend architecture including authentication, authorization, business logic, and transactional operations.

---

## 🚀 Features

### Authentication & Authorization

* User Registration & Login
* JWT Token based authentication
* Role Based Access Control

  * **ORGANIZER** → Manage events
  * **CUSTOMER** → Book tickets

### Event Management

* Organizer can create events
* Organizer can update events
* View all available events (public)

### Ticket Booking

* Customers can book tickets
* Automatic ticket availability update
* Prevent over-booking
* Transactional booking system

### Booking Management

* Customers can view their bookings
* Organizers can view bookings of their events

### Background Task

* Booking confirmation notification service

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot
* Spring Security
* JWT (io.jsonwebtoken)
* Spring Data JPA (Hibernate)
* MySQL
* Maven

---

## 📦 API Endpoints

### Auth APIs

| Method | Endpoint           | Access | Description       |
| ------ | ------------------ | ------ | ----------------- |
| POST   | /api/auth/register | Public | Register user     |
| POST   | /api/auth/login    | Public | Login and get JWT |

---

### Event APIs

| Method | Endpoint                  | Access    |
| ------ | ------------------------- | --------- |
| GET    | /api/events               | Public    |
| POST   | /api/events/create        | ORGANIZER |
| PUT    | /api/events/update/{id}   | ORGANIZER |
| GET    | /api/events/{id}/bookings | ORGANIZER |

---

### Booking APIs

| Method | Endpoint                      | Access   |
| ------ | ----------------------------- | -------- |
| POST   | /api/bookings/{eventId}?qty=2 | CUSTOMER |
| GET    | /api/bookings/my              | CUSTOMER |

---

## 🔐 Authorization

All protected APIs require header:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## ⚙️ Setup Instructions

### 1. Clone repository

```
git clone https://github.com/your-username/event-booking-system.git
cd event-booking-system
```

### 2. Configure Database

Update `application.properties`

```
spring.datasource.url=jdbc:mysql://localhost:3306/event_booking
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_secret_key
jwt.expiration=86400000
```

### 3. Run Application

```
mvn spring-boot:run
```

Server starts at:

```
http://localhost:8080
```

---

## 🧪 Testing

Use Postman:

1. Register user
2. Login → copy token
3. Add token in Authorization → Bearer Token
4. Access protected APIs

---

## 🧠 Concepts Covered

* Stateless Authentication (JWT)
* Spring Security Filters
* Role Based Authorization
* Transaction Management
* Entity Relationships (OneToMany, ManyToOne)
* Exception Handling
* REST API Design

---

## 📌 Future Improvements

* Email notification service
* Payment integration
* Event images upload
* Pagination & search
* Refresh tokens

---

## 👨‍💻 Author

Ashutosh Prakhar
