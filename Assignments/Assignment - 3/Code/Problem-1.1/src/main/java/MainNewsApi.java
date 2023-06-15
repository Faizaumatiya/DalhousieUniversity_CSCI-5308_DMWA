import org.json.simple.parser.ParseException;

import java.io.IOException;

public class MainNewsApi
{
    public static void main(String[] args) throws IOException, ParseException {
        NewsExtraction newsExtraction = new NewsExtraction();
        newsExtraction.NewsExtractor();
        NewsFilteration newsFilteration = new NewsFilteration();
        newsFilteration.readAndFilterNewsData();
    }
}

