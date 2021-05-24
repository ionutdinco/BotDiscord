package webSearch;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 *Class for representing a search engine.
 */
public class SearchEngine {
    /**
     * Google crawler.
     */
    private final String userAgent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
    /**
     * The final Url for searching.
     */
    private String useQuerry;

    /**
     * Function to build the Url, using the user input phrase.
     * @param querry tipul String
     */
    public void searchQuerry(String querry) {
        String searchQuerry = URLEncoder.encode(querry.trim(), StandardCharsets.UTF_8);
        this.useQuerry = "https://www.google.com/search?q=" + searchQuerry + "&num=6";
    }

    /**
     * Function to estabilish a connection and search for an information on google search. Gets the html code as a stream.
     * @param querry type String
     */
    public String search(String querry) {
        URL url = null;
        try {
            searchQuerry(querry);
            url = new URL(this.useQuerry);
            URLConnection connection = url.openConnection();
            connection.addRequestProperty("User-Agent", this.userAgent);
            InputStream stream = connection.getInputStream();
            return getString(stream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Function to estabilish a connection and make a GET request to an url, in order to abtain the html code as a stream.
     * @param pageURL type String
     */
    public String searchOnline(String pageURL){

        String result;
        URL url = null;
        try {
            url = new URL(pageURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream stream = con.getInputStream();

            return getString(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
      return null;
    }

    /**
     * Function that gets a stream and returns the equivalent string.
     * @param stream type InputStream
     */
    private String getString(InputStream stream) throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = input.readLine()) != null) {
                output.append(line);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return output.toString();
    }
}
