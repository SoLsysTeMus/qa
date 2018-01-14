package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

   @Test
   public static void testConnection() {
      Connection conn = null;

      try {
         conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&" +
                 "user=root&password=");

         Statement statement = conn.createStatement();
         ResultSet resultSet = statement.executeQuery("Select group_id, group_name, group_header, group_footer from group_list");
         Groups groups = new Groups();
         while (resultSet.next()){
            groups.add(new GroupData()
                    .withId(resultSet.getInt("group_id"))
                    .withName(resultSet.getString("group_name"))
                    .withHeader(resultSet.getString("group_header"))
                    .withFooter(resultSet.getString("group_footer")));
         }

         // Do something with the Connection

         resultSet.close();
         statement.close();
         conn.close();

         System.out.println(groups);

      } catch (SQLException ex) {
         // handle any errors
         System.out.println("SQLException: " + ex.getMessage());
         System.out.println("SQLState: " + ex.getSQLState());
         System.out.println("VendorError: " + ex.getErrorCode());
      }
   }
}
