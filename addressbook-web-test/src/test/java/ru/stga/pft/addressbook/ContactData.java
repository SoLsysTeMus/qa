package ru.stga.pft.addressbook;

public class ContactData {
   private final String firstName;
   private final String lastname;
   private final String address;
   private final String homePhone;
   private final String email;

   public ContactData(String firstName, String lastname, String address, String homePhone, String email) {
      this.firstName = firstName;
      this.lastname = lastname;
      this.address = address;
      this.homePhone = homePhone;
      this.email = email;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastname() {
      return lastname;
   }

   public String getAddress() {
      return address;
   }

   public String getHomePhone() {
      return homePhone;
   }

   public String getEmail() {
      return email;
   }
}
