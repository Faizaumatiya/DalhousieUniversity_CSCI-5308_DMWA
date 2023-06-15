import java.sql.*;
import java.util.ArrayList;

public class DBtransactions {
    private static Connection conn  = null;
    static Statement statement = null;
    static Boolean Lock = false;
    public static void acquireLock(String concurrentTransaction_id) throws InterruptedException {
        if(Lock == false)
        {
            Lock = true;
            System.out.println( concurrentTransaction_id+" has acquired lock");
        }else{
            System.out.println(concurrentTransaction_id+" is waiting to acquire lock");
            Thread.sleep(3000);
            acquireLock(concurrentTransaction_id);
        }
    }
    static void operation(ArrayList<String> runTransaction, String concurrentTransaction_id)
    {   Connection conn=null;

        try {
            conn  = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_otn", "root", "myDBisawsm!7");
            conn .setAutoCommit(false);

            if(concurrentTransaction_id.equals("T1")){
                acquireLock(concurrentTransaction_id);
                //select -  animals_scientific_name
                statement = conn .createStatement();
                ResultSet result = statement.executeQuery(runTransaction.get(0));
                while (result.next()) {

                    System.out.println("[T1] - Select statement is executed: "+result.getString(1) + ", " + result.getString(2)+ ", " +result.getInt(3)+", "+result.getInt(4));
                }

                Thread.sleep(3000);
                //select - animals_scientific_name with id
                PreparedStatement stmt = conn .prepareStatement(runTransaction.get(1));
                stmt.setInt(1,105792);
                ResultSet result1 = stmt.executeQuery();
                while(result1.next()){
                    System.out.println("[T1] - Select before update statement is executed: "+result1.getString(1) + ", " + result1.getString(2) + ", " + result1.getInt(3) + ", " + result1.getInt(4));
                }

                //update - animals_scientific_name with id
                PreparedStatement stmt1 = conn .prepareStatement(runTransaction.get(2));
                stmt1.setString(1, "Homo Sapien");
                stmt1.setInt(2,105792);
                int row = stmt1.executeUpdate();
                System.out.println("number of rows updated: " + row);

                PreparedStatement stmt4 = conn .prepareStatement(runTransaction.get(1));
                stmt4.setInt(1,105792);
                ResultSet result4 = stmt4.executeQuery();
                while(result4.next()){
                    System.out.println("[T1] - Select after update statement is executed: "+result4.getString(1) + ", " + result4.getString(2) + ", " + result4.getInt(3) + ", " + result4.getInt(4));
                }

                //select - animal_scientific_name with id
                PreparedStatement stmt2 = conn .prepareStatement(runTransaction.get(3));
                stmt2.setInt(1, 105801);
                ResultSet result2 = stmt2.executeQuery();
                while(result2.next()){
                    System.out.println("[T1] - Select before Delete statement is executed: "+ result2.getString(1) + ", " + result2.getString(2) + ", " + result2.getInt(3) + ", " + result2.getInt(4));
                }

                //delete- animal_scientific_name
                PreparedStatement stmt3 = conn .prepareStatement(runTransaction.get(4));
                stmt3.setInt(1, 105801);
                int row2 = stmt3.executeUpdate();
                System.out.println("number of rows deleted: " + row2);
                releaseLock(concurrentTransaction_id);
                conn .commit();
            }
            else
            {
                //select -  animals_scientific_name - done
                acquireLock(concurrentTransaction_id);
                conn   = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_otn", "root", "myDBisawsm!7");
                statement = conn  .createStatement();
                conn  .setAutoCommit(false);
                ResultSet result = statement.executeQuery(runTransaction.get(0));
                while (result.next()) {

                    System.out.println("[T2] - Select statement is executed: "+result.getString(1) + ", " + result.getString(2)+ ", " +result.getInt(3)+", "+result.getInt(4));
                }

                Thread.sleep(3000);
                //select - animals_scientific_name with id
                PreparedStatement stmt = conn  .prepareStatement(runTransaction.get(1));
                stmt.setInt(1,105792);
                ResultSet result1 = stmt.executeQuery();
                while(result1.next()){
                    System.out.println("[T2] - Select before Update statement is executed: "+result1.getString(1) + ", " + result1.getString(2) + ", " + result1.getInt(3) + ", " + result1.getInt(4));
                }

                //update - animals_scientific_name with id
                PreparedStatement stmt1 = conn  .prepareStatement(runTransaction.get(2));
                stmt1.setString(1, "Canis Lupus");
                stmt1.setInt(2,105792);
                int row = stmt1.executeUpdate();
                System.out.println("number of rows updated: " + row);

                PreparedStatement stmt4 = conn .prepareStatement(runTransaction.get(1));
                stmt4.setInt(1,105792);
                ResultSet result4 = stmt4.executeQuery();
                while(result4.next()){
                    System.out.println("[T2] - Select after update statement is executed: "+result4.getString(1) + ", " + result4.getString(2) + ", " + result4.getInt(3) + ", " + result4.getInt(4));
                }

                //select - animal_scientific_name with id
                PreparedStatement stmt2 = conn .prepareStatement(runTransaction.get(3));
                stmt2.setInt(1, 105801);
                ResultSet result2 = stmt2.executeQuery();
                while(result2.next()){
                    System.out.println("[T2] - Select before delete statement is executed: "+result2.getString(1) + ", " + result2.getString(2) + ", " + result2.getInt(3) + ", " + result2.getInt(4));
                }

                //delete- animal_scientific_name
                PreparedStatement stmt3 = conn  .prepareStatement(runTransaction.get(4));
                stmt3.setInt(1, 105801);
                int row2 = stmt3.executeUpdate();
                System.out.println("number of rows deleted: " + row2);
                releaseLock(concurrentTransaction_id);
                conn  .commit();
            }
        }
        catch (SQLException e)
        {
            try {
                conn .rollback();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        finally{
            try {
                if(conn !=null ){
                conn .close();

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void releaseLock(String concurrentTransaction_id)
    {
        Lock =false;
        System.out.println("Locks released by:"+concurrentTransaction_id);
    }
}
