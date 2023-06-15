import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
   References:
   [1] https://www.java67.com/2016/07/how-to-read-text-file-into-arraylist-in-java.html
*/

public class WordCounterEngine {
    public static List<String> readNewsFile(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void readAndWordCount() {
        String[] keywords = {"Canada", "University", "Dalhousie University", "Halifax", "Canada Education",
                "Moncton", "Toronto", "oil", "inflation"};
        ArrayList<HashMap<String, Integer>> eachLine = new ArrayList<>();

        for (int i = 0; i < keywords.length; i++) {
            List<String> listOfFiles = WordCounterEngine.readNewsFile("/home/faizaumatiya/" + keywords[i].toLowerCase() + ".txt");
            String str = listOfFiles.toString();
            HashMap<String, Integer> wordCountMap = new HashMap<>();

            for(int k =0;k<keywords.length;k++){
                Pattern pattern = Pattern.compile(keywords[k]);
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()) {
                    if(wordCountMap.containsKey(keywords[k])){
                    wordCountMap.put(keywords[k], wordCountMap.get(keywords[k])+1);
                    }
                    else{
                        wordCountMap.put(keywords[k],1);
                    }
                }
            }
            eachLine.add(i,wordCountMap);
        }
        HashMap<String,Integer> map = reduce(eachLine);
        for (int i = 0; i < keywords.length; i++) {
            if(map.get(keywords[i]) == null){
                System.out.println(keywords[i]+":"+ 0);
            }else{
            System.out.println(keywords[i]+":"+map.get(keywords[i]));
            }
        }
    }
    public HashMap<String,Integer> reduce(ArrayList<HashMap<String,Integer>> listOfMap){
        HashMap<String,Integer> finalMap = new HashMap<>();
        for(HashMap<String,Integer> map:listOfMap){
            map.forEach((key,value)-> finalMap.merge(key,value,(oldvalue,newvalue)->oldvalue+newvalue));
        }
        return finalMap;
    }
}




