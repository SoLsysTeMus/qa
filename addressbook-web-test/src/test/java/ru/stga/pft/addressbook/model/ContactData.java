package ru.stga.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
   private int id;
   private String firstName;
   private String lastName;
   private String address;
   private String homePhone;
   private String email;
   private String group;


   public int getId() {
      return id;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
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

   public ContactData withId(int id) {
      this.id = id;
      return this;
   }

   public ContactData withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public ContactData withLastname(String lastName) {
      this.lastName = lastName;
      return this;
   }

   public ContactData withAddress(String address) {
      this.address = address;
      return this;
   }

   public ContactData withHomePhone(String homePhone) {
      this.homePhone = homePhone;
      return this;
   }

   public ContactData withEmail(String email) {
      this.email = email;
      return this;
   }

   public ContactData withGroup(String group) {
      this.group = group;
      return this;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ContactData that = (ContactData) o;
      return id == that.id &&
              Objects.equals(firstName, that.firstName) &&
              Objects.equals(lastName, that.lastName) &&
              Objects.equals(address, that.address) &&
              Objects.equals(homePhone, that.homePhone) &&
              Objects.equals(email, that.email);
   }

   @Override
   public int hashCode() {

      return Objects.hash(id, firstName, lastName, address, homePhone, email);
   }

   @Override
   public String toString() {
      return "ContactData{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", address='" + address + '\'' +
              ", homePhone='" + homePhone + '\'' +
              ", email='" + email + '\'' +
              ", group='" + group + '\'' +
              '}';
   }
}
