package com;
import com.airline.Flight;
public class Customer {
    public String user_id;
    public String password;
    public Customer(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }
    public Flight  Book_Ticket(Flight F,int N){
        if(F.Seats_Available<N){
            System.out.println("Insufficient Seats !!");
            F.Display();
        }
        else{
            F.Seats_Available-=N;
            System.out.println("Ticket Booked Succesfully !!");
        }
        return F;
    }
}
