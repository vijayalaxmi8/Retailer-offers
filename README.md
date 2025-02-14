# Retailer Rewards Program

## 📌 Overview
This is a Spring Boot 3.4.2 application that calculates reward points for a retailer's rewards program based on customer transactions.

## 🚀 Features
- Customer registration and retrieval
- Transaction processing
- Reward point calculation based on purchase amounts
- Monthly and total reward points tracking
- RESTful API with Swagger documentation

## 🛠️ Tech Stack
- **Java 17**
- **Spring Boot 3.4.2** (Spring MVC, Spring Data JPA, Spring Validation)
- **H2 Database / PostgreSQL**
- **Lombok** (for reducing boilerplate code)
- **JUnit 5** & **Mockito** (for unit testing)
- **Swagger UI** (for API documentation)

## 🏗️ Project Setup

### **1️⃣ Prerequisites**
Make sure you have the following installed:
- **Java 17** (`java -version` to check)
- **Maven 3+** (`mvn -version` to check)
- **Git**

### **2️⃣ Clone the Repository**
git clone https://github.com/vijayalaxmi8/Retailer-offers.git
cd Retailer-offers

### **3️⃣ Build the Project**
mvn clean package

### **4️⃣ Run the Application**
java -jar target/Retailer-offers-1.0.0.jar
By default, the application will start on `http://localhost:8080`

### **5️⃣ Run in Development Mode**
mvn spring-boot:run


---

## 🔥 API Endpoints

### **Customer APIs**
| Method | Endpoint               | Description |
|--------|------------------------|-------------|
| GET    | `/customers`            | Get all customers |
| GET    | `/customers/{id}`       | Get customer by ID |
| POST   | `/customers`            | Create a new customer |

### **Transaction APIs**
| Method | Endpoint                   | Description |
|--------|----------------------------|-------------|
| GET    | `/transactions`             | Get all transactions |
| GET    | `/transactions/{customerId}`| Get transactions by customer ID |
| POST   | `/transactions`             | Add a new transaction |

### **Rewards APIs**
| Method | Endpoint              | Description |
|--------|-----------------------|-------------|
| GET    | `/rewards/{customerId}` | Get reward points by customer ID |


## 📖 Swagger API Documentation
Once the app is running, access Swagger UI at:
http://localhost:8080/swagger-ui/index.html

---

## 🧪 Running Tests
To run unit tests:
mvn test


## 🛠️ Configuration (application.properties)
Example properties:
server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

## 👨‍💻 Author
- **Vijayalaxmi k**

---


### ✅ Now your Spring Boot 3.4.2 application is well documented! 🚀

