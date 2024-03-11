<p align="center">
  <img src="https://github.com/DeTiveNC/SpringTemplateDockerized/assets/116792124/a123ca6f-e2a6-4566-9611-d2c3d505da23" alt="Logo" width="80" height="80">
  <img src="https://github.com/DeTiveNC/SpringTemplateDockerized/assets/116792124/d4f4c729-4758-444d-afea-f47fe2b81c93" alt="Logo" width="30" height="30">
  <img src="https://github.com/DeTiveNC/SpringTemplateDockerized/assets/116792124/7ed10241-b326-4a43-a3b9-fb4551cbed6c" alt="Logo" width="80" height="80">
  <img src="https://github.com/DeTiveNC/SpringTemplateDockerized/assets/116792124/d4f4c729-4758-444d-afea-f47fe2b81c93" alt="Logo" width="30" height="30">
  <img src="https://github.com/DeTiveNC/SpringTemplateDockerized/assets/116792124/f5f7d528-4845-40c2-a3e4-92d2a8821fe3" alt="Logo" width="80" height="80">

  <h3 align="center">Template for Spring+Docker+Postgresql+K8S</h3>

  <p align="center">
    Easy-To-Use dockerized SpringBootApp
    <br/>
    <br/>
    <a href="https://github.com/DeTiveNC/SpringTemplateDockerized/issues">Report Bug</a>
    .
    <a href="https://github.com/DeTiveNC/SpringTemplateDockerized/issues">Request Feature</a>
  </p>
</p>

![Contributors](https://img.shields.io/github/contributors/DeTiveNC/SpringTemplateDockerized?color=dark-green) ![Forks](https://img.shields.io/github/forks/DeTiveNC/SpringTemplateDockerized?style=social) ![Stargazers](https://img.shields.io/github/stars/DeTiveNC/SpringTemplateDockerized?style=social) ![Issues](https://img.shields.io/github/issues/DeTiveNC/SpringTemplateDockerized) ![License](https://img.shields.io/github/license/DeTiveNC/SpringTemplateDockerized) 

## Table Of Contents

* [About the Project](#about-the-project)
* [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
* [Deploy](#deploy)
* [K8S](#k8s)
* [License](#license)
* [Authors](#authors)


## About The Project

For people that always have problems building correctly a Docker for Spring Boot Application, I have your solution.

This project is an easy-to-use spring boot app for test or start a web service from scratch for the last version of spring boot.

## Built With

Start of the project is with the help of spring initializers peaking dependecies for Postgres+Web+JPA

* [Spring Initializer](https://start.spring.io/)

## Getting Started

Steps to configure and run the project locally

### Prerequisites

You need to have: 
- Docker

### Installation

1. Clone the repo
```
    git clone https://github.com/detivenc/SpringTemplateDockerized.git
```
2. Build the project
    - SPRING:
       - On terminal at the project folder
 ```./mvnw clean package -DskipTests```
3. Run the script of docker-compose
            ```docker-compose up```
4. PgAdmin
When you run it access with the creadentials:
- admin@admin.com (you can change it)
- admin (you can change it)
When you need to add the server is:
- General: 
  - Name: whatever
- Connection:
  - Host: java_db
  - Username: postgres or change it
  - Password: postgres or change it

## Usage

Use this project to have a fast creation of spring project, test it and deploy it.
For testing, you can use Swagger and see the changes of the database with PgAdmin

## Deploy

For deploying the docker compose you only need to do 
```
docker compose build
docker compose push
```

*NOTE* Remember that you need to have the jar file compiled on the spring boot project and on the docker-compose.yml need to be attachend this: 
```
image: <your-username>/java-app:<tag-name>
```

## K8S

<p align="center">
  <img src="https://github.com/DeTiveNC/SpringTemplateDockerized/assets/116792124/7ed10241-b326-4a43-a3b9-fb4551cbed6c" alt="Logo" width="80" height="80">
</p>

So for this part you can do it manually using the [Kubernetes guide](https://kubernetes.io/docs/tutorials/). In my repository i created it with [Kompose](https://kubernetes.io/docs/tasks/configure-pod-container/translate-compose-kubernetes/). With Kompose is easier to create the files in seconds and deploying locally to try it out the environment. Using ``` kompose convert  ``` You can convert the files in what you need to use for deploy and service an kubernetes cluster.

*NOTE* Because I run all locally I use [Minikube](https://minikube.sigs.k8s.io/docs/) that is the a greatest kubernetes cluster for running it locally and to explore kubernetes



## License

Distributed under the MIT License. See [LICENSE](https://github.com/DeTiveNC/SpringTemplateDockerized/blob/main/LICENSE.md) for more information.

## Authors

* **Nicolas Cao** - *Comp Eng Student* - [Nicolas Cao](https://github.com/detivenc) - *Built SpringProject*
