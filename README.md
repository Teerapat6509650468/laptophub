# LaptopHub: Inter-Service Communication Demo

This project demonstrates a basic **microservice-like architecture** using two Spring Boot web applications: **Laptop Store** and **Laptop Customer**. These applications communicate with each other via REST APIs to showcase inter-service communication using `RestTemplate`.

---

## Applications Overview

### 1. **Laptop Store**
- **Purpose**: Manages a catalog of laptops and provides APIs to fetch laptop details and reserve laptops.
- **Exposed Endpoints**:
  - `GET /laptops`: Fetch all laptops.
  - `POST /laptops/{id}/reserve`: Reserve a laptop by ID.
- **Consumes Services**: Fetches customer data from the **Laptop Customer** application.

### 2. **Laptop Customer**
- **Purpose**: Manages customer data and provides APIs to fetch customer details and add new customers.
- **Exposed Endpoints**:
  - `GET /customers`: Fetch all customers.
  - `POST /customers/addCustomer`: Add a new customer.
- **Consumes Services**: Fetches laptop data and reserves laptops via the **Laptop Store** application.

---

## Communication Between Applications

The two applications communicate using **REST APIs** via `RestTemplate`:
- **Laptop Store** calls the **Laptop Customer** service to fetch customer data and add new customers.
- **Laptop Customer** calls the **Laptop Store** service to fetch laptop data and reserve laptops.

---

## Technologies Used

- **Backend**:
  - Spring Boot (Web, Data JPA, H2 Database)
  - `RestTemplate` for inter-service communication
- **Frontend**:
  - HTML, Bootstrap for UI
  - JavaScript (Fetch API, Axios) for API calls
- **Database**:
  - H2 in-memory database for both applications
- **Build Tool**:
  - Maven

---

## Prerequisites

- **Java**: JDK 17 or higher
- **Maven**: Installed or use the provided Maven wrapper (`mvnw`/`mvnw.cmd`)
- **Ports**:
  - **Laptop Store**: Runs on `http://localhost:8080`
  - **Laptop Customer**: Runs on `http://localhost:8081`

---

## How to Run

### Step 1: Clone the Repository
```bash
git clone https://github.com/yourusername/laptophub.git
cd laptophub
```

### Step 2: Run Each Application

#### Run Laptop Store
```bash
cd laptop-store
./mvnw spring-boot:run
```

#### Run Laptop Customer
```bash
cd laptop-customer
./mvnw spring-boot:run
```

---

## Example Endpoints

### Laptop Store
- **Fetch all laptops**:  
  `GET http://localhost:8080/laptops`
- **Reserve a laptop**:  
  `POST http://localhost:8080/laptops/{id}/reserve`

### Laptop Customer
- **Fetch all customers**:  
  `GET http://localhost:8081/customers`
- **Add a new customer**:  
  `POST http://localhost:8081/customers/addCustomer`

---

## Testing the Communication

### 1. **Using the HTML UI**
- **Laptop Store**:  
  Open `http://localhost:8080` in your browser to:
  - Fetch all customers (via **Laptop Customer** service).
  - Add a new customer.

- **Laptop Customer**:  
  Open `http://localhost:8081` in your browser to:
  - Fetch all laptops (via **Laptop Store** service).
  - Reserve a laptop.

### 2. **Using Postman or cURL**
- Test the endpoints directly using tools like Postman or cURL.

Example cURL commands:
```bash
# Fetch all laptops
curl http://localhost:8081/client/laptops

# Reserve a laptop
curl -X POST http://localhost:8081/client/laptops/1/reserve

# Fetch all customers
curl http://localhost:8080/client/customers

# Add a customer
curl -X POST -H "Content-Type: application/json" \
-d '{"name":"Alice","email":"alice@example.com","phone":"1234567890","address":"123 Main St"}' \
http://localhost:8080/client/customers/add
```

---

## Project Structure

### Laptop Store
- **Frontend**: Displays customer data and allows adding new customers.
- **Backend**: Manages laptop data and communicates with the **Laptop Customer** service.

### Laptop Customer
- **Frontend**: Displays laptop data and allows reserving laptops.
- **Backend**: Manages customer data and communicates with the **Laptop Store** service.

---

## Notes

- This project is for **educational purposes** and demonstrates basic inter-service communication.
- In a production environment, consider:
  - Adding authentication and authorization.
  - Using service discovery (e.g., Eureka) instead of hardcoding URLs.
  - Implementing proper error handling and logging.

---

## License

This project is licensed under the MIT License.