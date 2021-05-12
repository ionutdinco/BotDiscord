package Events;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class PageReader {

    public static void main(String[] args) throws IOException {

        URL url = new URL("https://www.reddit.com/r/java.rss");

        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();


        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}
