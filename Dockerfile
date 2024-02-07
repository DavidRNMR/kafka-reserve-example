FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copiar la aplicación Java
COPY target/hotel-application-0.0.1-SNAPSHOT.jar /app/app.jar

# Instalar MongoDB
RUN apk add --no-cache mongodb-tools

# Directorio para almacenar datos de MongoDB
VOLUME ["/data/db"]

# Puerto de MongoDB
EXPOSE 27017

# Comando para iniciar la aplicación Java y MongoDB
CMD ["java", "-jar", "app.jar"]
