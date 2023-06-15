import java.util.HashMap;
import java.util.List;

public class SemanticAnalysisMain {
    public static void main(String[] args) {
        SemanticAnalysis semanticAnalysis = new SemanticAnalysis();
        semanticAnalysis.computeTfIdf();
        HashMap<Integer, List<Integer>> result = semanticAnalysis.computeTermFrequency();
        semanticAnalysis.displayTable(result);
    }
}


