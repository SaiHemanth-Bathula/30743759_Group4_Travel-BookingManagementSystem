# Travel Booking Management System

## Overview

The Travel Booking Management System is a console-based application developed in Core Java, using MySQL and JDBC for database interaction. It allows users to manage travel destinations, customers, and bookings efficiently.

## Features

### 1. Destination Management
- **Add a new destination**
- **View destination details**
- **Update destination information**
- **Delete a destination**

### 2. Customer Management
- **Add a new customer**
- **View customer details**
- **Update customer information**
- **Delete a customer**

### 3. Booking Management
- **Create a new booking**
- **View booking details**
- **Update booking information**
- **Cancel a booking**

## Database Schema

### 1. Destination Table
- `destination_id` (Primary Key)
- `name`
- `location`
- `description`
- `price_per_day`

### 2. Customer Table
- `customer_id` (Primary Key)
- `name`
- `email`
- `phone_number`
- `address`

### 3. Booking Table
- `booking_id` (Primary Key)
- `destination_id` (Foreign Key references `Destination Table`)
- `customer_id` (Foreign Key references `Customer Table`)
- `booking_date`
- `start_date`
- `end_date`
- `total_cost`
- `status` (confirmed/cancelled)

## Setup and Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL JDBC Driver (`mysql-connector-java-8.0.xx.jar`)

### Step 1: Set Up the Database
1. **Create the Database**:
   ```sql
   CREATE DATABASE travel_booking_db;
   USE travel_booking_db;
