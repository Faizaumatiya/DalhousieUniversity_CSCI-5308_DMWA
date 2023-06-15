import java.io.IOException;

public class MainMovieApi {
    public static void main(String[] args) throws IOException {
        MovieDbExtraction movieDbExtraction = new MovieDbExtraction();
        movieDbExtraction.movieDataExtractor();
        MovieDbFilteration movieDbFilteration = new MovieDbFilteration();
        movieDbFilteration.readAndFilterMovieData();
    }
}
