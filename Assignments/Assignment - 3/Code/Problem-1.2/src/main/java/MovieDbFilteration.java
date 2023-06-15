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
   [2] https://www.tutorialspoint.com/java/java_filewriter_class.htm
   [3] https://cloud.mongodb.com/v2/62c0d998b2d2eb715e565b55#clusters/connect?clusterId=Cluster0
 */

public class MovieDbFilteration {
    public static List<String> readMovieFile(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    public void readAndFilterMovieData() throws IOException {
        final String stringFilterSeparator = "},{";
        final String STRING_FILTER_SEPARATOR_REPLACEMENT = "}@@@@@@@@@{";

        String[] keywords = {"Canada", "University", "Moncton", "Halifax", "Toronto",
                "Vancouver", "Alberta", "Niagara"};

        for (int i = 0; i < keywords.length; i++) {
            List<String> filteredMoviesList = new ArrayList<>();
            List<String> list = MovieDbFilteration.readMovieFile("C:\\Users\\AVuser\\IdeaProjects\\Problem-1.2\\" + keywords[i] + ".txt");
            String filterForURL = "(\"Poster\":(\"http.*?\"),)";

            for (String list_element : list) {

                list_element = list_element.replaceAll(filterForURL, "");
                list_element = list_element.replace(stringFilterSeparator, STRING_FILTER_SEPARATOR_REPLACEMENT);
                filteredMoviesList.add(list_element);
            }
            String listDisplay = filteredMoviesList.toString();
            String updatedList = listDisplay.substring(1, listDisplay.length() - 1);
            File file1 = new File("filtered_"+keywords[i].toLowerCase()+".txt");
            file1.createNewFile();

            FileWriter fileWriter = new FileWriter(file1);
            fileWriter.write(updatedList);
            fileWriter.close();
            uploadMovieDataToMongoDB(filteredMoviesList);
        }
    }
    public void uploadMovieDataToMongoDB(List<String> filteredMovieList) throws IOException {
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
        MongoDatabase mongoDatabase = mongoClient.getDatabase("myMongoMovieDb");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("moviesDataCollection");
        for (String movies : filteredMovieList) {
            String movies_string = movies.substring(1, movies.length() - 1);
            String[] array = movies_string.split(STRING_SEPARATOR);
            for (String a : array) {
                //System.out.println("json element" + a);
                Document document = Document.parse(a);
                mongoCollection.insertOne(document);
            }
        }
        mongoClient.close();
    }
}

