import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import de.vandermeer.asciitable.AsciiTable;
import org.bson.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 /*
    References:
    [1] https://stackoverflow.com/questions/34695546/extract-multiple-fields-using-mongodb-3-2-0-java-driver
    [2] https://github.com/vdmeer/asciitable/blob/master/README.adoc
 */

public class SemanticAnalysis {
    /* [1] */
    // fetch description from MongoDB
    int countWherePeopleAppeared =0;
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

    //Compute TF-IDF
    // Using search query, checking in how many documents "people", "description",
    // and "weather" have apperared.
    public void computeTfIdf() {

        int countOfWordWeather = 0;
        int countOfWordPeople = 0;
        int countOfWordCondition = 0;
        ArrayList<String> description = fetchNewsDataDescriptionFromMongoDB();
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("Total documents", description.size(), " ", " ");
        asciiTable.addRule();
        asciiTable.addRow("Search Query", "Document Containing term df", "Total Documents(N)/ number of documents term appeared (df)", "Log10(N/df)");
        asciiTable.addRule();
        for (String descriptionLine : description) {
            if (descriptionLine.contains("weather")) {
                countOfWordWeather++;
            }
        }
        asciiTable.addRow("weather", countOfWordWeather, description.size() + "/" + countOfWordWeather, Math.log10(description.size() / countOfWordWeather));
        asciiTable.addRule();
        for (String descriptionLine : description) {
            if (descriptionLine.contains("people")) {
                countOfWordPeople++;
            }
        }
        asciiTable.addRow("people", countOfWordPeople, description.size() + "/" + countOfWordPeople, Math.log10(description.size() / countOfWordPeople));
        asciiTable.addRule();
        for (String descriptionLine : description) {
            if (descriptionLine.contains("condition")) {
                countOfWordCondition++;
            }
        }
        asciiTable.addRow("condition", countOfWordCondition, description.size() + "/" + countOfWordCondition, Math.log10(description.size() / countOfWordCondition));
        asciiTable.addRule();

        String renderTable = asciiTable.render(150);
        System.out.println(renderTable);
        try {
            FileWriter fileWriter = new FileWriter("TF-IDF-table.txt");
            fileWriter.write(renderTable);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // method to find which document has the highest occurence of the word "people" and compute highest relative frequency
    public HashMap<Integer, List<Integer>> computeTermFrequency() {
        double highestFrequency = Double.MIN_VALUE;
        int hightestFrequencyArticleNumber = 0;

        ArrayList<String> description = fetchNewsDataDescriptionFromMongoDB();
        Iterator<String> iterator = description.iterator();
        HashMap<Integer, List<Integer>> searchWords = new HashMap<>();
        int i = 1;
        while (iterator.hasNext()) {
            String eachDescription = iterator.next();
            String eachDescriptionLine= eachDescription;
            Pattern pattern = Pattern.compile("people");
            Matcher matcher = pattern.matcher(eachDescriptionLine);
            String tempDescription[] = eachDescriptionLine.split(" ");

            int totalWords;
            if(tempDescription.length == 0){
                totalWords =1;
            }else{
                totalWords = tempDescription.length;
            }

            int frequency = 0;
            while (matcher.find()) {
                 frequency++;
            }

            if(frequency >= 1){
                countWherePeopleAppeared++;
                List<Integer> list = new ArrayList<>();
                list.add(totalWords);
                list.add(frequency);
                searchWords.put(i,list);
            }

            double eachRelativeFrequency = (double) frequency/totalWords;
            if(eachRelativeFrequency>highestFrequency){
                highestFrequency = eachRelativeFrequency;
                hightestFrequencyArticleNumber = i;
            }
            i++;
        }
        displayHighestRelativeFrequency(highestFrequency,hightestFrequencyArticleNumber);
        return searchWords;
    }
    public void displayTable(HashMap<Integer, List<Integer>> result){
        /* [2] */
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("Term", "People", " ");
        asciiTable.addRule();
        asciiTable.addRow("People appeared in "+countWherePeopleAppeared+ " documents","Total words (m)","Frequency (f)");

        for (Map.Entry<Integer, List<Integer>> mapEntry : result.entrySet()) {
            int articleNumber = mapEntry.getKey();
            String newsArticleNumber = "News Arcticle#"+articleNumber;
            List<Integer> list = mapEntry.getValue();
            int totalWords = list.get(0);
            int frequency = list.get(1);
            asciiTable.addRule();
            asciiTable.addRow(newsArticleNumber,totalWords,frequency);
        }
        asciiTable.addRule();
        String renderTable = asciiTable.render();
        System.out.println(renderTable);
        try {
            FileWriter fileWriter = new FileWriter("Term-Frequency-table.txt");
            fileWriter.write(renderTable);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void displayHighestRelativeFrequency(double highestFrequency, int highestFrequencyArticleNumber) {
        AsciiTable asciiTable = new AsciiTable();
        asciiTable.addRule();
        asciiTable.addRow("News Arcticle#", "highest frequency");
        asciiTable.addRule();
        asciiTable.addRow(highestFrequencyArticleNumber,highestFrequency);
        asciiTable.addRule();
        String renderTable = asciiTable.render();
        System.out.println(renderTable);
        try {
            FileWriter fileWriter = new FileWriter("Highest-Relative-Frequency-table.txt");
            fileWriter.write(renderTable);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

