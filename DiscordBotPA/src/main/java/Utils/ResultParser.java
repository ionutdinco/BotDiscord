package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for parsing the results from the rss feed update or search object before sending it on Discord.
 */
public class ResultParser {

    /**
     *Function that eliminates the tags from the xml document obtained from a rss feed.
     * @param result type List<String>
     * @return returns the information found formatted.
     */
    public static List<String> parse(List<String> result) {
        List<String> answere = new ArrayList<>();
        try {
            String[] resultSet = result.get(0).split(",");
            for (String crt : resultSet) {

                if (crt.contains("title=")) {
                    String curr = "```diff\n-Title\n";
                    String[] saVad = crt.split("title=");
                    curr += saVad[1];
                    curr += "```";
                    answere.add(curr);
                }
                if (crt.contains("description=")) {
                    String curr = "```css\n[Description]\n";
                    curr += crt.split("description=")[1];
                    curr += "```";
                    answere.add(curr);
                }
                if (crt.contains("link=")) {
                    String curr = "```ini\n[Link]\n```";
                    curr += crt.split("link=")[1];
                    answere.add(curr);
                }

            }

            if (answere.isEmpty()) {
                answere = result;
            }

            System.out.println(answere);
            result = answere;
            return result;
        } catch (Exception e) {
            answere.add("I don't know!");
            result = answere;
            return result;
        }

    }


}
