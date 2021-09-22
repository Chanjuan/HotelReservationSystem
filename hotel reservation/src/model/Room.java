package model;

import java.util.Objects;

public class Room implements IRoom{
   private String roomNumber;
   private Double price;
   private RoomType enumeration;

   public Room(String roomNumber, Double price, RoomType enumeration){
      this.roomNumber = roomNumber;
      this.price = price;
      this.enumeration = enumeration;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   @Override
   public String toString() {
      return "Room{" +
            "roomNumber='" + roomNumber + '\'' +
            ", price=" + price +
            ", enumeration=" + enumeration +
            '}';
   }

   @Override
   public String getRoomNumber() {
      return roomNumber;
   }

   @Override
   public Double getRoomPrice() {
      return price;
   }

   @Override
   public RoomType getRoomType() {
      return enumeration;
   }

   //If the room was reserved, we change the price into 0
//and mark isaFree = false
   @Override
   public boolean isFree() {
      if(price == 0.0){
         return true;
      }
      return false;
   }
   //hashset中使用equals和hashcode方法进行对象比较
//
//   @Override
//   public boolean equals(Object o) {
//      if (this == o) {
//         return true;
//      }
//      if (o == null) {
//         return false;
//      }
//
//      if (getClass() != o.getClass()) {
//         return false;
//      }
//      Room room = (Room) o;
//      if (price != room.price) {
//         return false;
//      }
//      if(!enumeration.equals(room.enumeration)){
//         return false;
//      }
//      if (roomNumber == null) {
//         if (room.roomNumber != null) {
//            return false;
//         }
//      } else {
//         if (!roomNumber.equals(room.roomNumber)) {
//            return false;
//         }
//      }
//      return true;
//   }
//      @Override
//      public int hashCode() {
//         return 1;
//      }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Room room = (Room) o;
      return roomNumber.equals(room.roomNumber) && price.equals(room.price) && enumeration == room.enumeration;
   }

   @Override
   public int hashCode() {
      return Objects.hash(roomNumber, price, enumeration);
   }
}
