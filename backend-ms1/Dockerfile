# Use Eclipse Temurin with correct tag
FROM eclipse-temurin:17-jre

# Create a non-root user for security
RUN addgroup --system --gid 1001 appuser && \
    adduser --system --uid 1001 --gid 1001 appuser

# Create app directory
WORKDIR /app

# Copy the packaged JAR file
COPY target/*.jar app.jar

# Create SSL directories
RUN mkdir -p ./zyn/ssl/localhost ./zyn/ssl/prod

# Set proper permissions
RUN chown -R appuser:appuser /app

# Switch to non-root user
USER appuser

# Expose the port
EXPOSE 8036

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]