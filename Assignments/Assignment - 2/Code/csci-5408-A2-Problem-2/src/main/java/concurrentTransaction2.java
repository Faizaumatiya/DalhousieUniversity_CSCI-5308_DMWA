import java.util.ArrayList;

public class concurrentTransaction2 implements Runnable
{
    String concurrentTransaction_id = "T2";
    String T2_statement1 = "SELECT *FROM animals_scientific_name";
    String T2_statement2 = "SELECT *FROM animals_scientific_name WHERE aphiaid =?;";
    String T2_statement3 = "UPDATE animals_scientific_name SET scientificname =?  WHERE aphiaid =?;";
    String T2_statement4 = "SELECT *FROM animals_scientific_name WHERE aphiaid =?;";
    String T2_statement5 = "DELETE FROM animals_scientific_name WHERE aphiaid =?;";

    // start always calls run
    public void run()
    {
        ArrayList<String> runTransaction2 = new ArrayList<>();
        runTransaction2.add(T2_statement1);
        runTransaction2.add(T2_statement2);
        runTransaction2.add(T2_statement3);
        runTransaction2.add(T2_statement4);
        runTransaction2.add(T2_statement5);
        DBtransactions.operation(runTransaction2,concurrentTransaction_id);

    }
}

