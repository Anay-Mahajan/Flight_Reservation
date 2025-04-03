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
    public void Display() {
        System.out.printf("| %-10d | %-15s | %-13s | %-8d | %-16d |\n", 
                          Flight_No, Source, Destination, cost, Seats_Available);
        System.out.println("+------------+-----------------+---------------+----------+------------------+");
    }
}
