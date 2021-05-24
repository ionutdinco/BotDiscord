package webSearch;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class responsabile with the parse of the results found after search.
 */
public class HtmlParser {

    /**
     * Function that recives an html code as a string and get the 'href' attributes from elemnts <a></a> .
     * @param html type String.
     * @return List<String>,returns a list of links to sites where desired information can be found.
     */
    public static List<String> parserForSearchingLinks(final String html) {
        List<String> result = new ArrayList<String>();
        Document doc = Jsoup.parse(html);
        Elements results = doc.select("a > h3");
        for (Element link : results) {
            Elements parent = link.parent().getAllElements();
            String elementReference = parent.attr("href");
            if (elementReference.startsWith("/url?q=")) {
                elementReference = elementReference.replace("/url?q=", "");
            }
            String[] splittedString = elementReference.split("&sa=");
            if (splittedString.length > 1) {
                elementReference = splittedString[0];
            }

            try {
                simulate(elementReference);

                if (!elementReference.startsWith("https://www.youtube.com/") &&
                        !elementReference.startsWith("https://ro-ro.facebook.com/") &&
                        !elementReference.startsWith("https://www.facebook.com/")) {
                    result.add(elementReference);
                    System.out.println(elementReference);
                }
            } catch (Exception e) {
                System.err.println("status code not good");
            }
        }
        return result;
    }

    /**
     *Function to verify if the status code received following a request is 2xx.
     *If the information can't be accessed it throws an error which is catched in the @parserForSearchingLinks function.
     * @param elementReference type String (url of the html page)
     * @throws IOException
     */
    private static void simulate(String elementReference) throws IOException {
        URL url = null;
        url = new URL(elementReference);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        InputStream stream = con.getInputStream();

    }

    public static List<String> parserForSearchedElements(final String html) {
        List<String> result = new ArrayList<String>();
        Document doc = Jsoup.parse(html);
        Elements results = doc.select("div > p");
        int contor = 0;
        for (Element element : results) {
            contor++;
            result.add(element.text());
            if (contor == 2)
                break;

        }
        return result;
    }

    /**
     * Function that eliminates the english stopwords from the input phrase given by user for a more complete keyword search..
     * @param input type String
     * @return List<String>
     */
    public static List<String> parsePhrase(String input) {
        try {
            List<String> stopwords = Files.readAllLines(Paths.get("src\\main\\java\\WebSearch\\engStopWords\\engStopWords.txt"));
            ArrayList<String> allWords =
                    Stream.of(input.toLowerCase().split(" "))
                            .collect(Collectors.toCollection(ArrayList<String>::new));
            allWords.removeAll(stopwords);

            return allWords;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     *Counts the number of apparitions of the keywords in the parsed information taken from google searching
     * @param inputString type String (html code)
     * @param words type List (keywords)
     * @return Number of occurrences.
     */
    public static int containsWords(String inputString, List<String> words) {
        Trie trie = Trie.builder().onlyWholeWords().addKeywords(words).build();

        Collection<Emit> emits = trie.parseText(inputString);
        int nrOfApp = 0;
        boolean found = true;
        for (String word : words) {
            boolean contains = Arrays.toString(emits.toArray()).contains(word);
            if (contains) {
                nrOfApp++;
            }
        }

        return nrOfApp;
    }
}
