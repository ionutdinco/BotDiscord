package WebSearch;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SearchEngine {

    private final String userAgent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
    private String useQuerry;

    public void searchQuerry(String querry) {
        String searchQuerry = URLEncoder.encode(querry.trim(), StandardCharsets.UTF_8);
        this.useQuerry = "https://www.google.com/search?q=" + searchQuerry + "&num=6";
    }

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

    public String searchOnline(String pageURL){

        String result;
        URL url = null;
        try {
            url = new URL(pageURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status=con.getResponseCode();
//            if ( status == HttpURLConnection.HTTP_MOVED_TEMP
//                    || status == HttpURLConnection.HTTP_MOVED_PERM) {
//                String location = con.getHeaderField("Location");
//                URL newUrl = new URL(location);
//                con = (HttpURLConnection) newUrl.openConnection();
//            }

            InputStream stream = con.getInputStream();

            return getString(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
      return null;
    }


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
