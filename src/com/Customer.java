package com;
import com.airline.Flight;
public class Customer {
    public String user_id;
    public String password;
    public Customer(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }
    public Flight  Book_Ticket(Flight F,int N) throws Insufficient_Seats{
        if(F.Seats_Available<N){
            throw new Insufficient_Seats("Insufficient Seats");
        }
        else{
            F.Seats_Available-=N;
            System.out.println("Ticket Booked Succesfully !!");
        }
        return F;
    }
}
