package ru.stqa.pft.mantis.model;

public class Project {
   private int id;
   private String Name;

   public int getId() {
      return id;
   }

   public Project withId(int id) {
      this.id = id;
      return this;
   }

   public String getName() {
      return Name;
   }

   public Project withName(String name) {
      Name = name;
      return this;
   }
}
