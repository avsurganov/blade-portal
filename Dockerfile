# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-slim

# Install sbt
RUN apt-get update && \
    apt-get install -y curl && \
    curl -L -o sbt.deb https://sbt-downloads.cdnedge.bluemix.net/releases/v1.5.5/sbt-1.5.5.deb && \
    apt-get install -y ./sbt.deb && \
    rm sbt.deb && \
    apt-get clean

# Set the working directory in the container
WORKDIR /app

# Copy the project files into the container
COPY . /app

# Run sbt to compile the project dependencies
RUN sbt compile

# Expose the port that your application runs on
EXPOSE 8080

# Define the command to run the application using sbt
CMD ["sbt", "run"]