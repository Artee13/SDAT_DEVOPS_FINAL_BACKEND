# Data Model – Airport Arrivals App

## Entities

### 1. Airport

Fields:
- id (PK, long)
- code (string, unique) e.g. "YYT"
- name (string)
- city (string)
- country (string)
- timezone (string) e.g. "America/St_Johns"

Relationships:
- One Airport has many Gates
- One Airport has many Flights

---

### 2. Airline

Fields:
- id (PK, long)
- code (string) e.g. "AC"
- name (string) e.g. "Air Canada"
- country (string)
- logoUrl (string, optional)

Relationships:
- One Airline has many Flights

---

### 3. Gate

Fields:
- id (PK, long)
- name (string) e.g. "A12"
- terminal (string, optional)
- airport_id (FK -> Airport)

Relationships:
- One Gate belongs to one Airport
- One Gate has many Flights

---

### 4. Aircraft

Fields:
- id (PK, long)
- model (string) e.g. "Boeing 737-800"
- manufacturer (string)
- seatCapacity (int)

Relationships:
- One Aircraft has many Flights

---

### 5. Flight

Fields:
- id (PK, long)
- flightNumber (string) e.g. "AC123"
- type (string: "ARRIVAL" or "DEPARTURE")
- status (string: "ON_TIME", "DELAYED", "CANCELLED", etc.)
- scheduledTime (datetime)
- estimatedTime (datetime, optional)
- origin (string)
- destination (string)
- airport_id (FK -> Airport)
- airline_id (FK -> Airline)
- gate_id (FK -> Gate)
- aircraft_id (FK -> Aircraft, optional)

Relationships:
- Many Flights belong to one Airport
- Many Flights belong to one Airline
- Many Flights belong to one Gate
- Many Flights use one Aircraft
