# Car Rental System

A simple **Car Rental System** built using Java. This project allows customers to rent cars, return them, and perform advanced searches for available cars based on brand, model, or price range. It is designed to simulate a basic car rental management system with features for handling rentals and returns.

## Features

- **Add Cars**: The system includes pre-defined cars with details like brand, model, and price per day.
- **Rent Cars**: Customers can rent a car for a specified number of days.
- **Return Cars**: Cars can be returned, and availability is updated accordingly.
- **Advanced Search**: Customers can search for available cars by brand, model, or within a specified price range.
- **Rental Management**: The system tracks rented cars, and their rental periods, and allows for returning them easily.

## How It Works

1. **Menu**:
    - The system presents a menu with options to rent a car, return a car, perform an advanced search, or exit.

2. **Renting a Car**:
    - Customers can view all available cars and choose one by entering the corresponding car ID.
    - The system calculates the total price based on the number of rental days.
    - The car’s availability status is updated once rented.

3. **Returning a Car**:
    - Customers can return a rented car by providing the car ID.
    - Once returned, the car becomes available for rent again.

4. **Advanced Search**:
    - Users can search for cars based on brand, model, or price range.
    - The system displays matching cars that meet the search criteria.

## Classes and Responsibilities

- **Car**: Represents the car, including details like brand, model, price per day, and availability status.
- **Customer**: Represents a customer with a unique ID and name.
- **Rental**: Manages the relationship between a car and a customer, tracking the car rental period.
- **CarRentalSystem**: The core class responsible for managing cars, customers, rentals, and interactions such as renting and returning cars.

## Prerequisites

- **Java JDK** (version 8 or above)
- **IDE**: Use any Java-supported IDE (IntelliJ IDEA, Eclipse, NetBeans) or run it using command-line tools.

## How to Run

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/car-rental-system.git
    cd car-rental-system
    ```

2. Compile and run the `Main` class:
    - Using an IDE:
      - Open the project in your IDE, locate the `Main` class, and run it.
    - Using the command line:
      ```bash
      javac Main.java
      java Main
      ```

3. Follow the on-screen menu to interact with the system.

## Future Improvements

- **Customer Feedback & Ratings**: Collect and display customer ratings for rented cars.
- **User Authentication**: Add login functionality for both customers and administrators.

