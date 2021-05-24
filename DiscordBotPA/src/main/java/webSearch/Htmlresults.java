package webSearch;


import java.util.List;

/**
 *  Controller class, responsible for search engine control and parsing of results.
 */
public class Htmlresults {

    /**
     *Function to call the search object's methods and the html parser's methods, in order to obtein the output informations as a string.
     * Gets the results from the html pages with the most occurrences of the searched words.
     * @param querry type String.
     * @return the information found after the search(if there is any) as a String.
     */
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
                    output1 = formatList(pTag);
                } else if (aux > secod) {
                    secod = aux;
                    output2 = formatList(pTag);
                }

            }
            return output1 + " " + output2;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *Function that processes the output to look better on the Discord.
     * @param list type List<String>.
     */
    private String formatList(List<String> list){
        String result = "";
        for(var current: list){
            result += " ```json\n\"" + current + "\" ```";
        }
        return result;
    }
}
