package ru.stga.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stga.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

   @Parameter(names = "-c", description = "Group count")
   private int count;

   @Parameter(names = "-f", description = "File path")
   private String file;

   @Parameter(names = "-d", description = "Format data")
   private String format;

   public static void main(String[] args) throws IOException {
      GroupDataGenerator generator = new GroupDataGenerator();
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
      List<GroupData> groups = generateGroups(count);
      switch (format) {
         case "csv":
            saveAsCsv(groups, new File(file));
            break;
         case "xml":
            saveAsXml(groups, new File(file));
            break;
         case "json":
            saveAsJson(groups, new File(file));
            break;
         default:
            System.out.println("Unrecognized format");
            break;
      }
   }

   private void saveAsJson(List<GroupData> groups, File file) throws IOException {
      Gson gson = new GsonBuilder()
              .excludeFieldsWithoutExposeAnnotation()
              .setPrettyPrinting()
              .create();
      String json = gson.toJson(groups);
      try (Writer writer = new FileWriter(file)) {
         writer.write(json);
      }
   }

   private void saveAsXml(List<GroupData> groups, File file) throws IOException {
      XStream xStream = new XStream();
      xStream.processAnnotations(GroupData.class);
      String xml = xStream.toXML(groups);
      try (Writer writer = new FileWriter(file)) {
         writer.write(xml);
      }

   }

   private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
      try (Writer writer = new FileWriter(file)) {
         for (GroupData g : groups) {
            writer.write(String.format("%s:%s:%s\n", g.getName(), g.getHeader(), g.getFooter()));
         }
      }
   }

   private List<GroupData> generateGroups(int count) {
      List<GroupData> groupData = new ArrayList<>();
      for (int i = 0; i < count; i++) {
         groupData.add(new GroupData()
                 .withName(String.format("group%s", i))
                 .withHeader(String.format("header%s", i))
                 .withFooter(String.format("footer%s", i)));
      }
      return groupData;
   }
}
