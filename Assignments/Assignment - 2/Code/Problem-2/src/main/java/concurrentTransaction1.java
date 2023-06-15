import java.util.ArrayList;

public class concurrentTransaction1 implements Runnable{
    String concurrentTransaction_id = "T1";
    String T1_statement1 = "SELECT *FROM animals_scientific_name";
    String T1_statement2 = "SELECT *FROM animals_scientific_name WHERE aphiaid =?;";
    String T1_statement3 = "UPDATE animals_scientific_name SET scientificname =?  WHERE aphiaid =?;";
    String T1_statement4 = "SELECT * FROM animals_scientific_name WHERE aphiaid =?;";
    String T1_statement5 = "DELETE FROM animals_scientific_name WHERE aphiaid =? ;";

    // start always calls run
    public void run()
    {
        ArrayList<String> runTransaction1 = new ArrayList<>();
        runTransaction1.add(T1_statement1);
        runTransaction1.add(T1_statement2);
        runTransaction1.add(T1_statement3);
        runTransaction1.add(T1_statement4);
        runTransaction1.add(T1_statement5);
        DBtransactions.operation(runTransaction1,concurrentTransaction_id);

    }
}
