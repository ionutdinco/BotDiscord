package WebSearch;


import javax.print.DocFlavor;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Htmlresults {
    private String result;
    private String output2;

    public String executeSearch(String querry) {
        SearchEngine s = new SearchEngine();
        String result = s.search(querry);
        List<String> links;
        String output1 = null;
        String output2 = null;
        int first = 0;
        int secod = 0;
        StringBuilder string = new StringBuilder();

        try {
            links = HtmlParser.parserForSearchingLinks(result);
            for (var link : links) {
                String res = s.searchOnline(link);
                List<String> pTag = HtmlParser.parserForSearchedElements(res);
                List<String> words = HtmlParser.parsePhrase(result);
                int aux = HtmlParser.containsWords(pTag.toString(), words);
                if (aux > first) {
                    first = aux;
                    output1 = pTag.toString();
                } else if (aux > secod) {
                    secod = aux;
                    output2 = pTag.toString();
                }

            }
            return output1 + " " + output2;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
