package ru.stga.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {
   @XStreamOmitField
   private int id;
   @Expose
   private String firstName;
   @Expose
   private String lastName;
   @Expose
   private String address;
   @Expose
   private String homePhone;
   @Expose
   private String workPhone;
   private String mobilePhone;
   @Expose
   private String firstEmail;
   private String secondEmail;
   private String thirdEmail;
   @Expose
   private String group;
   private String allPhones;
   private String allEmails;
   @Expose
   private File photo;


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

   public File getPhoto() {
      return photo;
   }

   public String getHomePhone() {
      return homePhone;
   }

   public String getFirstEmail() {
      return firstEmail;
   }

   public String getSecondEmail() {
      return secondEmail;
   }

   public String getThirdEmail() {
      return thirdEmail;
   }

   public String getGroup() {
      return group;
   }

   public String getWorkPhone() {
      return workPhone;
   }

   public String getMobilePhone() {
      return mobilePhone;
   }

   public String getAllPhones() {
      return allPhones;
   }

   public String getAllEmails() {
      return allEmails;
   }

   public ContactData withId(int id) {
      this.id = id;
      return this;
   }

   public ContactData withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public ContactData withLastName(String lastName) {
      this.lastName = lastName;
      return this;
   }

   public ContactData withAddress(String address) {
      this.address = address;
      return this;
   }

   public ContactData withPhoto(File photo) {
      this.photo = photo;
      return this;
   }

   public ContactData withHomePhone(String homePhone) {
      this.homePhone = homePhone;
      return this;
   }

   public ContactData withFirstEmail(String firstEmail) {
      this.firstEmail = firstEmail;
      return this;
   }

   public ContactData withSecondEmail(String secondEmail) {
      this.secondEmail = secondEmail;
      return this;
   }

   public ContactData withThirdEmail(String thirdEmail) {
      this.thirdEmail = thirdEmail;
      return this;
   }

   public ContactData withAllEmails(String allEmails) {
      this.allEmails = allEmails;
      return this;
   }

   public ContactData withGroup(String group) {
      this.group = group;
      return this;
   }

   public ContactData withWorkPhone(String workPhone) {
      this.workPhone = workPhone;
      return this;
   }

   public ContactData withMobilePhone(String mobilePhone) {
      this.mobilePhone = mobilePhone;
      return this;
   }

   public ContactData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ContactData that = (ContactData) o;
      return id == that.id &&
              Objects.equals(firstName, that.firstName) &&
              Objects.equals(lastName, that.lastName);
   }

   @Override
   public int hashCode() {

      return Objects.hash(id, firstName, lastName);
   }

   @Override
   public String toString() {
      return "ContactData{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", address='" + address + '\'' +
              ", homePhone='" + homePhone + '\'' +
              ", workPhone='" + workPhone + '\'' +
              ", mobilePhone='" + mobilePhone + '\'' +
              ", firstEmail='" + firstEmail + '\'' +
              ", secondEmail='" + secondEmail + '\'' +
              ", thirdEmail='" + thirdEmail + '\'' +
              ", group='" + group + '\'' +
              ", allPhones='" + allPhones + '\'' +
              ", allEmails='" + allEmails + '\'' +
              '}';
   }


}
