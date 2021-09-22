import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
   public static void addRoom(Scanner scanner, List<IRoom> rooms){
      System.out.println("Enter room number");
      String roomNumber = scanner.nextLine();
      System.out.println("Enter price per night");
      String priceString = scanner.nextLine();
      Double price = Double.valueOf(priceString);
      System.out.println("Enter room type: 1 for single bed, 2 for double bed");
      String roomType = scanner.nextLine();
      System.out.println(roomType);
      RoomType type;
      if(roomType.equals("1")){
         type = RoomType.SINGLE;
         rooms.add(new Room(roomNumber, price, type));
      }
      if(roomType.equals("2")){
         type = RoomType.DOUBLE;
         rooms.add(new Room(roomNumber, price, type));
      }
      System.out.println("Would you like to add another room? y/n");
      String haveAnother = scanner.nextLine();
      if(haveAnother.equals("y")){
         addRoom(scanner,rooms);
      }
      if(haveAnother.equals("n")){
         AdminResource.addRoom(rooms); //If we don't want to add more rooms return to the AdminMenu
      }
   }
   public static void main(String[] args) throws ParseException {
      Scanner scanner = new Scanner(System.in);
      while(true){
         System.out.println("Admin Menu");
         System.out.println("-----------------------------------");
         System.out.println("1.See all Customers");
         System.out.println("2.See all Rooms");
         System.out.println("3.See all Reservations");
         System.out.println("4.Add a Room");
         System.out.println("5.Back to Main Menu");
         System.out.println("-----------------------------------");
         System.out.println("Please select a number for the menu option");
         String input = scanner.nextLine();
         switch (input){
            case "1":
               Collection<Customer> customers = AdminResource.getAllCustomers();
               for(Customer customer:customers){
                  System.out.println(customer);
               }
               break;

            case "2":
               Collection<IRoom> allRooms= AdminResource.getAllRooms();
               for(IRoom eachRoom: allRooms){
                  System.out.println(eachRoom);
               }
               break;

            case "3":
               AdminResource.displayAllReservations();
               break;

            case "4":
               List<IRoom> rooms = new LinkedList<>();
               addRoom(scanner, rooms);
               break;

            case "5":
               MainMenu.main(null);
         }
      }
   }
}
