# Customer Management Application

This Java application is designed to manage customer data through various actions, such as processing a CSV file and retrieving customer details by reference.

## Prerequisites

- Java Development Kit (JDK) 21
- Git
- git clone https://github.com/mununuribonjesi/ESG-REST-ENDPOINT to run rest-endpoint to save and retrieve customer details

## Usage

Upon running the application, you will be prompted with a menu to select an action:

1. **Process CSV file:** Reads a CSV file (`customer.csv`) and sends the data to a rest api.
2. **Get Customer Details by Ref:** Retrieves customer details based on a provided reference number.
3. **Exit (Q):** Exits the application.

Follow the on-screen instructions to interact with the application.

# Application Overview

The application follows a modular and organised approach, implementing the principles of separation of concerns and code re-usability. Here's an overview of the approach used in each part of the application:

## `CustomerService` Class

### Responsibility:
- Handles the parsing of CSV data into a list of `Customer` objects.
- Uses a `Parser` to parse the CSV file and a mapping function to convert parsed values into `Customer` objects.

### Approach:
- Encapsulates CSV parsing logic within the `CustomerService` class.
- Utilises a `Parser` class to abstract away the low-level parsing details.
- Uses a mapping function (`mapParserResultsToCustomer`) to convert parsed CSV values into `Customer` objects.

## `PostalService` Class

### Responsibility:
- Coordinates with `CustomerService` to process and send customer data.
- Sends HTTP requests to a specified API for saving CSV data and retrieving customer details.

### Approach:
- Relies on the `CustomerService` for parsing CSV data.
- Uses `HttpClient` for sending HTTP requests.
- Handles both sending a POST request to save customer data and sending a GET request to retrieve customer details.
- Utilises helper methods for building and sending HTTP requests, improving code readability and maintainability.
- Handles exceptions and converts them into a `RuntimeException` for simplicity.

## `Parser` Class

### Responsibility:
- General-purpose CSV parsing utility.

### Approach:
- Takes a `BufferedReader` and a mapping function as parameters.
- Reads CSV lines from the `BufferedReader` and applies the mapping function to create objects.
- Provides a generic method (`parseCsv`) allowing reuse for different types of objects.

### Key Principles Demonstrated:

- **Separation of Concerns:**
    - Each class has a clear responsibility, focusing on a specific aspect of the application.

- **Code Re-usability:**
    - The `Parser` class is a reusable utility for parsing CSV data with different mapping functions.

- **Abstraction:**
    - The use of a mapping function allows abstracting away the conversion of CSV values to specific objects.

- **Exception Handling:**
    - Appropriate exception handling is implemented in the `PostalService` class.

- **HTTP Request Handling:**
    - The `PostalService` class handles the creation and execution of HTTP requests.

This approach enhances code organisation, readability, and maintainability while promoting the reuse of components. 
It also adheres to the SOLID principles, making the code more modular and extensible.
