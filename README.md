Micro-Shop Pro: A Full-Stack E-Commerce Microservice Project
Welcome to Micro-Shop Pro! This is a complete, end-to-end e-commerce platform built from the ground up to demonstrate a wide range of modern, industry-standard technologies and architectural patterns. The project features a sophisticated, containerized backend built with Java & Spring, and a responsive, feature-rich frontend built with React.

This project was built to showcase a real-world application of microservice principles, including service discovery, centralized configuration, security, resilience, and asynchronous communication.

Features
Backend
8 Independent Microservices: A complete backend featuring services for Products, Orders, Inventory, Authentication, API Gateway, Service Discovery, Centralized Configuration, and Payments.

Containerized with Docker: The entire 11-part backend stack (including PostgreSQL, RabbitMQ, and Zipkin) is orchestrated with a single docker-compose.yml file for easy, one-command startup.

Centralized Configuration: All service properties are managed in a central Git repository and served by the Config Server.

Service Discovery: Services dynamically find and communicate with each other using Eureka.

Secure: The system is secured at the edge by the API Gateway, which validates JWTs issued by a dedicated Authorization Server.

Resilient & Observable: Implements the Circuit Breaker pattern with Resilience4j to prevent cascading failures and Distributed Tracing with Zipkin to monitor requests across the entire system.

Asynchronous, Event-Driven Architecture: The Order, Payment, and Inventory services are decoupled using RabbitMQ, making the system more robust and scalable.

Professional Database Management: The database schema is managed and version-controlled using Flyway migrations.

Live API Documentation: All backend services feature automatically generated, interactive API documentation via SpringDoc OpenAPI (Swagger).

Frontend
Modern UI: A responsive and professional user interface built with React and the Material-UI (MUI) component library.

Complete Authentication Flow: Users can register for new accounts and log in. The application manages the user's session using React Context and localStorage.

Protected Routing: A robust routing system where only authenticated users can view protected pages like the product list, cart, and order history.

Full E-Commerce User Journey:

Browse a grid of products.

View a detailed product page with an image gallery.

Add/remove items and update quantities in a persistent shopping cart.

A multi-step checkout process for collecting a shipping address.

A simulated payment flow that updates the order status in real-time.

A dedicated page for users to view their complete order history.

System Architecture
Technology Stack
Category

Technology

Backend

Java 17/21, Spring Boot, Spring Cloud

Frontend

React.js, Material-UI (MUI), React Router, Axios

Databases

PostgreSQL, Flyway (for migrations)

Messaging

RabbitMQ

Containerization

Docker, Docker Compose

Observability

Zipkin, Micrometer

Resilience

Resilience4j (Circuit Breaker)

Security

Spring Security, JWT

API Docs

SpringDoc OpenAPI (Swagger)

Service Mesh

Netflix Eureka, Spring Cloud Gateway, Spring Cloud Config

How to Run
Prerequisites
Docker & Docker Compose

Node.js and npm

A Git client

1. Clone the Repositories
First, clone this main project repository and the separate configuration repository.

# Clone the main application
git clone https://github.com/your-username/microshop-pro-full-stack.git

# Clone the configuration repository
git clone https://github.com/your-username/microshop-pro-config.git

2. Configure the Backend
Navigate into the main project folder. You will need to update the docker-compose.yml file to point to your local copy of the configuration repository.

Open docker-compose.yml.

Find the config-server service.

Change the SPRING_CLOUD_CONFIG_SERVER_GIT_URI to point to the file path of your local config repo.

environment:
  - SPRING_CLOUD_CONFIG_SERVER_GIT_URI=file:///path/to/your/local/microshop-pro-config

3. Launch the Backend
From the root of the microshop-pro-full-stack directory, run the magic command:

docker compose up --build

This will build all the service images and start all 11 backend containers. It will take several minutes on the first run.

4. Launch the Frontend
Open a new terminal.

Navigate into the frontend directory: cd frontend

Install dependencies: npm install

Start the development server: npm start

Your browser will automatically open to http://localhost:3000, and the application will be ready to use!

Verification
Check Containers: Run docker ps to see all 11 containers running.

Check Eureka: Navigate to http://localhost:8761 to see all services registered and UP.

Check API Docs: Navigate to http://localhost:8000/swagger-ui.html to see the unified API documentation.

Use the App: Register a new user, log in, add products to your cart, and complete the checkout process.
