FROM amazoncorretto:17.0.12

WORKDIR /app

COPY build/libs/transaction-0.0.1-SNAPSHOT.jar /app/transaction-0.0.1-SNAPSHOT.jar

EXPOSE 8082:8082

CMD ["java", "-jar", "transaction-0.0.1-SNAPSHOT.jar"]