package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

   @Id
   @Column(name = "id")
   private int id;

   @Column(name = "username")
   private String username;

   @Column(name = "email")
   private String email;

   @Column(name = "enabled", columnDefinition = "TINYINT(1) DEFAULT 0")
   private int enabled;


   public int getId() {
      return id;
   }

   public String getUsername() {
      return username;
   }

   public String getEmail() {
      return email;
   }

   public int getEnabled() {
      return enabled;
   }
}
