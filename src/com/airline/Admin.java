package com.airline;
public class Admin {
    public String Admin_ID;
    public String password;
    public Admin(String admin_ID, String password) {
        Admin_ID = admin_ID;
        this.password = password;
    }
    public Flight Add_Flight(int flight_No, String source, String destination, int cost, int seats_Available)  {
        Flight F=new Flight(flight_No, source, destination, cost, seats_Available);
        return F;
    }
}
