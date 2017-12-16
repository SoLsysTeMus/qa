package ru.stga.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
   private int id;
   private String firstName;
   private String lastname;
   private String address;
   private String homePhone;
   private String email;
   private String group;

   public ContactData(String firstName, String lastname, String address, String homePhone, String email, String group) {

      this.id = Integer.MAX_VALUE;
      this.firstName = firstName;
      this.lastname = lastname;
      this.address = address;
      this.homePhone = homePhone;
      this.email = email;
      this.group = group;
   }

   public ContactData(int id, String firstName, String lastname, String address, String homePhone, String email) {

      this.id = id;
      this.firstName = firstName;
      this.lastname = lastname;
      this.address = address;
      this.homePhone = homePhone;
      this.email = email;
      this.group = null;
   }

   public int getId() {
      return id;
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ContactData that = (ContactData) o;
      return Objects.equals(firstName, that.firstName) &&
              Objects.equals(lastname, that.lastname) &&
              Objects.equals(address, that.address) &&
              Objects.equals(email, that.email);
   }

   @Override
   public int hashCode() {

      return Objects.hash(firstName, lastname, address, email);
   }

   @Override
   public String toString() {
      return "ContactData{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastname='" + lastname + '\'' +
              ", address='" + address + '\'' +
              ", homePhone='" + homePhone + '\'' +
              ", email='" + email + '\'' +
              ", group='" + group + '\'' +
              '}';
   }
}
