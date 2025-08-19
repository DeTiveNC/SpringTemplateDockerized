<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=git,githubactions,kubernetes,docker,gradle,postgres,spring,kotlin&perline=8" />
  </a>
</p>

<h1 align="center">Spring Boot, Docker, and Kubernetes Template</h1>

<p align="center">
  A ready-to-use, containerized Spring Boot application template for rapid development and deployment.
  <br/>
  <br/>
  <a href="https://github.com/DeTiveNC/SpringTemplateDockerized/issues">Report Bug</a>
  ·
  <a href="https://github.com/DeTiveNC/SpringTemplateDockerized/issues">Request Feature</a>
</p>


  ![Contributors](https://img.shields.io/github/contributors/DeTiveNC/SpringTemplateDockerized?color=dark-green)
  ![Forks](https://img.shields.io/github/forks/DeTiveNC/SpringTemplateDockerized?style=social)
  ![Stargazers](https://img.shields.io/github/stars/DeTiveNC/SpringTemplateDockerized?style=social)
  ![Issues](https://img.shields.io/github/issues/DeTiveNC/SpringTemplateDockerized)
  ![License](https://img.shields.io/github/license/DeTiveNC/SpringTemplateDockerized)
  [![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/DeTiveNC/SpringTemplateDockerized)

## About The Project

This project provides a solid foundation for building and deploying Spring Boot applications. It comes pre-configured with Docker for containerization, PostgreSQL as the database, and Kubernetes for orchestration. It's designed to help you get your web service up and running quickly, with a focus on modern development practices.

### Features

*   **Spring Boot:** The latest version for building robust Java and Kotlin applications.
*   **Docker:** Containerize your application for consistent environments and easy scaling.
*   **PostgreSQL:** A powerful, open-source object-relational database system.
*   **Kubernetes:** Pre-configured for deploying your application to a K8s cluster.
*   **JWT Authentication:** Secure your endpoints with JSON Web Tokens.
*   **Gradle:** A powerful build automation tool.

### Built With

*   [Spring Boot](https://spring.io/projects/spring-boot)
*   [Kotlin](https://kotlinlang.org/)
*   [Gradle](https://gradle.org/)
*   [Docker](https://www.docker.com/)
*   [PostgreSQL](https://www.postgresql.org/)
*   [Kubernetes](https://kubernetes.io/)

## Getting Started

Follow these steps to get a local copy of the project up and running.

### Prerequisites

*   Docker
*   JDK 17 or later

### Installation

1.  **Clone the repo**
    ```sh
    git clone https://github.com/DeTiveNC/SpringTemplateDockerized.git
    ```
2.  **Build the project**
    Use the Gradle wrapper to build the project and create the JAR file.
    ```sh
    ./gradlew build
    ```
3.  **Run with Docker Compose**
    This will start the application and the PostgreSQL database.
    ```sh
    docker-compose up
    ```

## Usage

This application provides both a web interface using Vaadin and REST API endpoints for authentication and user management.

### Web Interface (Vaadin)

Once the application is running, you can access the Vaadin web interface:

1. **Login Page**: Navigate to `http://localhost:8080/login`
   - Enter your email address and password
   - Click **"Login"** to authenticate with existing credentials
   - Click **"Register"** to create a new user account
   - Upon successful authentication, a JWT token will be displayed in a notification

The web interface provides a user-friendly way to:
- Register new user accounts
- Login with existing credentials
- View JWT tokens for API access

### REST API Endpoints

The application also exposes the following API endpoints for programmatic access:

*   **Register a new user:**
    `POST /api/auth/register`
    <br>
    Request body:
    ```json
    {
      "email": "user@example.com",
      "password": "password"
    }
    ```
    Response:
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

*   **Login:**
    `POST /api/auth/login`
    <br>
    Request body:
    ```json
    {
      "email": "user@example.com",
      "password": "password"
    }
    ```
    Response:
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

*   **Health Check:**
    `GET /health`

### Getting Started with Authentication

1. **Start the application** using Docker Compose:
   ```sh
   docker-compose up
   ```

2. **Access the web interface** at `http://localhost:8080/login`

3. **Register a new user** by entering an email and password, then clicking "Register"

4. **Login** with your credentials to receive a JWT token

5. **Use the JWT token** in the `Authorization: Bearer <token>` header for protected API endpoints

You can use the web interface for easy interaction, or use tools like `curl` or Postman to interact directly with the REST API.

## Deployment

To deploy the application, you can build and push the Docker image to a container registry.

1.  **Build the Docker image**
    ```sh
    docker compose build
    ```
2.  **Push the Docker image**
    Make sure to tag the image with your registry's username and a tag.
    ```sh
    docker push <your-username>/java-app:<tag-name>
    ```

## Kubernetes

This project includes a `k8s` directory with Kubernetes manifests to deploy the application. You can use a tool like [Minikube](https://minikube.sigs.k8s.io/docs/) to run a local Kubernetes cluster.

The manifests were generated using [Kompose](https://kompose.io/), which translates Docker Compose files into Kubernetes resources.

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Authors

*   **Nicolas Cao** - *Comp Eng Student* - [Nicolas Cao](https://github.com/detivenc) 
