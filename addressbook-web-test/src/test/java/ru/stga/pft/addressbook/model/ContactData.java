package ru.stga.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

   @XStreamOmitField
   @Id
   @Column(name = "id")
   private int id;

   @Expose
   @Column(name = "firstname")
   private String firstName;

   @Expose
   @Column(name = "lastname")
   private String lastName;

   @Expose
   @Column(name = "address")
   @Type(type = "text")
   private String address;

   @Expose
   @Column(name = "home")
   @Type(type = "text")
   private String homePhone;

   @Expose
   @Column(name = "work")
   @Type(type = "text")
   private String workPhone;

   @Expose
   @Column(name = "mobile")
   @Type(type = "text")
   private String mobilePhone;

   @Expose
   @Column(name = "email")
   @Type(type = "text")
   private String firstEmail;

   @Expose
   @Column(name = "email2")
   @Type(type = "text")
   private String secondEmail;

   @Expose
   @Column(name = "email3")
   @Type(type = "text")
   private String thirdEmail;

   @Expose
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
   private Set<GroupData> groups = new HashSet<>();

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
              Objects.equals(workPhone, that.workPhone) &&
              Objects.equals(mobilePhone, that.mobilePhone) &&
              Objects.equals(firstEmail, that.firstEmail) &&
              Objects.equals(secondEmail, that.secondEmail) &&
              Objects.equals(thirdEmail, that.thirdEmail) &&
              Objects.equals(allPhones, that.allPhones) &&
              Objects.equals(allEmails, that.allEmails);
   }

   @Override
   public int hashCode() {

      return Objects.hash(id, firstName, lastName, address, homePhone, workPhone, mobilePhone, firstEmail, secondEmail, thirdEmail, allPhones, allEmails);
   }

   @Transient
   private String allPhones;

   @Transient
   private String allEmails;

   @Expose
   @Column(name = "photo")
   @Type(type = "text")
   private String photo;

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
              ", groups=" + groups +
              ", allPhones='" + allPhones + '\'' +
              ", allEmails='" + allEmails + '\'' +
              '}';
   }

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
      if (photo != null) {
         return new File(photo);
      } else {
         return null;
      }
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
      this.photo = photo.getPath();
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

   public Groups getGroups() {
      return new Groups(groups);
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


   public ContactData inGroup(GroupData group) {
      groups.add(group);
      return this;
   }
}
