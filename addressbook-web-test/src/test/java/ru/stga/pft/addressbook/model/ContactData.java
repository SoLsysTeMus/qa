package ru.stga.pft.addressbook.model;

public class ContactData {
   private String firstName;
   private String lastname;
   private String address;
   private String homePhone;
   private String email;
   private String group;

   public ContactData(String firstName, String lastname, String address, String homePhone, String email, String group) {

      this.firstName = firstName;
      this.lastname = lastname;
      this.address = address;
      this.homePhone = homePhone;
      this.email = email;
      this.group = group;
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

   public String getGroup() {
      return group;
   }
}
