# Use a lightweight OpenJDK runtime as a base image
FROM openjdk:17-slim

# Create a non-root user for security
RUN addgroup --system --gid 1001 appuser && \
    adduser --system --uid 1001 --gid 1001 appuser

# Create app directory
WORKDIR /app

# Copy the packaged JAR file
COPY target/*.jar app.jar

# Copy SSL certificates (if they exist)
COPY zyn/ssl/localhost/keystore.p12 ./zyn/ssl/localhost/keystore.p12
COPY zyn/ssl/prod/keystore.p12 ./zyn/ssl/prod/keystore.p12

# Set proper permissions
RUN chown -R appuser:appuser /app

# Switch to non-root user
USER appuser

# Expose the port
EXPOSE 8036

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]