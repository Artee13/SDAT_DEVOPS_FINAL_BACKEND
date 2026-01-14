```md
# Airport Arrivals & Departures – Backend

Spring Boot backend for the SDAT + DevOps Final Sprint project.  
Provides REST API for managing airports, flights, airlines, and gates.

---

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Docker & Docker Compose
- Maven

---

## Entities
- Airport
- Airline
- Gate
- Flight

Relationships:
- Flight → Airport
- Flight → Airline
- Flight → Gate

---

## API Features
- CRUD for all entities
- Filter flights by airport and type (ARRIVAL / DEPARTURE)
- Global error handling
- CORS enabled for frontend
- Health check endpoint

---

## How to Run (Docker – Recommended)

### Prerequisites
- Docker Desktop

### Steps
```bash
docker compose up -d --build