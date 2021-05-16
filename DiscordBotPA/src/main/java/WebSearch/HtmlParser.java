package WebSearch;

import com.google.gson.annotations.SerializedName;
import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HtmlParser {

    public static List<String> parserForSearchingLinks(final String html) throws Exception {
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
            if(!elementReference.startsWith("https://www.youtube.com/")) {
                result.add(elementReference);
                System.out.println(elementReference);
            }
        }
        return result;
    }

    public static List<String> parserForSearchedElements(final String html){
        List<String> result = new ArrayList<String>();
        Document doc = Jsoup.parse(html);
        Elements results = doc.select("div > p");
        int contor=0;
        for (Element element : results) {
            contor++;
            result.add(element.text());
            if(contor==2)
                break;

        }
        return result;
    }

    public static List<String> parsePhrase(String input){
        try {
           List<String> stopwords = Files.readAllLines(Paths.get("D:\\BotDiscordPA\\BotDiscordPA\\DiscordBotPA\\src\\main\\java\\WebSearch\\engStopWords\\engStopWords.txt"));
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

    public static int containsWords(String inputString, List<String> words) {
         Trie trie = Trie.builder().onlyWholeWords().addKeywords(words).build();

        Collection<Emit> emits = trie.parseText(inputString);
        int nrOfApp=0;
        boolean found = true;
        for(String word : words) {
            boolean contains = Arrays.toString(emits.toArray()).contains(word);
            if (contains) {
                nrOfApp++;
            }
        }

        return nrOfApp;
    }
}
