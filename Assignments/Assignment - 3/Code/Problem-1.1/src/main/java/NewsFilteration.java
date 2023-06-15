import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Reference:
    [1] https://www.java67.com/2016/07/how-to-read-text-file-into-arraylist-in-java.html
    [2] https://www.javatpoint.com/how-to-remove-special-characters-from-string-in-java
    [3] https://www.tutorialspoint.com/java/java_filewriter_class.htm
    [4] https://cloud.mongodb.com/v2/62c0d998b2d2eb715e565b55#clusters/connect?clusterId=Cluster0
 */
public class NewsFilteration {
    public static List<String> readFile(String fileName) {
        List<String> readLines = Collections.emptyList();
        try {
            readLines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readLines;
    }
    public void readAndFilterNewsData() throws IOException {
        final String stringFilterSeparator = "},{";
        final String STRING_FILTER_SEPARATOR_REPLACEMENT = "}@@@@@@@@@{";

        String[] keywords = {"Canada", "University", "Dalhousie University", "Halifax", "Canada Education",
                            "Moncton", "Toronto", "oil", "inflation"};

        for (int i = 0; i < keywords.length; i++) {
            List<String> filteredNewsList = new ArrayList<>();
            List<String> list = NewsFilteration.readFile("C:\\Users\\AVuser\\IdeaProjects\\Problem-1.1\\" + keywords[i] + ".txt");

            String filterForURL = "(\"url\":(\"http.*?\"),)";
            String filterForAuthorURL = "(\"author\":(\"http.*?\"),)";
            String filterForURLToImage = "(\"urlToImage\":(\"http.*?\"|null),)";
            String filterWhenAuthorIsNull = "(\"author\":(\"|null),)";
            String filterWhenIdIsNull = "(,\"id\":(null))";
            String filterForSpecialCharacters = "[^A-Z0-9 a-z,.(')-_+:{}\"%$]|\\r\\n";
            String filterForTags = "(<[^>]*>)";

            for (String list_element : list) {
                list_element = list_element.replaceAll(filterForURL, "");
                list_element = list_element.replaceAll(filterForAuthorURL, "");
                list_element = list_element.replaceAll(filterForURLToImage, "");
                list_element = list_element.replaceAll(filterWhenAuthorIsNull, "");
                list_element = list_element.replaceAll(filterWhenIdIsNull, "");
                list_element = list_element.replaceAll(filterForSpecialCharacters, "");
                list_element = list_element.replaceAll(filterForTags , "");
                list_element = list_element.replace(stringFilterSeparator, STRING_FILTER_SEPARATOR_REPLACEMENT);

                //System.out.println("list element"+list_element);
                filteredNewsList.add(list_element);
            }
            String listDisplay = filteredNewsList.toString();
            String updatedList = listDisplay.substring(1, listDisplay.length() - 1);

            File file1 = new File("filtered_"+keywords[i].toLowerCase()+".txt");
            file1.createNewFile();
            FileWriter fileWriter = new FileWriter(file1);
            fileWriter.write(updatedList);
            fileWriter.close();

            uploadNewsDataToMongoDB(filteredNewsList);
        }
    }
    public void uploadNewsDataToMongoDB(List<String> filteredNewsList) throws IOException {
        final String STRING_SEPARATOR = "@@@@@@@@@";
        ConnectionString connectionString = new ConnectionString("mongodb+srv://Faiza:jgxu90UQixANgK6V@cluster0.oe1yvip.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        //MongoDatabase database = mongoClient.getDatabase("test");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("myMongoNews");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("newsDataCollection");
        for(String news:filteredNewsList){
            String news_str = news.substring(1,news.length()-1);
            String[] array = news_str.split(STRING_SEPARATOR);
            for(String a:array){
                //System.out.println("json element"+a);
                Document document = Document.parse(a);
                mongoCollection.insertOne(document);
            }
        }
        mongoClient.close();
    }
}

