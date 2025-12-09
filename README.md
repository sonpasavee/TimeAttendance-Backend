# TimeAttendance‑Backend

A RESTful backend API for a time‑attendance system, built with Spring
Boot and Docker.

## 🚀 Features

-   REST API for user & attendance management (register, login, clock
    in/out, view attendance records)
-   JWT / session‑based authentication and authorization
-   CRUD operations for users and attendance data
-   MySQL / database integration (via JPA / Spring Data)
-   API designed for separation between backend and frontend (suitable
    for separate frontend repo)
-   Dockerfile included --- easy to containerize and deploy

## 🧰 Tech Stack

-   Java + Spring Boot
-   Spring Web, Spring Security, Spring Data JPA
-   MySQL (or other SQL DB)
-   Docker
-   Maven

## 📂 Project Structure

    TimeAttendance-Backend/
    ├── src/
    ├── Dockerfile
    ├── pom.xml
    └── .gitignore

## ✅ Getting Started (Local Development)

### Prerequisites

-   Java JDK 17+
-   MySQL
-   Maven
-   Optional: Docker

### Setup

    git clone https://github.com/sonpasavee/TimeAttendance-Backend.git
    cd TimeAttendance-Backend

    mvn clean install
    mvn spring-boot:run

## 📦 Endpoints

- `POST /register` : ลงทะเบียนผู้ใช้
- `POST /login` : เข้าสู่ระบบ
- `GET /attendance/my` : ดึงประวัติการเข้า-ออกของตัวเอง
- `POST /attendance/clockin` : บันทึกเวลาเข้า
- `POST /attendance/clockout` : บันทึกเวลาออก
- `GET /admin/users` : ดึงผู้ใช้ทั้งหมด (ADMIN)
- `POST /admin/users` : สร้างผู้ใช้ใหม่ (ADMIN)
- `PUT /admin/users/{id}` : แก้ไขผู้ใช้ (ADMIN)
- `DELETE /admin/users/{id}` : ลบผู้ใช้ (ADMIN)
