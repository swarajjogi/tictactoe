# Tic-Tac-Toe Spring Boot Application with CI/CD Pipeline

## Project Overview

This project is a web-based Tic-Tac-Toe application built using Spring Boot and deployed on AWS EC2 with a fully automated CI/CD pipeline using GitHub Actions.

The main goal of this project was not only to build the application, but also to understand real-world deployment workflows, Linux server management, cloud deployment, and CI/CD automation.

---

# Tech Stack

## Backend

* Java 17
* Spring Boot 3
* Maven

## Cloud & DevOps

* AWS EC2
* GitHub Actions
* Linux (Ubuntu Server)
* SSH
* SCP

---

# Features

* Web-based Tic-Tac-Toe game
* Spring Boot application packaging using Maven
* Deployment on AWS EC2
* Automated CI/CD pipeline
* Automatic application restart after deployment
* Linux background process management using `nohup`
* Secure deployment using SSH keys and GitHub Secrets

---

# CI/CD Workflow

The CI/CD pipeline was implemented using GitHub Actions.

## Workflow Process

```text
Developer pushes code to GitHub
        ↓
GitHub Actions workflow triggers
        ↓
Spring Boot project builds automatically
        ↓
JAR file is generated using Maven
        ↓
JAR file is securely copied to EC2 using SCP
        ↓
Existing application process is stopped
        ↓
Latest application version is deployed automatically
```

---

# AWS Deployment Steps

## 1. Created AWS EC2 Instance

* Ubuntu Server
* Configured Security Groups
* Opened ports:

  * 22 (SSH)
  * 8080 (Application)

## 2. Connected to EC2 Using SSH

```bash
ssh -i tictactoe-key.pem ubuntu@<EC2-IP>
```

## 3. Packaged Spring Boot Application

```bash
mvn clean package
```

## 4. Transferred JAR to EC2

```bash
scp -i tictactoe-key.pem target/tictactoe-0.0.1-SNAPSHOT.jar ubuntu@<EC2-IP>:/home/ubuntu/app
```

## 5. Ran Application on EC2

```bash
nohup java -jar target/tictactoe-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

---

# GitHub Actions Deployment Workflow

The deployment workflow:

* Builds the application
* Transfers the JAR to EC2
* Restarts the application automatically

Workflow file location:

```text
.github/workflows/deploy.yml
```

---

# Challenges Faced During Deployment

During implementation, several deployment and Linux-related issues were encountered and resolved:

* SSH key permission issues on Windows
* EC2 Security Group configuration
* SCP deployment path issues
* Linux background process handling
* Port 8080 conflicts
* GitHub Actions SSH workflow debugging
* Application restart automation

These issues helped in understanding practical deployment debugging and CI/CD troubleshooting.

---

# Key Learnings

Through this project, I learned:

* Spring Boot deployment process
* Linux server basics
* AWS EC2 management
* SSH and SCP usage
* GitHub Actions workflow creation
* CI/CD pipeline implementation
* Process management in Linux
* Deployment troubleshooting and debugging

---

# Future Improvements

* Dockerize the application
* Add Nginx reverse proxy
* Configure HTTPS
* Add automated testing
* Infrastructure provisioning using Terraform
* Monitoring and logging setup

---

# Author

Swaraj Jogi
