# ======================
# 🧱 Build stage
# ======================
FROM maven:3.9.6-eclipse-temurin-21 AS build

# ตั้ง working directory
WORKDIR /app

# คัดลอกไฟล์ pom.xml ก่อน (เพื่อ cache dependencies)
COPY pom.xml .

# ดาวน์โหลด dependencies ล่วงหน้า
RUN mvn dependency:go-offline

# คัดลอก source code ทั้งหมด
COPY src ./src

# สั่ง build โดยข้ามการทดสอบ
RUN mvn clean package -DskipTests

# ======================
# 🚀 Runtime stage
# ======================
FROM eclipse-temurin:21-jdk

# ตั้ง working directory
WORKDIR /app

# คัดลอก JAR จาก stage ก่อนหน้า
COPY --from=build /app/target/*.jar app.jar

# เปิด port 8080 ให้ Render ใช้
EXPOSE 8080

# สั่งรัน Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
