import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/* References:
[1] https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
[2] https://github.com/yintaoxue/read-open-source-code/blob/master/lucene-4.7.2/src/org/apache/lucene/benchmark/utils/ExtractReuters.java
[3] https://cloud.mongodb.com/v2/62c0d998b2d2eb715e565b55#clusters/connect?clusterId=Cluster0

 */
public class ReutersRead {
    public void extractFileName(){
        String[] reuters = {"reut2-009", "reut2-014"};
        for (int i =0;i<reuters.length;i++){
            try {
                readReuterFiles(reuters[i]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void readReuterFiles(String filepath) throws IOException {
        File file = new File("C:\\Users\\AVuser\\IdeaProjects\\Problem-1.3\\" + filepath + ".sgm");
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder reuterString = new StringBuilder();
        String reuterLine;

        while ((reuterLine = br.readLine()) != null) {
            if (reuterLine.indexOf("</REUTERS") == -1) {
                reuterString.append(reuterLine).append(' ');
            }
            else {
                String title = null;
                Pattern pattern = Pattern.compile("<TITLE>(.*?)</TITLE>");
                Matcher matcher = pattern.matcher(reuterString);
                while (matcher.find()) {
                    if (matcher.group(1) != null) {
                        title = matcher.group(1);
                    }
                }
                String body = null;
                Pattern pattern1 = Pattern.compile("<BODY>(.*?)</BODY>");
                Matcher matcher1 = pattern1.matcher(reuterString);
                while (matcher1.find()) {
                    if (matcher1.group(1) != null) {
                        body=matcher1.group(1);
                    }
                }
                uploadReutersDataToMongoDB(title,body);
            }
        }
    }
    public void uploadReutersDataToMongoDB(String outputStringTitle, String outputStringBody){
        ConnectionString connectionString = new ConnectionString("mongodb+srv://Faiza:jgxu90UQixANgK6V@cluster0.oe1yvip.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
       // MongoDatabase database = mongoClient.getDatabase("test");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("ReuterDB");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("reuterDataCollection");
        Document document = new Document();
        HashMap<String, String> mapTitleAndBody=new HashMap<>();
        mapTitleAndBody.put(outputStringTitle, outputStringBody);
        document.append("Title", outputStringTitle).append("Text",outputStringBody);
        mongoCollection.insertOne(document);
        mongoClient.close();
    }
}

