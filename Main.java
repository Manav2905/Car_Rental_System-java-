import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getBasePricePerDay() {
        return basePricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }

    public void displayInfo() {
        System.out.println("Car ID: " + carId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.printf("Price per day: $%.2f%n", basePricePerDay);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println();
    }
}

class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
            System.out.println("Car returned successfully.");
        } else {
            System.out.println("Car was not rented.");
        }
    }

    public void displayRentedCars() {
        System.out.println("\nCurrently rented cars:");
        for (Rental rental : rentals) {
            rental.getCar().displayInfo();
        }
    }

    public void advancedSearch() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("== Advanced Search ==\n");
            System.out.print("Enter search filter (brand/model/price): ");
            String filter = scanner.nextLine().toLowerCase();

            System.out.print("Enter search value (e.g., 'Toyota', 'Accord', or '50-200'): ");
            String value = scanner.nextLine().toLowerCase();

            boolean found = false;

            for (Car car : cars) {
                switch (filter) {
                    case "brand":
                        if (car.getBrand().toLowerCase().contains(value)) {
                            car.displayInfo();
                            found = true;
                        }
                        break;
                    case "model":
                        if (car.getModel().toLowerCase().contains(value)) {
                            car.displayInfo();
                            found = true;
                        }
                        break;
                    case "price":
                        try {
                            String[] priceRange = value.split("-");
                            double lowerBound = Double.parseDouble(priceRange[0]);
                            double upperBound = Double.parseDouble(priceRange[1]);
                            if (car.getBasePricePerDay() >= lowerBound && car.getBasePricePerDay() <= upperBound) {
                                car.displayInfo();
                                found = true;
                            }
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid price range. Please enter a valid range like '50-100'.");
                        }
                        break;
                    default:
                        System.out.println("Invalid filter. Please choose 'brand', 'model', or 'price'.");
                }
            }

            if (!found) {
                System.out.println("No cars match your search criteria.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during search. Please try again.");
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Advanced Search");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    rentCarMenu();
                    break;
                case 2:
                    returnCarMenu();
                    break;
                case 3:
                    advancedSearch();
                    break;
                case 4:
                    System.out.println("Thank you for using the Car Rental System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void rentCarMenu() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("\n== Rent a Car ==\n");
            System.out.print("Enter your name: ");
            String customerName = scanner.nextLine();

            System.out.println("\nAvailable Cars:");
            for (Car car : cars) {
                if (car.isAvailable()) {
                    car.displayInfo();
                }
            }

            System.out.print("\nEnter the car ID you want to rent: ");
            String carId = scanner.nextLine();

            System.out.print("Enter the number of days for rental: ");
            int rentalDays;
            try {
                rentalDays = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number of days. Please enter a valid number.");
                return;
            }

            Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
            addCustomer(newCustomer);

            Car selectedCar = null;
            for (Car car : cars) {
                if (car.getCarId().equals(carId) && car.isAvailable()) {
                    selectedCar = car;
                    break;
                }
            }

            if (selectedCar != null) {
                double totalPrice = selectedCar.getBasePricePerDay() * rentalDays;
                System.out.println("\n== Rental Summary == ");
                System.out.printf("Car: %s %s%n", selectedCar.getBrand(), selectedCar.getModel());
                System.out.printf("Rental Days: %d%n", rentalDays);
                System.out.printf("Total Price: $%.2f%n", totalPrice);
                rentCar(selectedCar, newCustomer, rentalDays);
                System.out.println("Car rented successfully!\n");
            } else {
                System.out.println("Invalid car ID or car is not available.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the rental process. Please try again.");
        }
    }

    private void returnCarMenu() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("\n== Return a Car ==\n");
            displayRentedCars(); // Show all rented cars
            if (rentals.isEmpty()) {
                System.out.println("No cars are currently rented.");
                return;
            }
            
            System.out.print("Enter the car ID you want to return: ");
            String carId = scanner.nextLine();
            Car carToReturn = null;

            // Find the car to return
            for (Rental rental : rentals) {
                if (rental.getCar().getCarId().equals(carId)) {
                    carToReturn = rental.getCar();
                    break;
                }
            }

            if (carToReturn != null) {
                returnCar(carToReturn);
            } else {
                System.out.println("Invalid car ID or car is not rented.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the return process. Please try again.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        // Adding some car instances
        rentalSystem.addCar(new Car("C001", "Toyota", "Camry", 60.0));
        rentalSystem.addCar(new Car("C002", "Honda", "Accord", 70.0));
        rentalSystem.addCar(new Car("C003", "Mahindra", "Thar", 150.0));
        rentalSystem.addCar(new Car("C004", "Ford", "Mustang", 200.0));
        rentalSystem.addCar(new Car("C005", "Chevrolet", "Impala", 90.0));
        rentalSystem.addCar(new Car("C006", "Nissan", "Altima", 80.0));
        rentalSystem.addCar(new Car("C007", "Hyundai", "Elantra", 65.0));
        rentalSystem.addCar(new Car("C008", "BMW", "5 Series", 120.0));
        rentalSystem.addCar(new Car("C009", "Audi", "A6", 110.0));
        rentalSystem.addCar(new Car("C010", "Volkswagen", "Passat", 95.0));

        rentalSystem.menu();
    }
}