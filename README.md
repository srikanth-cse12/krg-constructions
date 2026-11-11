# KRG CONSTRUCTIONS - Official Web Application

[![Java](https://img.shields.io/badge/Java-17%20LTS-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)](https://www.thymeleaf.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

A modern, production-grade web application for **KRG CONSTRUCTIONS**, a family-owned premier manufacturer of high-density solid cement bricks and supplier of essential construction materials based in Madagondapalli, Tamil Nadu.

Built with **Java 17**, **Spring Boot 3.3.5**, and **Thymeleaf**, the application is engineered for high performance, strict input security, dynamic SEO, responsive mobile design, and seamless customer engagement via integrated WhatsApp enquiry pipelines.

---

## Table of Contents
- [Main Website Features](#main-website-features)
- [Technology Stack](#technology-stack)
- [Project Architecture & Structure](#project-architecture--structure)
- [Prerequisites](#prerequisites)
- [Local Development & Setup](#local-development--setup)
- [Production Deployment](#production-deployment)
- [Application Profiles & Configuration](#application-profiles--configuration)
- [Available Endpoints & SEO](#available-endpoints--seo)
- [Security & Performance](#security--performance)
- [Business Information](#business-information)

---

## Main Website Features

1. **Industrial Brand Identity & Header**
   - High-contrast charcoal, safety orange, and pure white industrial color palette.
   - SVG vector logo with brand typography ensuring sharpness across standard and Retina displays.
   - Responsive sticky navigation bar with mobile hamburger menu and direct call/WhatsApp triggers.

2. **Hero Presentation & Direct Inquiries**
   - Impactful hero showcasing heavy-duty hydraulic brick manufacturing machinery.
   - Direct Call Now (`tel:9787267811`) and WhatsApp enquiry action buttons.
   - Highlights key value propositions: Direct Factory Rates, Tamil Nadu Manufacturing, and 24/7 Availability.

3. **Complete Construction Product Catalog**
   - **Manufactured Products**: High-density 4-inch and 6-inch solid cement bricks cured for maximum compressive strength.
   - **Supplied Raw Materials**: M-Sand (Manufactured Sand), P-Sand (Plastering Sand), 20mm & 40mm Blue Metal Jelly Aggregates, Quarry Stone Dust, and Top Brand Cement Bags.
   - Authentic industrial imagery and specifications for every product.

4. **Interactive 4-Stage Manufacturing Flow**
   - Step 1: Raw Material Proportioning (Cement, quarry dust, blue metal chips).
   - Step 2: High-Tonnage Hydraulic Compaction.
   - Step 3: Standard 14–21 Day Water Pond Curing.
   - Step 4: Quality Stacking & Fleet Dispatch.

5. **Delivery & Logistics Section**
   - Covers approximately 30–35 km radius from Madagondapalli factory (Hosur, Denkanikottai, Thally, Kelamangalam, and surrounding rural development hubs).
   - Dedicated delivery fleet: Heavy-duty Tractor-trailers for rural/narrow site access and Multi-ton Tipper Lorry/Trucks for bulk site drops.
   - Small orders accepted with delivery charges included in product pricing.
   - Google Maps direct factory navigation button (`12°36'18.5"N 77°44'33.8"E`).

6. **Interactive WhatsApp Enquiry System**
   - Real-time client-side and server-side validated contact form.
   - Fields: Customer Name, Phone Number, Product Selection, Quantity, Delivery Location, and Notes.
   - Formats a structured WhatsApp message sent to `+91 81228 95259` with customer review before sending.

7. **Search Engine Optimization (SEO) & Schema.org**
   - Complete Open Graph (OG) and Twitter Card social metadata.
   - JSON-LD Structured Data for `LocalBusiness` / `Manufacturer`.
   - Dynamic `robots.txt` and XML `sitemap.xml` generation with automatic base URL detection.

---

## Technology Stack

| Layer | Technology | Details |
| :--- | :--- | :--- |
| **Language** | Java 17 (LTS) | Modern Java records, pattern matching, enhanced switch |
| **Framework** | Spring Boot 3.3.5 | Spring MVC, Spring Actuator, Spring Test |
| **Template Engine** | Thymeleaf 3.1 | Server-rendered HTML5 templates |
| **Styling** | Custom CSS3 | Modern Flexbox, CSS Grid, CSS Variables, Mobile-first |
| **Frontend Scripting**| Vanilla JavaScript | Zero third-party runtime JS dependencies |
| **Build Tool** | Apache Maven 3.9+ | Standard Maven layout and lifecycle plugins |
| **Server** | Embedded Apache Tomcat | HTTP response compression (Gzip), UTF-8 enforcement |

---

## Project Architecture & Structure

```
KRG/
├── pom.xml                                    # Maven configuration & dependencies
├── .gitignore                                 # Git ignore rules for Java, Maven & IDEs
├── README.md                                  # Complete project documentation
└── src/
    ├── main/
    │   ├── java/com/krg/construction/
    │   │   ├── KrgConstructionsApplication.java  # Main Spring Boot bootstrap class
    │   │   ├── config/
    │   │   │   ├── GlobalExceptionHandler.java   # Centralized error handler & status codes
    │   │   │   └── WebSecurityHeadersFilter.java # Security headers filter (CSP, XSS, etc.)
    │   │   ├── controller/
    │   │   │   ├── CustomErrorController.java    # Custom branded 404/500 error views
    │   │   │   ├── EnquiryController.java        # REST API for enquiry validation
    │   │   │   ├── HealthCheckController.java    # Lightweight /health JSON endpoint
    │   │   │   ├── HomeController.java           # Main landing page controller
    │   │   │   └── SeoController.java            # Dynamic /robots.txt & /sitemap.xml
    │   │   ├── model/
    │   │   │   ├── CompanyInfo.java              # Immutable company details record
    │   │   │   ├── DeliveryFleetItem.java        # Fleet vehicle data model
    │   │   │   ├── EnquiryRequest.java           # Customer enquiry request model
    │   │   │   ├── ManufacturingStage.java       # Process step model
    │   │   │   ├── Product.java                  # Brick & material product model
    │   │   │   └── WhyChooseUsItem.java          # Value proposition model
    │   │   └── service/
    │   │       ├── BaseUrlResolver.java          # Canonical request URL resolver
    │   │       └── ConstructionDataService.java  # Central data service for products & factory
    │   └── resources/
    │       ├── application.properties            # Base common configuration
    │       ├── application-dev.properties        # Development profile (hot reload, debug)
    │       ├── application-prod.properties       # Production profile (caching, hardened)
    │       ├── static/
    │       │   └── images/                       # Authentic product & factory photos (.jpg, .svg)
    │       └── templates/
    │           ├── index.html                    # Responsive single-page application template
    │           └── error.html                    # Custom branded error page
    └── test/
        └── java/com/krg/construction/
            ├── HomeControllerTest.java           # Controller & page structure unit tests
            └── KrgConstructionsApplicationTests.java # Context loading tests
```

---

## Prerequisites

Ensure you have the following installed on your machine:
- **Java Development Kit (JDK) 17** or higher
  ```bash
  java -version
  ```
- **Apache Maven 3.8+**
  ```bash
  mvn -version
  ```
- **Git** (for version control)

---

## Local Development & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/krg-constructions.git
cd krg-constructions
```

### 2. Build the Application
Compile the source code and build the executable JAR:
```bash
mvn clean package -DskipTests
```

### 3. Run Locally with Development Profile
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
Or execute the built JAR directly:
```bash
java -Dspring.profiles.active=dev -jar target/krg-constructions-0.0.1-SNAPSHOT.jar
```

### 4. Access the Website
Open your web browser and navigate to:
```
http://localhost:8080
```

---

## Production Deployment

### 1. Build Executable JAR
```bash
mvn clean package -DskipTests
```
The production-ready self-contained JAR is generated at:
`target/krg-constructions-0.0.1-SNAPSHOT.jar`

### 2. Run with Production Profile
```bash
java -Dspring.profiles.active=prod -jar target/krg-constructions-0.0.1-SNAPSHOT.jar
```

### 3. Deploying with Custom Port & Host
The application binds by default to `0.0.0.0` and port `8080`. You can customize both using standard environment variables:

```bash
# On Linux/macOS
export PORT=80
export SERVER_ADDRESS=0.0.0.0
export SPRING_PROFILES_ACTIVE=prod
java -jar target/krg-constructions-0.0.1-SNAPSHOT.jar

# On Windows (PowerShell)
$env:PORT="8080"
$env:SPRING_PROFILES_ACTIVE="prod"
java -jar target\krg-constructions-0.0.1-SNAPSHOT.jar
```

---

## Application Profiles & Configuration

The application uses multi-profile configuration architecture:

| Property | Default Value | Description |
| :--- | :--- | :--- |
| `SPRING_PROFILES_ACTIVE` | `prod` | Active Spring profile (`dev` or `prod`) |
| `PORT` | `8080` | HTTP listener port |
| `SERVER_ADDRESS` | `0.0.0.0` | Network binding interface (enables local Wi-Fi mobile testing) |
| `DATABASE_URL` | *Optional* | Neon PostgreSQL URI (`postgresql://user:pass@ep-xyz.us-east-2.aws.neon.tech/neondb?sslmode=require`) |
| `SPRING_DATASOURCE_URL` | *Optional* | Standard JDBC URL (`jdbc:postgresql://ep-xyz.us-east-2.aws.neon.tech/neondb?sslmode=require`) |
| `SPRING_DATASOURCE_USERNAME` | *Optional* | Neon database user name |
| `SPRING_DATASOURCE_PASSWORD` | *Optional* | Neon database password |
| `APP_BASE_URL` | *Auto-detected* | Canonical public URL (e.g., `https://krgconstructions.com`) |
| `APP_HERO_IMAGE` | `/images/hero-cement-brick-machine.jpg` | Overridable hero banner image path |
| `APP_ABOUT_IMAGE` | `/images/solid-cement-bricks-cured.jpg` | Overridable factory yard image path |

- **Development (`application-dev.properties`)**: Disables Thymeleaf template caching for rapid editing, sets verbose logging, enables detailed error messages.
- **Production (`application-prod.properties`)**: Enables Thymeleaf caching, sets 24-hour static asset caching, enables Gzip compression, silences stacktraces on error pages.

---

## Available Endpoints & SEO

| Endpoint | HTTP Method | Description |
| :--- | :--- | :--- |
| `/` | `GET` | Main responsive landing page |
| `/health` | `GET` | Lightweight JSON health status (`{"status":"UP", ...}`) |
| `/actuator/health` | `GET` | Production Spring Boot Actuator probe |
| `/robots.txt` | `GET` | Dynamic robots directives linking to dynamic sitemap |
| `/sitemap.xml` | `GET` | Dynamic XML sitemap for Google & Bing web crawlers |
| `/api/enquiry/submit` | `POST` | Validates, deduplicates, saves enquiry to PostgreSQL, and generates WhatsApp URL |
| `/api/enquiry/validate` | `POST` | Compatibility endpoint for enquiry validation and persistence |
| `/error` | `GET`, `POST` | Custom branded error page for 404, 500, and HTTP exceptions |

---

## Security & Performance

- **Persistent Customer Enquiry Storage**: Customer orders and enquiries are validated, deduplicated, and stored securely in PostgreSQL (Neon serverless cloud database in production, or in-memory PostgreSQL-compatible mode during local development).
- **Zero Public Exposure of Customer Data**: Strictly no public endpoints or unauthenticated dashboards expose customer telephone numbers, names, or site delivery addresses.
- **HTTP Security Headers**: Injected via `WebSecurityHeadersFilter`:
  - `X-Content-Type-Options: nosniff`
  - `X-Frame-Options: SAMEORIGIN`
  - `X-XSS-Protection: 1; mode=block`
  - `Referrer-Policy: strict-origin-when-cross-origin`
- **Input Sanitization**: Server-side and client-side sanitization strips HTML tags, script injection patterns, and malformed characters.
- **Mobile Network Optimization**: Gzip compression enabled for HTML, CSS, JS, JSON, XML, and SVG. Lazy loading configured for below-the-fold factory images.

---

## Business Information

- **Business Name**: KRG CONSTRUCTIONS
- **Factory Location**: JP4V+335, Madagondapalli, Tamil Nadu, India
- **Coordinates**: `12°36'18.5"N 77°44'33.8"E`
- **Primary Contact**: +91 97872 67811
- **WhatsApp Support**: +91 81228 95259
- **Operating Hours**: Open 24 Hours
- **Ownership**: Family-owned business

---

## License & Copyright

© 2026 KRG CONSTRUCTIONS. All rights reserved.

