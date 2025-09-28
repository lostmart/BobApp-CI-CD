<!-- Live GitHub Actions Workflow Badges -->

[![Build Status](https://github.com/lostmart/BobApp-CI-CD/actions/workflows/docker-build-deploy.yml/badge.svg)](https://github.com/lostmart/BobApp-CI-CD/actions/workflows/build.yml)
[![Tests](https://github.com/lostmart/Gerez-un-projet-collaboratif-en-int-grant-une-demarche-CI-CD/actions/workflows/tests.yml/badge.svg)](https://github.com/lostmart/Gerez-un-projet-collaboratif-en-int-grant-une-demarche-CI-CD/actions/workflows/tests.yml)

[![Backend Coverage](https://img.shields.io/endpoint?url=https://gist.githubusercontent.com/lostmart/BobApp-CI-CD/raw/jacoco.json)](https://lostmart.github.io/Gerez-un-projet-collaboratif-en-int-grant-une-demarche-CI-CD/)
[![Frontend Coverage](https://img.shields.io/codecov/c/github/lostmart/Gerez-un-projet-collaboratif-en-int-grant-une-demarche-CI-CD)](https://codecov.io/gh/lostmart/Gerez-un-projet-collaboratif-en-int-grant-une-demarche-CI-CD)

# BobApp

Project: BobApp - L'application de joke par excellence !
Course: Gérez un projet collaboratif en intégrant une démarche CI/CD

This repository contains the complete CI/CD implementation for BobApp with the following GitHub Actions workflows:

1. CI/CD WORKFLOW (docker-build-deploy.yml)

   - Automated Docker build and deployment
   - Multi-architecture support
   - Security scanning with Trivy
   - Deployment to Docker Hub

2. TESTS WORKFLOW (tests.yml)

   - Angular frontend unit tests with Karma
   - Spring Boot backend unit tests
   - Coverage report generation
   - Test result publishing

3. QUALITY WORKFLOW (build.yml)
   - SonarQube code quality analysis
   - Unified frontend and backend analysis
   - Coverage integration
   - Quality gates enforcement

All workflows are configured with proper dependencies, caching, and error handling to ensure reliable CI/CD operations for BobApp.

Clone project:

> git clone https://github.com/lostmart/BobApp-CI-CD.git

## Front-end

Go inside folder the front folder:

> cd front

Install dependencies:

> npm install

Launch Front-end:

> npm run start;

### Docker

Build the container:

> docker build -t bobapp-front .

Start the container:

> docker run -p 8080:8080 --name bobapp-front -d bobapp-front

## Back-end

Go inside folder the back folder:

> cd back

Install dependencies:

> mvn clean install

Launch Back-end:

> mvn spring-boot:run

Launch the tests:

> mvn clean install

### Docker

Build the container:

> docker build -t bobapp-back .

Start the container:

> docker run -p 8080:8080 --name bobapp-back -d bobapp-back
