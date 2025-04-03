package com;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.airline.Admin;
import com.airline.Flight;
public class App {
    public static ArrayList<Flight>Flight_List=new ArrayList<>();
    public static ArrayList<Admin>Admin_List=new ArrayList<>();
    public static ArrayList<Customer>User_List=new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public static void updateFlightDataToFile() {
        String filePath = "flight.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Flight flight : Flight_List) {
                writer.write(flight.Flight_No + " " + flight.Source + " " + flight.Destination + " " 
                             + flight.cost + " " + flight.Seats_Available);
                writer.newLine();
            }
            System.out.println("Flight data updated in Database.");
        } catch (IOException e) {
            System.out.println("Error updating flight data: " + e.getMessage());
        }
    }
    public static void updateUserDataToFile() {
        String filePath = "user_flight.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Customer C : User_List) {
                writer.write(C.user_id);
                for(int f: C.Booking_Details.keySet()){
                    writer.write(" "+f+" "+C.Booking_Details.get(f));
                }
                writer.newLine();
            }
            System.out.println("User Data Updated into the file.");
        } catch (IOException e) {
            System.out.println("Error updating flight data: " + e.getMessage());
        }
    }
    public static void appendUserToFile(String userId, String password) {
        String filePath = "user.txt"; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) { 
            writer.write(userId + " " + password);
            writer.newLine();
            System.out.println("New user added to Database.");
        } catch (IOException e) {
            System.out.println("Error updating user data: " + e.getMessage());
        }
    }
    private  static void Load_the_data(){
        String filePath = "flight.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) { 
                String[] data = line.split(" ");
                App.Flight_List.add(new Flight(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        Integer.parseInt(data[3]),
                        Integer.parseInt(data[4])
                ));
            }
        } 
        catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        String filePath2 = "admin.txt";
        try (BufferedReader br2 = new BufferedReader(new FileReader(filePath2))) {
            String line;
            while ((line = br2.readLine()) != null) { 
                String[] data = line.split(" ");
                App.Admin_List.add(new Admin(data[0], data[1]));
            }
        } 
        catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        String filepath4="user_flight.txt";
        HashMap<String,HashMap<Integer,Integer>>Booking_Database =new HashMap<String,HashMap<Integer,Integer>>();
        try (BufferedReader br4=new BufferedReader(new FileReader(filepath4))){
            String line;
            while((line =br4.readLine())!=null){
                String[] data =line.split(" ");
                int n=data.length;
                HashMap<Integer,Integer>temp=new HashMap<Integer,Integer>();
                for(int i=1;i<n;i+=2){
                    temp.put(Integer.parseInt(data[i]),Integer.parseInt(data[i+1]));
                }
                Booking_Database.put(data[0], temp);
            }
        } 
        catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        String filePath3="user.txt";
        try (BufferedReader br3 = new BufferedReader(new FileReader(filePath3))) {
            String line;
            while ((line = br3.readLine()) != null) { 
                String[] data = line.split(" ");
                Customer C=new Customer(data[0], data[1]);
                if(Booking_Database.containsKey(C.user_id)){
                    for(int flight_no:Booking_Database.get(C.user_id).keySet()){
                        C.Booking_Details.put(flight_no,Booking_Database.get(C.user_id).get(flight_no));
                    }
                }
                App.User_List.add(C);
            }
        } 
        catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    public static void main(String[] args) throws Exception {
        App.Load_the_data();
        int choice;
        while (true) { 
            System.out.println("\n===== Flight Reservation System =====");
            System.out.println("1. User Login ");
            System.out.println("2. Admin Login ");
            System.out.println("3. New User Registration");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\nUser Menu Selected.");
                    System.out.println("Enter User_ID : ");
                    scanner.nextLine();
                    String id=scanner.nextLine();
                    System.out.println("Enter password : ");
                    String pass=scanner.nextLine();
                    boolean flag=false;
                    for(Customer U :User_List){
                        if(U.user_id.equals(id) && U.password.equals(pass)){
                            U.User_Menu();
                            flag=true;
                        }
                    }
                    if(!flag){
                        System.out.println("Incorrect User Name or Password");
                    }
                break;

                case 2:
                    System.out.println("\nAdmin Menu Selected.");
                    System.out.println("Enter User_ID : ");
                    scanner.nextLine();
                    String aid=scanner.nextLine();
                    System.out.println("Enter password : ");
                    String apass=scanner.nextLine();
                        boolean flag1=false;
                        for(Admin A :Admin_List){
                            if(A.Admin_ID.equals(aid) && A.password.equals(apass)){
                                A.Admin_Menu();
                                flag1=true;
                            }
                        }
                        if(!flag1){
                            System.out.println("Incorrect User Name or Password");
                        }
                    break;
                case 3:
                    System.out.println("Enter a User_ID : ");
                    scanner.nextLine();
                    String uid=scanner.nextLine();
                    System.out.println("Enter Password to be set : ");
                    String upass=scanner.nextLine();
                    User_List.add(new Customer(uid,upass));
                    for(Customer c:User_List){
                        System.out.println(c.user_id);
                        System.out.println(c.password);
                    }
                    appendUserToFile(uid, upass);
                    break;
                case 4:
                    System.out.println("\nExiting the system. Thank you!");
                    scanner.close();
                    System.exit(1);
                    break;

                default:
                    System.out.println("\nInvalid choice! Please enter 1, 2,3 or 4.");
            }
        }
    }
}
