public class MyFirstProgram {

   public static void main(String[] args) {

      System.out.println("My first program");

      Point point1 = new Point(3, -4);
      Point point2 = new Point(-6, 5);

      System.out.println("Расстояние между точками " + "А(" + point1.x + ";" + point1.y +")" + " и " +
                                       "B(" + point2.x + ";" + point2.y +") = "  + distance(point1, point2));

      System.out.println("Расстояние между точками " + "А(" + point1.x + ";" + point1.y +")" + " и " +
              "B(" + point2.x + ";" + point2.y +") = " + point1.distance(point2));

   }


   public static double distance(Point p1, Point p2) {

      return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));

   }

}
