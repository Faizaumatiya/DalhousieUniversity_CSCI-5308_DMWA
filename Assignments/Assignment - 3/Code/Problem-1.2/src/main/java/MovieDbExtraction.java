import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/* Reference:
   [1] https://medium.com/swlh/getting-json-data-from-a-restful-api-using-java-b327aafb3751
   [2] https://www.tutorialspoint.com/java/java_filewriter_class.htm
*/
public class MovieDbExtraction {
    public void movieDataExtractor() {
        URL url;
        {
            try {
                String[] keywords = {"Canada", "University", "Moncton", "Halifax", "Toronto",
                                    "Vancouver", "Alberta", "Niagara"};

                for (int i = 0; i < keywords.length; i++) {
                    url = new URL("http://www.omdbapi.com/?s="+keywords[i]+"&apiKey=c971bcbe");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    int httpresponsecode = httpURLConnection.getResponseCode();

                    if (httpresponsecode == 200) {
                        StringBuilder jsonInline = new StringBuilder();
                        Scanner scanner = new Scanner(url.openStream());
                        while (scanner.hasNext()) {
                            jsonInline.append(scanner.nextLine());
                        }
                        scanner.close();
                        JSONParser parse = new JSONParser();
                        JSONObject jsonObject = (JSONObject) parse.parse(String.valueOf(jsonInline));
                        JSONArray jsonArray = (JSONArray) jsonObject.get("Search");

                        File file = new File(keywords[i].toLowerCase() + ".txt");
                        file.createNewFile();
                        FileWriter fileWriter = new FileWriter(file);

                        fileWriter.write(jsonArray.toJSONString());
                        fileWriter.close();
                    } else {
                        throw new RuntimeException("HttpResponseCode: " + httpresponsecode);
                    }
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
