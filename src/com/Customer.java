package com;
import java.util.HashMap;
import java.util.Iterator;
import com.airline.Flight;
public class Customer {
    public String user_id;
    public String password;
    public HashMap<Integer,Integer> Booking_Details =new HashMap<Integer,Integer>();
    public Customer(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }
    public void Customer_details(boolean flag) {
        System.out.println("+----------------+----------------------+");
        System.out.println("| User ID        | Password             |");
        System.out.println("+----------------+----------------------+");
        System.out.printf("| %-14s | %-20s |\n", user_id, password);
        System.out.println("+----------------+----------------------+");
        if (!Booking_Details.isEmpty()) {
            Booking_Details(); 
        } else {
            System.out.println("No tickets booked yet!");
        }
    }    
    private Flight  Confirm_Ticket(Flight F,int N) throws Insufficient_Seats{
        if(F.Seats_Available<N){
            throw new Insufficient_Seats("Insufficient Seats");
        }
        else{
            F.Seats_Available-=N;
            System.out.println("Ticket Booked Succesfully !!");
        }
        return F;
    }
    private void addBookingDetail(int flightNumber, int seats) {
        if(Booking_Details.containsKey(flightNumber)){
            Booking_Details.put(flightNumber,Booking_Details.get(flightNumber)+seats);
        }
        else{
            Booking_Details.put(flightNumber, seats);
        }
        App.updateUserDataToFile();
    }
    private void Booking_Details() {
        if (Booking_Details.isEmpty()) {
            System.out.println("No tickets booked!");
            return;
        }
        System.out.println("+-------------+-----------------+");
        System.out.println("| Flight No.  | No. of Tickets  |");
        System.out.println("+-------------+-----------------+");
    
        for (int flightNumber : Booking_Details.keySet()) {
            System.out.printf("| %-11d | %-15d |\n", flightNumber, Booking_Details.get(flightNumber));
        }
        System.out.println("+-------------+-----------------+");
    }
    
    private void Book_Ticket(){
        System.out.print("Enter Flight Number: ");
        int flightNumber = App.scanner.nextInt();
        System.out.print("Enter Number of Seats: ");
        int numberOfSeats = App.scanner.nextInt();
        boolean flag=false;
        Iterator<Flight> it = App.Flight_List.iterator();
        while (it.hasNext()) {
            Flight F = it.next();
            if (F.Flight_No == flightNumber){
                it.remove();
                flag = true;
                try {
                    App.Flight_List.add(Confirm_Ticket(F, numberOfSeats)); 
                    addBookingDetail(flightNumber,numberOfSeats);
                    
                } 
                catch (Insufficient_Seats e) {
                    System.out.println(e.getMessage());
                    App.Flight_List.add(F);
                }
                App.updateFlightDataToFile();
                break;                
            }
        }
        if(!flag){
            System.out.println("Enter a valid Flight Number");
        }                
    }
    private void Cancel_Ticket(){
        System.out.print("Enter Flight No. : ");
        int f=App.scanner.nextInt();
        System.out.print("Enter No. of seats to Cancel : ");
        int n=App.scanner.nextInt();
        if(Booking_Details.containsKey(f)){
            if(n<=Booking_Details.get(f)){
                Booking_Details.put(f,Booking_Details.get(f)-n);
                Iterator<Flight> it = App.Flight_List.iterator();
                while(it.hasNext()){
                    Flight F= it.next();
                    if(F.Flight_No==f){
                        F.Seats_Available+=n;
                        break;
                    }
                }
                App.updateFlightDataToFile();
            }
            else{
                System.out.println("Booked Ticket is less than Entered value ");
            }
        }
        else{
            System.out.println("No Tickets Booked for the Flight No. "+f);
        }
        App.updateUserDataToFile();
    }
    public void User_Menu(){
        int choice;
        do {
            System.out.println("\n===== Flight Reservation System =====");
            System.out.println("1. Book Ticket");
            System.out.println("2. Booking Details");
            System.out.println("3. Log Out");
            System.out.println("4.Cancel Ticket ");
            System.out.print("Enter your choice: ");
            choice = App.scanner.nextInt();
            switch (choice) {
                case 1:
                    Book_Ticket();
                    break;
                case 2:
                    Booking_Details();
                    break;
                case 3:
                    System.out.println("Logging out... Thank you for using our system!");
                    break;
                case 4:
                    Cancel_Ticket();
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1 or 2.");
            }
        } while (choice != 3);
    }
}
