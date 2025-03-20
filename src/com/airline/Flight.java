package com.airline;
public class Flight {
    public int Flight_No;
    public String Source;
    public String Destination;
    public int cost;
    public int Seats_Available;
    public Flight(int flight_No, String source, String destination, int cost, int seats_Available) {
        Flight_No = flight_No;
        Source = source;
        Destination = destination;
        this.cost = cost;
        Seats_Available = seats_Available;
    }
    public  void Display(){
        System.out.println(Flight_No);
        System.out.println(Source);
        System.out.println( Destination);
        System.out.println(cost);
        System.out.println(Seats_Available);
    }
}
