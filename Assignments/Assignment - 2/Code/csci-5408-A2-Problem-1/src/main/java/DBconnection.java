import java.sql.*;
import java.util.HashMap;

public class DBconnection {
    private static Connection localConnection = null;
    private static Connection remoteConnection = null;
    Statement statement = null;
    HashMap<String, Connection> hash_map;

    public DBconnection() throws SQLException {
        localConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/a2_dist<b00899642>?profileSQL=true", "root", "myDBisawsm!7");
        remoteConnection = DriverManager.getConnection("jdbc:mysql://35.238.156.72:3306/a2_dist<b00899642>?profileSQL=true", "root", "mygcproot");

        hash_map = new HashMap<>();
        hash_map.put("newsletter", localConnection);
        hash_map.put("park", localConnection);
        hash_map.put("equipments", remoteConnection);
        hash_map.put("vendor", remoteConnection);
    }

    public void transaction() throws SQLException {
        //Local Database - select operation on newsletter table
        statement = hash_map.get("newsletter").createStatement();
        hash_map.get("newsletter").setAutoCommit(false);
        ResultSet result = statement.executeQuery("SELECT *FROM newsletter");
        System.out.println("Newsletter Table:");
        while (result.next()) {

            System.out.println(result.getString(1) + ", " + result.getString(2)+ ", " +result.getString(3));
        }
        hash_map.get("newsletter").commit();
        hash_map.get("newsletter").rollback();
        System.out.println();

        //Local Database - select operation on park table
        statement = hash_map.get("park").createStatement();
        hash_map.get("park").setAutoCommit(false);
        PreparedStatement stmt = hash_map.get("park").prepareStatement("SELECT *FROM park WHERE Park_id =?");
        stmt.setInt(1, 610);
        ResultSet result1 = statement.executeQuery("SELECT *FROM park");
        System.out.println("Updated Park Table:");
        while (result1.next()) {

            System.out.println(result1.getInt(1) + ", " + result1.getString(2) + ", " + result1.getString(3) + ", " + result1.getString(4) + ", " + result1.getString(5) + ", " + result1.getString(6));
        }
        //Local Database - update operation on park table
        PreparedStatement stmt1 = hash_map.get("park").prepareStatement("UPDATE park SET City=? WHERE Park_id =?");
        stmt1.setString(1, "Mumbai");
        stmt1.setInt(2, 610);
        int row = stmt1.executeUpdate();
        System.out.println("number of rows updated" + row);
        System.out.println();
        hash_map.get("park").commit();
        hash_map.get("park").rollback();

        //Remote Database - select operation on equipments table
        statement = hash_map.get("equipments").createStatement();
        hash_map.get("equipments").setAutoCommit(false);
        ResultSet result2 = statement.executeQuery("SELECT *FROM equipments");
        System.out.println("Equipments Table:");
        while (result2.next()) {
            System.out.println(result2.getInt(1) + ", " + result2.getString(2) + ", " + result2.getString(3) + ", " + result2.getInt(4));
        }
        System.out.println();
        hash_map.get("equipments").commit();
        hash_map.get("equipments").rollback();

        //Remote Database - select operation on vendor table
        statement = hash_map.get("vendor").createStatement();
        hash_map.get("vendor").setAutoCommit(false);
        PreparedStatement stmt2 = hash_map.get("vendor").prepareStatement("SELECT *FROM vendor WHERE V_id =?");
        stmt2.setInt(1, 650);
        ResultSet result3 = statement.executeQuery("SELECT *FROM vendor");
        System.out.println("Updated Vendor Table:");
        while (result3.next()) {
            System.out.println(result3.getInt(1) + ", " + result3.getString(2) + ", " + result3.getString(3) + ", " + result3.getString(4));

        }
        //Remote Database - update operation on vendor table
        statement = hash_map.get("vendor").createStatement();
        PreparedStatement stmt3 = hash_map.get("vendor").prepareStatement("UPDATE vendor SET V_name=? WHERE V_id =?");
        stmt3.setString(1, "Oliver");
        stmt3.setInt(2, 650);
        int row1 = stmt3.executeUpdate();
        System.out.println("number of rows updated" + row1);
        hash_map.get("vendor").commit();
        hash_map.get("vendor").rollback();
    }
}
