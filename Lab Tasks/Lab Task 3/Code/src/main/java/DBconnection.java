
import java.sql.*;

public class DBconnection {
    private static Connection localConnection = null;
    private static Connection dockerConnection = null;

    public DBconnection() throws SQLException {
        localConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "myDBisawsm!7");
        dockerConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3000/dockerDB", "root", "my-secret-pw");
    }

    private Connection getTableConnection(String tableName) {
        if (tableName.equals("user") || tableName.equals("order_info")) {
            System.out.println("the table " + tableName + " exists in local instance");
            return localConnection;
        } else if (tableName.equals("inventory")) {
            System.out.println("the table exists in docker instance");
            return dockerConnection;
        } else {
            return null;
        }
    }
    // creates an order in local database
    public void executeInsert(String tableName) throws Exception{
        Connection con = getTableConnection(tableName);
        PreparedStatement statement= con.prepareStatement("INSERT INTO " +tableName + "(order_id, item_id, user_id, item_name, quantity, order_date) values (?, ?, ?, ?, ?, ?)");
        statement.setInt(1, 500);
        statement.setInt(2, 444);
        statement.setInt(3, 40);
        statement.setString(4, "Pen");
        statement.setInt(5, 23);
        statement.setString(6, "1-7-2022");
        int row = statement.executeUpdate();
        System.out.println("The row inserted is " +row + tableName);
    }
    public void quantityUpdate() throws Exception {
        int quantity = findQuantity("inventory", "select * from inventory where item_id=" +333);
        quantity = quantity - 1;
        Connection con = getTableConnection("inventory");
        PreparedStatement statement =con.prepareStatement("UPDATE inventory SET available_quantity=? WHERE item_id =?");
        statement.setInt(1, quantity);
        statement.setInt(2, 333);
        int row = statement.executeUpdate();
        System.out.println("number of rows updated" +row);
    }
    public int findQuantity(String tableName, String query) throws Exception{
        int quantity =0;
        Connection con = getTableConnection(tableName);
        ResultSet result = con.createStatement().executeQuery(query);
        while(result.next()){
            quantity=result.getInt(3);
        }
        return quantity;
    }

    public void executeQuery(String tableName, String query) throws Exception {
        Connection con = getTableConnection(tableName);
        ResultSet result = con.createStatement().executeQuery(query);
        ResultSetMetaData md = result.getMetaData();
        int colNumber = md.getColumnCount();
        System.out.println("result for query= " + query + " is:");
        while (result.next()) {
            for(int i=1; i<=colNumber; i++){
//                if(i>1) System.out.print(", ");
                String colValue = result.getString(i);
                System.out.println( md.getColumnName(i)+ " " + colValue );
            }
            //System.out.println(result.getString(1) + ", " + result.getString(2));
            System.out.println(" ");
        }
    }
}
