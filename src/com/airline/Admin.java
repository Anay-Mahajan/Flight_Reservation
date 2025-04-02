package com.airline;
import java.util.Scanner;
import com.App;
import com.Customer;
public class Admin {
    public String Admin_ID;
    public String password;
    public Admin(String admin_ID, String password) {
        Admin_ID = admin_ID;
        this.password = password;
    }
    private void Add_New_Flight(){
        @SuppressWarnings("resource")
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter Flight No: ");
        int flightNo = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();                
        System.out.print("Enter Cost: ");
        int cost = scanner.nextInt();
        System.out.print("Enter Number of Seats: ");
        int seats = scanner.nextInt();
        App.Flight_List.add(new Flight(flightNo, source, destination, cost, seats));
        App.updateFlightDataToFile();       
    }
    public void Admin_Menu(){
        int choice;
        @SuppressWarnings("resource")
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("\n===== Flight Reservation System =====");
            System.out.println("1. Add Flight ");
            System.out.println("2. Log Out");
            System.out.println("3.Customer Details");
            System.out.println("4.Flight Details");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Add_New_Flight();
                    break;
                case 2:
                    System.out.println("Logging out... Thank you for using our system!");
                    break;
                case 3:
                    for(Customer C : App.User_List){
                        C.Customer_details();
                    }
                case 4:
                    for(Flight f:App.Flight_List){
                        f.Display();
                    }
                default:
                    System.out.println("Invalid choice! Please enter 1 or 2.");
            }

        } while (choice != 2);       
    }
}
