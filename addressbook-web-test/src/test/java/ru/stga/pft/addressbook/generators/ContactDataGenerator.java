package ru.stga.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stga.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
   @Parameter(names = "-c", description = "Contact count")
   private int count;

   @Parameter(names = "-f", description = "File path")
   private String file;

   @Parameter(names = "-d", description = "Format data")
   private String format;

   public static void main(String[] args) throws IOException {
      ContactDataGenerator generator = new ContactDataGenerator();
      JCommander jCommander = new JCommander(generator);
      try {
         jCommander.parse(args);
      } catch (ParameterException e) {
         jCommander.usage();
         return;
      }
      generator.run();
   }

   private void run() throws IOException {
      List<ContactData> contacts = generateContacts(count);
      switch (format) {
         case "xml":
            saveAsXml(contacts, new File(file));
            break;
         case "json":
            saveAsJson(contacts, new File(file));
            break;
         default:
            System.out.println("Unrecognized format");
            break;
      }
   }

   private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
      Gson gson = new GsonBuilder()
              .excludeFieldsWithoutExposeAnnotation()
              .setPrettyPrinting()
              .create();
      String json = gson.toJson(contacts);
      try (Writer writer = new FileWriter(file)) {
         writer.write(json);
      }
   }

   private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      String xml = xStream.toXML(contacts);
      try (Writer writer = new FileWriter(file)) {
         writer.write(xml);
      }
   }


   private List<ContactData> generateContacts(int count) {
      List<ContactData> contactData = new ArrayList<>();
      for (int i = 0; i < count; i++) {
         contactData.add(new ContactData()
                 .withFirstName(String.format("Dmitry%s", i))
                 .withLastName(String.format("Volkovsky%s", i))
                 .withAddress(String.format("Moscow%s", i))
                 .withHomePhone(String.format("8800555353%s", i))
                 .withFirstEmail(String.format("volkovsky%s@ros-it.ru", i))
                 .withGroup(String.format("group0")));
      }
      return contactData;
   }
}
