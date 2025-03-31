import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import com.Customer;
import com.Insufficient_Seats;
import com.airline.Admin;
import com.airline.Flight;
public class App {
    static ArrayList<Flight>Flight_List=new ArrayList<>();
    static ArrayList<Admin>Admin_List=new ArrayList<>();
    static ArrayList<Customer>User_List=new ArrayList<>();
    public static void User_Menu(Customer U){
        int choice;
        @SuppressWarnings("resource")
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("\n===== Flight Reservation System =====");
            System.out.println("1. Book Ticket");
            System.out.println("2. Log Out");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Flight Number: ");
                    int flightNumber = scanner.nextInt();
                    System.out.print("Enter Number of Seats: ");
                    int numberOfSeats = scanner.nextInt();
                    boolean flag=false;
                    Iterator<Flight> it = Flight_List.iterator();
                    while (it.hasNext()) {
                        Flight F = it.next();
                        it.remove();
                        if (F.Flight_No == flightNumber) {
                            flag = true;
                            try {
                                Flight_List.add(U.Book_Ticket(F, numberOfSeats)); 
                            } catch (Insufficient_Seats e) {
                                System.out.println(e.getMessage());
                                Flight_List.add(F);
                            }
                            updateFlightDataToFile();
                            break;
                        }
                    }
                    if(!flag){
                        System.out.println("Enter a valid Flight Number");
                    }
                    break;
                case 2:
                    System.out.println("Logging out... Thank you for using our system!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1 or 2.");
            }
        } while (choice != 2);

    }
    public static void Admin_Menu(Admin A){
        int choice;
        @SuppressWarnings("resource")
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("\n===== Flight Reservation System =====");
            System.out.println("1. Add Flight ");
            System.out.println("2. Log Out");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
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
                    Flight_List.add(A.Add_Flight(flightNo, source, destination, cost, seats));
                    updateFlightDataToFile();
                    break;
                case 2:
                    System.out.println("Logging out... Thank you for using our system!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1 or 2.");
            }

        } while (choice != 2);        
    }
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
    public static void appendUserToFile(String userId, String password) {
        String filePath = "user.txt"; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { 
            writer.write(userId + " " + password);
            writer.newLine();
            System.out.println("New user added to Database.");
        } catch (IOException e) {
            System.out.println("Error updating user data: " + e.getMessage());
        }
    }
    public static void main(String[] args) throws Exception {
        String filePath = "flight.txt";
        File flightFile = new File(filePath);
        if (!flightFile.exists()) {
            try {
                flightFile.createNewFile(); 
                System.out.println("flight.txt file created.");
            } catch (IOException e) {
                System.out.println("Error creating flight.txt: " + e.getMessage());
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) { 
                String[] data = line.split(" ");
                Flight_List.add(new Flight(
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
        File adminFile = new File(filePath2);
        if (!adminFile.exists()) {
            try {
                adminFile.createNewFile();
                System.out.println("admin.txt file created.");
            } catch (IOException e) {
                System.out.println("Error creating admin.txt: " + e.getMessage());
            }
        }
        try (BufferedReader br2 = new BufferedReader(new FileReader(filePath2))) {
            String line;
            while ((line = br2.readLine()) != null) { 
                String[] data = line.split(" ");
                Admin_List.add(new Admin(data[0], data[1]));
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
                User_List.add(new Customer(data[0], data[1]));
            }
        } 
        catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) { 
            System.out.println("\n===== Flight Reservation System =====");
            System.out.println("1. User Login ");
            System.out.println("2. Admin Login ");
            System.out.println("3.New User Registeration");
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
                            User_Menu(U);
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
                                Admin_Menu(A);
                                flag1=true;
                            }
                        }
                        if(!flag1){
                            System.out.println("Incorrect User Name or Password");
                        }
                    break;
                case 3:
                    System.out.println("Enter User Name to be set : ");
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
