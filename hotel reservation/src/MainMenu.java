import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;


import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
   private static Customer createAccount(Scanner scanner){
      System.out.println("Please enter an email address");
      String email = scanner.nextLine();
      System.out.println("Please enter your first name");
      String firstName = scanner.nextLine();
      System.out.println("Please enter your last name");
      String lastName = scanner.nextLine();
      HotelResource.createACustomer(email, firstName, lastName);
      System.out.println("Your account was created successfully");
      return HotelResource.getCustomer(email);
   }

   private static Customer logIn(Scanner scanner){
      System.out.println("Please enter your email address");
      String email = scanner.nextLine();
      Customer customer= HotelResource.getCustomer(email);
      //check if the account already exists
      if(customer != null){
         return customer;
      }
      else{
         //Account does not exist. Ask for account creation
         System.out.println("The account does not exist.");
         System.out.println("Do you want to create a new account? y/n");
         String yesOrNo = scanner.nextLine();
         if(yesOrNo.equals("y")){
            createAccount(scanner);
         }
         if(yesOrNo.equals("n")){
            return null;
         }
      }
      return null;
   }
   private static Customer choice(Scanner scanner, String accountChoice){
      if(accountChoice.equals("1")){
         return logIn(scanner);  //If the account does not  exist, return null
      }
      if(accountChoice.equals("2")){
         return createAccount(scanner);
      }
      return null;
   }
   public static void main(String[] args) throws ParseException {
      Scanner scanner = new Scanner(System.in);
      Customer customer = null;
      while(true) {
         System.out.println("Welcome to the Hotel Reservation Application");
         System.out.println("-----------------------------------");
         System.out.println("1.Find and reserve a room");
         System.out.println("2.See my reservations");
         System.out.println("3.Create an account");
         System.out.println("4.Admin");
         System.out.println("5.Exit");
         System.out.println("-----------------------------------");
         System.out.println("Please select a number for the menu option");
         String input = scanner.nextLine();

         switch (input){
            case "1":
               //sign in or sign up?
               if(customer == null){   //If user has not signed up
                  System.out.println("Do you want to log in or create an account?");
                  System.out.println("1.Log in");
                  System.out.println("2.Create an account");
                  String accountChoice = scanner.nextLine();
                  customer = choice(scanner,accountChoice);
               }
               //If user did not choose to create an account then break
               if(customer == null){
                  break;
               }

               //Enter dates
               System.out.println("Please enter the check-in date(MM/DD/YYY)");
               java.text.SimpleDateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy");
               String checkIn = scanner.nextLine();
               Date checkInDate = formatter.parse(checkIn);

               System.out.println("Please enter the check-out date(MM/DD/YYY)");
               String checkOut = scanner.nextLine();
               Date checkOutDate = formatter.parse(checkOut);
               //find all free rooms
               Collection<IRoom> rooms= HotelResource.findARoom(checkInDate,checkOutDate);
               for(IRoom eachRoom: rooms){
                  System.out.println(eachRoom);
               }

               //Select the room number to reservation
               System.out.println("Please enter the room number for reservation");
               String roomNumber = scanner.nextLine();
               IRoom room = HotelResource.getRoom(roomNumber);
               HotelResource.bookARoom(customer.getEmail(), room, checkInDate, checkOutDate);
               System.out.println("You've reserved the room successfully");

               break;
            case "2":
               //If the customer has not logged in
               if(customer == null){
                  System.out.println("You haven't logged in yet");
                  System.out.println("Do you want to log in or create an account?");
                  System.out.println("1.Log in");
                  System.out.println("2.Create an account");
                  String accountChoice = scanner.nextLine();
                  customer = choice(scanner,accountChoice);
                  if(customer == null){
                     break;
                  }
               }
               else{
                  Collection<Reservation> customersReservations = HotelResource.getCustomersReservations(customer.getEmail());
                  for(Reservation eachReservation: customersReservations){
                     System.out.println(eachReservation);
                  }
               }
               break;
            case "3":
               createAccount(scanner);
               break;
            case "4":
               AdminMenu.main(null);
               break;
            case "5":
               customer = null;
               System.out.println("You logged out successfully");
               break;
         }
      }

   }
}
