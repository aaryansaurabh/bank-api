# Bank API

A secure RESTful Banking API built with Spring Boot,featuring JWT authentication,account management,fund transfers,& email notifications.

## Demo
[Swagger UI](https://bank-api-ci3c.onrender.com/swagger-ui/index.html)

## Tech Stack
- Java 17 + Spring Boot 3
- Spring Security + JWT
- MySQL (Aiven)
- JavaMailSender (Gmail SMTP)
- Swagger / OpenAPI
- Docker + Render

## Features
- User Registration & Login with JWT
- Account Creation (Savings/Current)
- Deposit, Withdraw & Transfer
- Transaction History
- Email Notifications

##  API Endpoints
| Method |      Endpoint            | Description |
| POST   | /api/auth/register       | Register user |
| POST   | /api/auth/login          | Login & get JWT |
| GET    | /api/account/balance     | Check balance |
| POST   | /api/account/deposit     | Deposit money |
| POST   | /api/account/withdraw    | Withdraw money |
| POST   | /api/account/transfer    | Transfer funds |
| GET    | /api/account/transaction | Transaction history |

## ⚙️ Setup
1. Clone the repo
2. Fill in your credentials `application.yml` 
4. Run the app
