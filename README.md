# LaptopHub: การสื่อสารระหว่างบริการ (Inter-Service Communication Demo)

[Switch to English Version](#laptophub-inter-service-communication-demo)

โปรเจกต์นี้เป็นตัวอย่างการสร้าง **สถาปัตยกรรมแบบไมโครเซอร์วิส (Microservice-like Architecture)** โดยใช้แอปพลิเคชัน Spring Boot สองตัว ได้แก่ **Laptop Store** และ **Laptop Customer** ซึ่งแอปพลิเคชันทั้งสองนี้สื่อสารกันผ่าน REST APIs เพื่อแสดงตัวอย่างการสื่อสารระหว่างบริการโดยใช้ `RestTemplate` 

---

## ภาพรวมของแอปพลิเคชัน

### 1. **Laptop Store**
- **วัตถุประสงค์**: จัดการแคตตาล็อกของแล็ปท็อป และให้บริการ API สำหรับดึงข้อมูลแล็ปท็อปและการจองแล็ปท็อป
- **Endpoints ที่ให้บริการ**:
  - `GET /laptops`: ดึงข้อมูลแล็ปท็อปทั้งหมด
  - `POST /laptops/{id}/reserve`: จองแล็ปท็อปตาม ID
- **บริการที่ใช้**: ดึงข้อมูลลูกค้าจากแอปพลิเคชัน **Laptop Customer**

### 2. **Laptop Customer**
- **วัตถุประสงค์**: จัดการข้อมูลลูกค้า และให้บริการ API สำหรับดึงข้อมูลลูกค้าและเพิ่มลูกค้าใหม่
- **Endpoints ที่ให้บริการ**:
  - `GET /customers`: ดึงข้อมูลลูกค้าทั้งหมด
  - `POST /customers/addCustomer`: เพิ่มลูกค้าใหม่
- **บริการที่ใช้**: ดึงข้อมูลแล็ปท็อปและจองแล็ปท็อปผ่านแอปพลิเคชัน **Laptop Store**

---

## การสื่อสารระหว่างแอปพลิเคชัน

แอปพลิเคชันทั้งสองสื่อสารกันผ่าน **REST APIs** โดยใช้ `RestTemplate`:
- **Laptop Store** เรียกใช้บริการของ **Laptop Customer** เพื่อดึงข้อมูลลูกค้าและเพิ่มลูกค้าใหม่
- **Laptop Customer** เรียกใช้บริการของ **Laptop Store** เพื่อดึงข้อมูลแล็ปท็อปและจองแล็ปท็อป

---

## เทคโนโลยีที่ใช้

- **Backend**:
  - Spring Boot (Web, Data JPA, H2 Database)
  - `RestTemplate` สำหรับการสื่อสารระหว่างบริการ
- **Frontend**:
  - HTML, Bootstrap สำหรับ UI
  - JavaScript (Fetch API, Axios) สำหรับการเรียก API
- **Database**:
  - H2 in-memory database สำหรับทั้งสองแอปพลิเคชัน
- **Build Tool**:
  - Maven

---

## ข้อกำหนดเบื้องต้น

- **Java**: JDK 17 หรือสูงกว่า
- **Maven**: ติดตั้งไว้แล้ว หรือใช้ Maven wrapper (`mvnw`/`mvnw.cmd`)
- **Ports**:
  - **Laptop Store**: ทำงานที่ `http://localhost:8080`
  - **Laptop Customer**: ทำงานที่ `http://localhost:8081`

---

## วิธีการรัน

### ขั้นตอนที่ 1: Clone Repository
```bash
git clone https://github.com/yourusername/laptophub.git
cd laptophub
```

### ขั้นตอนที่ 2: รันแต่ละแอปพลิเคชัน

#### รัน Laptop Store
```bash
cd laptop-store
./mvnw spring-boot:run
```

#### รัน Laptop Customer
```bash
cd laptop-customer
./mvnw spring-boot:run
```

---

## ตัวอย่าง Endpoints

### Laptop Store
- **ดึงข้อมูลแล็ปท็อปทั้งหมด**:  
  `GET http://localhost:8080/laptops`
- **จองแล็ปท็อป**:  
  `POST http://localhost:8080/laptops/{id}/reserve`

### Laptop Customer
- **ดึงข้อมูลลูกค้าทั้งหมด**:  
  `GET http://localhost:8081/customers`
- **เพิ่มลูกค้าใหม่**:  
  `POST http://localhost:8081/customers/addCustomer`

---

## การทดสอบการสื่อสาร

### 1. **ผ่าน HTML UI**
- **Laptop Store**:  
  เปิด `http://localhost:8080` ในเบราว์เซอร์เพื่อ:
  - ดึงข้อมูลลูกค้าทั้งหมด (ผ่านบริการ **Laptop Customer**)
  - เพิ่มลูกค้าใหม่

- **Laptop Customer**:  
  เปิด `http://localhost:8081` ในเบราว์เซอร์เพื่อ:
  - ดึงข้อมูลแล็ปท็อปทั้งหมด (ผ่านบริการ **Laptop Store**)
  - จองแล็ปท็อป

### 2. **ผ่าน Postman หรือ cURL**
- ทดสอบ Endpoints โดยตรงผ่านเครื่องมืออย่าง Postman หรือ cURL

ตัวอย่างคำสั่ง cURL:
```bash
# ดึงข้อมูลแล็ปท็อปทั้งหมด
curl http://localhost:8081/client/laptops

# จองแล็ปท็อป
curl -X POST http://localhost:8081/client/laptops/1/reserve

# ดึงข้อมูลลูกค้าทั้งหมด
curl http://localhost:8080/client/customers

# เพิ่มลูกค้าใหม่
curl -X POST -H "Content-Type: application/json" \
-d '{"name":"Alice","email":"alice@example.com","phone":"1234567890","address":"123 Main St"}' \
http://localhost:8080/client/customers/add
```

---

## โครงสร้างโปรเจกต์

### Laptop Store
- **Frontend**: แสดงข้อมูลลูกค้าและเพิ่มลูกค้าใหม่
- **Backend**: จัดการข้อมูลแล็ปท็อปและสื่อสารกับบริการ **Laptop Customer**

### Laptop Customer
- **Frontend**: แสดงข้อมูลแล็ปท็อปและจองแล็ปท็อป
- **Backend**: จัดการข้อมูลลูกค้าและสื่อสารกับบริการ **Laptop Store**

---

## หมายเหตุ

- โปรเจกต์นี้จัดทำขึ้นเพื่อ **การศึกษา** และแสดงตัวอย่างการสื่อสารระหว่างบริการเบื้องต้น
- ในสภาพแวดล้อมการผลิต ควรพิจารณา:
  - เพิ่มการยืนยันตัวตนและการอนุญาต (Authentication & Authorization)
  - ใช้ Service Discovery (เช่น Eureka) แทนการกำหนด URL แบบฮาร์ดโค้ด
  - เพิ่มการจัดการข้อผิดพลาดและการบันทึก Log อย่างเหมาะสม

---

## ใบอนุญาต

โปรเจกต์นี้อยู่ภายใต้ลิขสิทธิ์แบบ MIT License

---

# LaptopHub: Inter-Service Communication Demo

[Switch to Thai Version](#laptophub-การสื่อสารระหว่างบริการ-inter-service-communication-demo)

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