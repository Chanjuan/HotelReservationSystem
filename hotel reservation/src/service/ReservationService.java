package service;

import model.*;

import java.util.*;

public class ReservationService {
   static List<Reservation> reservationList = new LinkedList<>(); //Use two different list to store and retrieve a reservation
   static List<IRoom> roomList = new LinkedList<>();

   public static List<IRoom> getRoomList() {
      return roomList;
   }

   public static void addRoom(IRoom room){
      Room newRoom = new Room(room.getRoomNumber(), room.getRoomPrice(), room.getRoomType());
      roomList.add(newRoom);
      reservationList.add(new Reservation(null, newRoom, null, null));
      // need to be recorded as a new room
   }
   public static IRoom getARoom(String roomId){//通过roomID获取该房间的其他信息（price, roomType）
      for(IRoom eachRoom: roomList){
         if(eachRoom.getRoomNumber().equals(roomId)){
            double price = eachRoom.getRoomPrice();
            RoomType type = eachRoom.getRoomType();
            return new Room(roomId, price, type);
         }
      }
      return null;
   }
   public static Reservation reserveARoom(Customer customer,IRoom room, Date checkInDate, Date checkOutDate){

      Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
      reservationList.add(newReservation);
      System.out.println("new reservation: "+ newReservation);
      //after reserve a room, we set its price into 0, which means it is not free
//      room.setPrice(0.0);
      return newReservation;
   }
   //Check the date of check-in and check-out is valid
   public static boolean isValid(Date inDate, Date outDate, Date checkInDate, Date checkOutDate){
      if(checkOutDate.before(inDate) || checkOutDate.equals(inDate)){
         return true;
      }
      if(checkInDate.after(outDate) || checkInDate.equals(outDate)){
         return true;
      }
      return false;
   }
   public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
      Set<IRoom> roomFound = new HashSet<>();

      for(Reservation eachReservation: reservationList){
         //the room is not in the map

         Date inDate = eachReservation.getCheckInDate();
         Date outDate = eachReservation.getCheckOutDate();

         //check if the rooms are free(no customer reserved)
         if(eachReservation.getCustomer() == null){
            //check if there are check-in and check-out date
            IRoom curtRoom = eachReservation.getRoom();
            roomFound.add(curtRoom);
            continue;
         }
         //Fill out rooms already reserved
//         System.out.println( eachReservation);
//         System.out.println(isValid(inDate, outDate, checkInDate, checkOutDate));
         if(!isValid(inDate, outDate, checkInDate, checkOutDate)){
            IRoom curtRoom = eachReservation.getRoom();
            roomFound.add(curtRoom);
            roomFound.remove(curtRoom);
         }
      }
      return roomFound;
   }

   public static Collection<Reservation> getCustomerReservation(Customer customer){
      List<Reservation> reservationFound = new LinkedList<>();
      for(Reservation eachReservation:reservationList){
         if(eachReservation.getCustomer() == customer){
            reservationFound.add(eachReservation);
         }
      }
      return reservationFound;
   }

   private static boolean canPrint(Reservation eachReservation){
      if(eachReservation.getCustomer() == null){
         return false;
      }
      return true;
   }
   public static void printAllReservation(){
      for(Reservation eachReservation: reservationList){
         //only print free rooms
         if(canPrint(eachReservation)){
            System.out.println(eachReservation);
         }
      }
   }

}

