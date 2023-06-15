import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import de.vandermeer.asciitable.AsciiTable;
import org.bson.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/* Reference:
    [1] https://www.java67.com/2016/07/how-to-read-text-file-into-arraylist-in-java.html
    [2] https://gist.github.com/mkulakowski2/4289441
    [3] https://gist.github.com/mkulakowski2/4289437
    [4] https://stackoverflow.com/questions/34695546/extract-multiple-fields-using-mongodb-3-2-0-java-driver
    [5] https://github.com/vdmeer/asciitable/blob/master/README.adoc
 */
public class SentimentAnalysis {

    /* [1] */
    public static List<String> readFile(String fileName) {
        List<String> readLines = Collections.emptyList();
        try {
            readLines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readLines;
    }

    /* [2] */
    public List<String> readNegativeWordsFile() {
        List<String> list = SentimentAnalysis.readFile("negative-words.txt");
        // System.out.println(list);
        return list;
    }

    /* [3]  */
    public List<String> readPositiveWordsFile() {
        List<String> list = SentimentAnalysis.readFile("positive-words.txt");
        // System.out.println(list);
        return list;
    }

    /* [4] */

    // fetch descritpiton from News Article uploaded on MongoDB
    public ArrayList<String> fetchNewsDataDescriptionFromMongoDB() {
        ArrayList<String> descriptionList = new ArrayList<>();
        ConnectionString connectionString = new ConnectionString("mongodb+srv://Faiza:jgxu90UQixANgK6V@cluster0.oe1yvip.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("myMongoNews");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("newsDataCollection");
        MongoCursor<Document> cursor = mongoCollection.find().iterator();
        while (cursor.hasNext()) {
            String str = cursor.next().get("description").toString();
            descriptionList.add(str);
        }
        return descriptionList;
    }

    public HashMap<Integer, List<String>> wordComparisonOfDescription() {
        List<String> positiveWords = readPositiveWordsFile();
        List<String> negativeWords = readNegativeWordsFile();

        HashMap<Integer, List<String>> totalListOfDescriptionWithPolarity = new HashMap<>();
        ArrayList<String> description = fetchNewsDataDescriptionFromMongoDB();
        ArrayList<String> cleanedDescription = new ArrayList<>();
        String removeSpecialCharacters = "[^a-zA-Z0-9-+]";
        String removeNumbers = "[013456789]";

        for (String list_element : description) {
            list_element = list_element.replaceAll(removeSpecialCharacters, " ");
            list_element = list_element.replaceAll(removeNumbers, " ");
            cleanedDescription.add(list_element);
        }
        Iterator<String> iterator = cleanedDescription.iterator();
        int articleNumber = 1;
        while (iterator.hasNext()) {
            HashMap<String, Integer> bagOfWords = new HashMap<>();
            String eachDescription = iterator.next();
            String tempDescription[] = eachDescription.split(" ");

            for (String word : tempDescription) {
                if (bagOfWords.containsKey(word)) {
                    bagOfWords.put(word, bagOfWords.get(word) + 1);
                } else {
                    bagOfWords.put(word, 1);
                }
            }
            int score = 0;
            String matchWord = " ";

            for (Map.Entry<String, Integer> entry : bagOfWords.entrySet()) {
                if (negativeWords.contains(entry.getKey())) {
                    matchWord = matchWord + "," + entry.getKey();
                    score = score - entry.getValue();
                } else if (positiveWords.contains(entry.getKey())) {
                    matchWord = matchWord + "," + entry.getKey();
                    score = score + entry.getValue();
                }
                else{
                    score += 0;
                }
            }
            // check polarity for the bag of words
            String polarity = null;
            if (score < 0) {
                polarity = "negative";
            } else if (score > 0) {
                polarity = "positive";
            } else {
                polarity = "neutral";
            }
            List<String> values = new ArrayList<>();
            values.add(eachDescription);
            values.add(matchWord);
            values.add(polarity);
            totalListOfDescriptionWithPolarity.put(articleNumber, values);
            articleNumber++;
        }
        return totalListOfDescriptionWithPolarity;
    }
    /* [5] */
    public void displayTable() {
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("News Arcticle", "Description","Match", "Polarity");
        asciiTable.addRule();
        HashMap<Integer, List<String>> result = wordComparisonOfDescription();
        for (Map.Entry<Integer, List<String>> mapEntry : result.entrySet())
        {
            Integer newsArticleNumber = mapEntry.getKey();
            List<String> valueList = mapEntry.getValue();
            String displayDescription = valueList.get(0);
            if (displayDescription.length() > 50) {
                displayDescription = displayDescription.substring(0, 50);
                displayDescription = displayDescription + "...";
            }
            String displayWord = valueList.get(1);
            String displayPolarity = valueList.get(2);
            asciiTable.addRow(newsArticleNumber, displayDescription, displayWord, displayPolarity);
            asciiTable.addRule();

        }
        String renderTable = asciiTable.render();

        //write table into file
        try {
            FileWriter fileWriter = new FileWriter("sentiment_table.txt");
            fileWriter.write(renderTable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(renderTable);
    }
}