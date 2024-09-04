package com.travel.user;

import com.travel.customExceptions.CustomerNotFoundException;
import com.travel.customExceptions.DestinationNotFoundException;
import com.travel.customExceptions.InvalidBookingDateException;
import com.travel.dao.BookingDAO;
import com.travel.dao.CustomerDAO;
import com.travel.dao.DestinationDAO;
import com.travel.utils.Booking;
import com.travel.utils.Customer;
import com.travel.utils.Destination;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DestinationDAO destinationDAO = new DestinationDAO();
    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final BookingDAO bookingDAO = new BookingDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Travel Booking Management System ===");
            System.out.println("1. Destination Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Booking Management");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageDestinations();
                    break;
                case 2:
                    manageCustomers();
                    break;
                case 3:
                    manageBookings();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageDestinations() {
        while (true) {
            System.out.println("\n--- Destination Management ---");
            System.out.println("1. Add a new destination");
            System.out.println("2. View destination details");
            System.out.println("3. Update destination information");
            System.out.println("4. Delete a destination");
            System.out.println("5. Back to main menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addDestination();
                    break;
                case 2:
                    viewDestination();
                    break;
                case 3:
                    updateDestination();
                    break;
                case 4:
                    deleteDestination();
                    break;
                case 5:
                    return;
                default:
                    System.out.print("Invalid choice. Please try again.");

            }
        }
    }

    private static void manageCustomers() {
        while (true) {
            System.out.println("\n--- Customer Management ---");
            System.out.println("1. Add a new customer");
            System.out.println("2. View customer details");
            System.out.println("3. Update customer information");
            System.out.println("4. Delete a customer");
            System.out.println("5. Back to main menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    return;
                default:
                    throw new CustomerNotFoundException("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageBookings() {
        while (true) {
            System.out.println("\n--- Booking Management ---");
            System.out.println("1. Create a new booking");
            System.out.println("2. View booking details");
            System.out.println("3. Update booking information");
            System.out.println("4. Cancel a booking");
            System.out.println("5. Back to main menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createBooking();
                    break;
                case 2:
                    viewBooking();
                    break;
                case 3:
                    updateBooking();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    return;
                default:
                    throw new InvalidBookingDateException("Invalid choice. Please try again.");
            }
        }
    }

    private static void addDestination() {
        System.out.println("\n--- Add New Destination ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Location: ");
        String location = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Price per day: ");
        double pricePerDay = scanner.nextDouble();
        scanner.nextLine();

        Destination Destination = new Destination();
        Destination.setName(name);
        Destination.setLocation(location);
        Destination.setDescription(description);
        Destination.setPricePerDay(pricePerDay);


        destinationDAO.addDestination(Destination);
        System.out.println("Destination added successfully.");
    }

    private static void viewDestination() {
        System.out.print("\nEnter Destination ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Destination destination = destinationDAO.getDestination(id);
        if (destination != null) {
            System.out.println("ID: " + destination.getDestinationId());
            System.out.println("Name: " + destination.getName());
            System.out.println("Location: " + destination.getLocation());
            System.out.println("Description: " + destination.getDescription());
            System.out.println("Price per day: " + destination.getPricePerDay());
        } else {
            System.out.println("Destination not found.");
        }
    }

    private static void updateDestination() {
        System.out.print("\nEnter Destination ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Destination destination = destinationDAO.getDestination(id);

        // If the destination is not found, throw the custom exception
        if (destination == null) {
            throw new DestinationNotFoundException("Destination with ID " + id + " not found.");
        }

        System.out.print("New Name (leave blank to keep current): ");
        String name = scanner.nextLine();
        System.out.print("New Location (leave blank to keep current): ");
        String location = scanner.nextLine();
        System.out.print("New Description (leave blank to keep current): ");
        String description = scanner.nextLine();
        System.out.print("New Price per day (leave blank to keep current): ");
        String priceStr = scanner.nextLine();

        if (!name.isEmpty()) destination.setName(name);
        if (!location.isEmpty()) destination.setLocation(location);
        if (!description.isEmpty()) destination.setDescription(description);
        if (!priceStr.isEmpty()) destination.setPricePerDay(Double.parseDouble(priceStr));

        destinationDAO.updateDestination(destination);
        System.out.println("Destination updated successfully.");
    }


    private static void deleteDestination() {
        System.out.print("\nEnter Destination ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        destinationDAO.deleteDestination(id);
        System.out.println("Destination deleted successfully.");
    }

    private static void addCustomer() {
        System.out.println("\n--- Add New Customer ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);

        customerDAO.addCustomer(customer);
        System.out.println("Customer added successfully.");
    }

    private static void viewCustomer() {
        System.out.print("\nEnter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Customer customer = customerDAO.getCustomer(id);
        if (customer != null) {
            System.out.println("ID: " + customer.getCustomerId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone Number: " + customer.getPhoneNumber());
            System.out.println("Address: " + customer.getAddress());
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void updateCustomer() {
        System.out.print("\nEnter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Customer customer = customerDAO.getCustomer(id);
        if (customer != null) {
            System.out.print("New Name (leave blank to keep current): ");
            String name = scanner.nextLine();
            System.out.print("New Email (leave blank to keep current): ");
            String email = scanner.nextLine();
            System.out.print("New Phone Number (leave blank to keep current): ");
            String phoneNumber = scanner.nextLine();
            System.out.print("New Address (leave blank to keep current): ");
            String address = scanner.nextLine();

            if (!name.isEmpty()) customer.setName(name);
            if (!email.isEmpty()) customer.setEmail(email);
            if (!phoneNumber.isEmpty()) customer.setPhoneNumber(phoneNumber);
            if (!address.isEmpty()) customer.setAddress(address);

            customerDAO.updateCustomer(customer);
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void deleteCustomer() {
        System.out.print("\nEnter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        customerDAO.deleteCustomer(id);
        System.out.println("Customer deleted successfully.");
    }

    private static void createBooking() {
        System.out.println("\n--- Create New Booking ---");
        System.out.print("Destination ID: ");
        int destinationId = scanner.nextInt();
        System.out.print("Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Booking Date (YYYY-MM-DD): ");
        String bookingDateStr = scanner.nextLine();
        System.out.print("Start Date (YYYY-MM-DD): ");
        String startDateStr = scanner.nextLine();
        System.out.print("End Date (YYYY-MM-DD): ");
        String endDateStr = scanner.nextLine();

        Date bookingDate = Date.valueOf(bookingDateStr);
        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);

        Booking booking = new Booking();
        booking.setDestinationId(destinationId);
        booking.setCustomerId(customerId);
        booking.setBookingDate(bookingDate);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);


        Destination destination = destinationDAO.getDestination(destinationId);
        if (destination != null) {
            long days = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
            double totalCost = days * destination.getPricePerDay();
            booking.setTotalCost(totalCost);
            booking.setStatus("confirmed");

            bookingDAO.createBooking(booking);
            System.out.println("Booking created successfully.");
        } else {
            System.out.println("Destination not found.");
        }
    }

    private static void viewBooking() {
        System.out.print("\nEnter Booking ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Booking booking = bookingDAO.getBooking(id);
        if (booking != null) {
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Destination ID: " + booking.getDestinationId());
            System.out.println("Customer ID: " + booking.getCustomerId());
            System.out.println("Booking Date: " + booking.getBookingDate());
            System.out.println("Start Date: " + booking.getStartDate());
            System.out.println("End Date: " + booking.getEndDate());
            System.out.println("Total Cost: " + booking.getTotalCost());
            System.out.println("Status: " + booking.getStatus());
        } else {
            System.out.println("Booking not found.");
        }
    }

    private static void updateBooking() {
        System.out.print("\nEnter Booking ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Booking booking = bookingDAO.getBooking(id);
        if (booking != null) {
            System.out.print("New Start Date (leave blank to keep current): ");
            String startDateStr = scanner.nextLine();
            System.out.print("New End Date (leave blank to keep current): ");
            String endDateStr = scanner.nextLine();
            System.out.print("New Status (confirmed/cancelled, leave blank to keep current): ");
            String status = scanner.nextLine();

            if (!startDateStr.isEmpty()) booking.setStartDate(Date.valueOf(startDateStr));
            if (!endDateStr.isEmpty()) booking.setEndDate(Date.valueOf(endDateStr));
            if (!status.isEmpty()) booking.setStatus(status);


            if (!startDateStr.isEmpty() || !endDateStr.isEmpty()) {
                Destination destination = destinationDAO.getDestination(booking.getDestinationId());
                if (destination != null) {
                    long days = (booking.getEndDate().getTime() - booking.getStartDate().getTime()) / (1000 * 60 * 60 * 24);
                    double totalCost = days * destination.getPricePerDay();
                    booking.setTotalCost(totalCost);
                }
            }

            bookingDAO.updateBooking(booking);
            System.out.println("Booking updated successfully.");
        } else {
            System.out.println("Booking not found.");
        }
    }

    private static void cancelBooking() {
        System.out.print("\nEnter Booking ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        bookingDAO.cancelBooking(id);
        System.out.println("Booking cancelled successfully.");
    }
}
