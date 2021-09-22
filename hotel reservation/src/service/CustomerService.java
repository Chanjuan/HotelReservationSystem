package service;

import model.Customer;

import java.util.*;

public class CustomerService {
   static Map<String, Customer> customerMap = new HashMap<>();

   public static void addCustomer(String email, String firstName, String lastName){
      Customer newCustomer = new Customer(firstName, lastName, email);
      customerMap.put(email, newCustomer);
   }
   public static Customer getCustomer(String customerEmail){
      //Using map
      if(!customerMap.containsKey(customerEmail)){
         return null;
      }
      return customerMap.get(customerEmail);
   }
   public static Collection<Customer> getAllCustomers(){
      List<Customer> customerList = new LinkedList<>();
      for(String eachEmail:customerMap.keySet()){  //Traverse the whole map by email
         customerList.add(customerMap.get(eachEmail));   //Add all customer
      }
      return customerList;
   }

}
