# AI Prompt Service with Circuit Breaker

A Spring Boot 3.5 application that demonstrates asynchronous WebClient calls to the OpenAI API,  
including retry logic, timeout handling, global exception handling, and Resilience4j Circuit Breaker.  
Built with Java 17, ready for production-grade learning.

---

## Features

- **Async WebClient Calls** – Non-blocking HTTP requests to AI APIs.
- **Error Handling** – Handles client errors (4xx), server errors (5xx), and rate limits.
- **Retry Mechanism** – Automatically retries failed requests using Resilience4j Retry.
- **Circuit Breaker** – Handles service downtime gracefully with fallback responses.
- **Timeout Management** – Requests automatically timeout to prevent hanging calls.
- **Global Exception Handling** – Centralized exception handling for clean API responses.
- **Environment Variable for API Key** – Securely store your OpenAI key using `.env` or system variables.

---

## Tech Stack

- Java 17  
- Spring Boot 3.5  
- WebClient (Reactive, Async HTTP calls)  
- Resilience4j (Retry & Circuit Breaker)  
- Maven  
- JUnit 5 / Spring Boot Test  

---

## Test the endpoint using Postman

**POST** `http://localhost:8080/api/ai/prompt/circuit`  
**Content-Type:** `application/json`

**Body:**
```json
{
  "prompt": "Hello AI, write a short poem about Java."
}

##Author
Mohana P – Senior Java & Spring Boot Developer
GitHub: https://github.com/MohanaGowda-Code
