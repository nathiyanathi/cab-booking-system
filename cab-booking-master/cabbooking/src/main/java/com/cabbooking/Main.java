package com.cabbooking;

import com.cabbooking.dao.*;
import com.cabbooking.models.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    static UserDAO userDAO = new UserDAO();
    static CabDAO cabDAO = new CabDAO();
    static BookingDAO bookingDAO = new BookingDAO();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== CAB BOOKING SYSTEM =====");
            System.out.println("1. Add User");
            System.out.println("2. List Users");
            System.out.println("3. Add Cab");
            System.out.println("4. List Cabs");
            System.out.println("5. Book Cab");
            System.out.println("6. View Bookings");
            System.out.println("7. Delete Booking");
            System.out.println("8. Exit");

            System.out.print("Select Option: ");
            int op;
            try {
                op = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception ex) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (op) {
            case 1:
                addUser();
                break;

            case 2:
                listUsers();
                break;

            case 3:
                addCab();
                break;

            case 4:
                listCabs();
                break;

            case 5:
                bookCab();
                break;

            case 6:
                viewBookings();
                break;

            case 7:
                deleteBooking();
                break;

            case 8:
                System.out.println("Exiting...");
                com.cabbooking.dao.HibernateUtil.shutdown();
                return;

            default:
                System.out.println("Invalid option!");
        }

        }
    }

    static void addUser() {
        System.out.print("Enter name: ");
        String n = sc.nextLine();
        System.out.print("Enter email: ");
        String e = sc.nextLine();
        System.out.print("Enter phone: ");
        String p = sc.nextLine();

        User u = new User(n, e, p);
        int id = userDAO.save(u);
        System.out.println("User created with ID: " + id);
    }

    static void listUsers() {
        List<User> users = userDAO.listAll();
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        users.forEach(u -> System.out.println("ID: " + u.getId() + ", Name: " + u.getName() + ", Email: " + u.getEmail()));
    }

    static void addCab() {
        System.out.print("Number Plate: ");
        String np = sc.nextLine();
        System.out.print("Driver Name: ");
        String dn = sc.nextLine();
        System.out.print("Model: ");
        String m = sc.nextLine();

        Cab c = new Cab(np, dn, m);
        int id = cabDAO.save(c);
        System.out.println("Cab added ID: " + id);
    }

    static void listCabs() {
        List<Cab> cabs = cabDAO.listAll();
        if (cabs.isEmpty()) {
            System.out.println("No cabs found.");
            return;
        }
        cabs.forEach(c -> System.out.println("ID: " + c.getId() + ", Plate: " + c.getNumber_plate() + ", Driver: " + c.getDriver_name() + ", Available: " + c.isAvailable()));
    }

    static void bookCab() {
        System.out.print("Enter User ID: ");
        int uid;
        try { uid = Integer.parseInt(sc.nextLine().trim()); } catch (Exception ex) { System.out.println("Invalid ID"); return; }

        System.out.print("Enter Cab ID: ");
        int cid;
        try { cid = Integer.parseInt(sc.nextLine().trim()); } catch (Exception ex) { System.out.println("Invalid ID"); return; }

        System.out.print("Pickup: ");
        String p = sc.nextLine();
        System.out.print("Dropoff: ");
        String d = sc.nextLine();

        User u = userDAO.get(uid);
        Cab c = cabDAO.get(cid);

        if (u == null) { System.out.println("User not found."); return; }
        if (c == null) { System.out.println("Cab not found."); return; }
        if (!c.isAvailable()) { System.out.println("Cab is not available."); return; }

        Booking b = new Booking(u, c, p, d);
        int bid = bookingDAO.save(b);

        c.setAvailable(false);
        cabDAO.update(c);

        System.out.println("Booking successful! ID: " + bid);
    }

    static void viewBookings() {
        List<Booking> list = bookingDAO.listAll();
        if (list.isEmpty()) { System.out.println("No bookings."); return; }
        list.forEach(b -> System.out.println("Booking ID: " + b.getId() + ", User: " + b.getUser().getName() + ", Cab: " + b.getCab().getNumber_plate() + ", Status: " + b.getStatus()));
    }

    static void deleteBooking() {
        System.out.print("Enter Booking ID: ");
        int id;
        try { id = Integer.parseInt(sc.nextLine().trim()); } catch (Exception ex) { System.out.println("Invalid ID"); return; }

        Booking b = bookingDAO.get(id);
        if (b == null) { System.out.println("Booking not found."); return; }

        // mark cab available again
        Cab c = b.getCab();
        bookingDAO.delete(id);
        if (c != null) {
            c.setAvailable(true);
            cabDAO.update(c);
        }
        System.out.println("Booking deleted.");
    }
}
