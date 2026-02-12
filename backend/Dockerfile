# Pasul 1: Folosim imaginea oficială de Java 25
FROM eclipse-temurin:25-jdk

# Pasul 2: Instalăm Maven direct în container
RUN apt-get update && apt-get install -y maven

# Pasul 3: Setăm folderul de lucru
WORKDIR /app

# Pasul 4: Copiem fișierul de configurare Maven și descărcăm librăriile
COPY pom.xml .
RUN mvn dependency:go-offline

# Pasul 5: Copiem codul sursă al aplicației
COPY src ./src

# Pasul 6: Comanda care pornește aplicația Spring Boot
CMD ["mvn", "spring-boot:run"]