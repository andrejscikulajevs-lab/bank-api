# Mini Bank API

Simple banking backend application built with Spring Boot.

The system provides basic banking functionality:
- User management
- Account creation
- Deposit and withdrawal operations
- Transaction history
- Currency conversion

---

# Tech Stack

Java 17+  
Spring Boot  
Spring Data JPA  
H2 in-memory database  
Maven  

---

# How to Run

Clone the repository and run the application using Maven.  

Application starts on:  
http://localhost:8080

---

# H2 Database Console

Open in browser:  
http://localhost:8080/h2-console  

Connection settings:  
JDBC URL: jdbc:h2:mem:testdb  
User: sa  
Password: (empty)  

---

# API Overview

## Users

Create user:
POST /users?name=John  

Get all users:
GET /users  

Get user by id:
GET /users/{id}  

---

## Accounts

Create account for user:  
POST /users/{userId}/accounts?currency=EUR  

Get user accounts:  
GET /users/{userId}/accounts  

Get account by id:  
GET /accounts/{id}  

---

## Operations

Deposit:  
POST /accounts/{id}/deposit?amount=100  

Withdraw:  
POST /accounts/{id}/debit?amount=50  

Get balance:  
GET /accounts/{id}/balance  

---

## Transactions

Get transactions (paginated):  
GET /accounts/{id}/transactions?page=0&size=10  

Default values:  
page = 0 (first page)  
size = 10  

---

## Currency Conversion

Convert currency:  
GET /currency/convert?amount=100&from=EUR&to=USD  

---

# Example Flow

1. Create user  
POST /users?name=John  

2. Create account  
POST /users/1/accounts?currency=EUR  

3. Deposit money  
POST /accounts/1/deposit?amount=100  

4. Get transactions  
GET /accounts/1/transactions  

---

# Architecture

The application follows layered architecture:

Controller → Service → Repository  

- Controllers handle HTTP requests  
- Services contain business logic  
- Repositories manage database access  

---

# Data Model

User → has many Accounts  
Account → has many Transactions  

User 1 → N Accounts  
Account 1 → N Transactions  

---

# Notes

- H2 database is in-memory and resets after application restart  
- Currency exchange rates are fixed inside the application  
- Pagination is zero-based (page = 0 is the first page)  

---

# Features

- Clean layered architecture  
- RESTful API design  
- JPA relationships  
- Pagination support  
- Currency conversion logic  

---

# Future Improvements

- Global exception handling  
- DTO layer for all responses  
- External currency API integration  
- Authentication and authorization  

---

# Purpose

Demonstrates Spring Boot, REST API design and JPA relationships.