package model;

import java.util.regex.Pattern;

public class Customer {
   private String firstName;
   private String lastName;
   private String email;
   private final String emailRegex = "^(.+)@(.+).(.+)$";
   private final Pattern pattern = Pattern.compile(emailRegex);   //To exam the format of email input

   public Customer(String firstName,String lastName, String email){
      if(!pattern.matcher(email).matches()){ //while constructing a new customer object, we exam the format firstly
         throw new IllegalArgumentException("Error. Invalid email address.");
      }
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }

   public String getEmail() {
      return email;
   }

   @Override
   public String toString() {
      return "Customer{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            '}';
   }

}
