import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

   @Test
   public void distanceBetweenPossitiveAndNegativeCoordinatesPointsTest (){

      Point point1 = new Point(3, -4);
      Point point2 = new Point(-6, 5);

      Assert.assertEquals(point1.distance(point2),12.727922061357855);
   }

   @Test
   public void distanceBetweenPossitiveCoordinatesPointsTest (){

      Point point1 = new Point(2, 5);
      Point point2 = new Point(6, 0);

      Assert.assertEquals(point1.distance(point2),6.4031242374328485);
   }

}
