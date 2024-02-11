# Tambola Game API

## Overview
This project implements a RESTful API for managing a Tambola (also known as Bingo) game. It includes functionality to add Tambola tickets, retrieve tickets, and interact with a SQL database to store and fetch ticket data.

## Components
The project consists of the following components:

### 1. Controller
- **TambolaController**: Handles HTTP requests and routes them to appropriate methods in the service layer. It defines endpoints for adding Tambola tickets and retrieving Tambola tickets.

### 2. Service
- **TambolaService**: Provides business logic for adding and retrieving Tambola tickets. It interacts with the repository to perform CRUD operations on Tambola ticket entities.

### 3. Repository
- **TambolaRepository**: Interface for CRUD operations on Tambola ticket entities. It extends Spring Data's repository interface and provides methods for interacting with the underlying SQL database.

### 4. SQL Database
- The API interacts with a SQL database to persist Tambola ticket data. The database schema includes tables for storing Tambola tickets.

## Dependencies
- Java
- Spring Framework (including Spring Data)
- SQL Database (e.g., MySQL, PostgreSQL)
- Logging API (java.util.logging)

## Functionality
The API offers the following functionality:

- Adding Tambola tickets: Allows users to add Tambola tickets to the system.
- Retrieving Tambola tickets: Provides endpoints to fetch Tambola tickets from the database, with options for pagination and sorting.

## Usage
Users can interact with the API through HTTP requests to the defined endpoints. Detailed usage instructions for each endpoint can be found in the controller class.

## Logging
The application utilizes Java logging (java.util.logging) to record errors and exceptions that occur during the execution of API operations.

## Author
- [Raj Thakur]

