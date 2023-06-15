import java.time.Duration;
import java.time.Instant;

public class ecommerceMain {
    public static void main(String args[])
    {
        try
        {

            DBconnection dBconnection = new DBconnection();

            Instant start = Instant.now();
            dBconnection.executeQuery("user", "select * from user");
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");

            Instant start1 = Instant.now();
            dBconnection.executeQuery("order_info", "select * from order_info");
            Instant end1 = Instant.now();
            Duration timeElapsed1 = Duration.between(start1, end1);
            System.out.println("Time taken: "+ timeElapsed1.toMillis() +" milliseconds");

            Instant start2 = Instant.now();
            dBconnection.executeQuery("inventory", "select * from inventory");
            Instant end2 = Instant.now();
            Duration timeElapsed2 = Duration.between(start2, end2);
            System.out.println("Time taken: "+ timeElapsed2.toMillis() +" milliseconds");

            Instant start3 = Instant.now();
            dBconnection.executeInsert("order_info");
            Instant end3 = Instant.now();
            Duration timeElapsed3 = Duration.between(start3, end3);
            System.out.println("Time taken: "+ timeElapsed3.toMillis() +" milliseconds");

            Instant start4 = Instant.now();
            dBconnection.quantityUpdate();
            Instant end4 = Instant.now();
            Duration timeElapsed4 = Duration.between(start4, end4);
            System.out.println("Time taken: "+ timeElapsed4.toMillis() +" milliseconds");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
