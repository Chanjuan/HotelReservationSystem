package model;

public class FreeRoom extends Room{

   public FreeRoom(String roomNumber, Double price, RoomType enumeration) {
      super(roomNumber, 0.0, enumeration);
   }

   @Override      //Need further override
   public String toString() {
      return super.toString();
   }
}
