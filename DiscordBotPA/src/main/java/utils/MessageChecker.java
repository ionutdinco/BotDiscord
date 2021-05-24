package utils;

import events.Communication;
import events.FeedReader;
import webSearch.Htmlresults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * MessageChecker class is responsible with getting the request and
 * redirection to the right functions to get the result
 */
public class MessageChecker {
    /**
     * the prefix that the server response to
     */
    public String prefix;
    public List<String> result = null;
    public FeedReader feedReader = new FeedReader();
    Htmlresults results = new Htmlresults();


    /**
     * Constructor with given prefix for bot commands
     */
    public MessageChecker(String prefix) {
        this.prefix = prefix;
        result = new ArrayList<>();
    }

    /**
     * This method is responsible with parsing the command and return that the message is taken by the bot
     * @param message type String, representing the message from discord channel
     * @return type boolean, representing that the message has been taken by the bot or not
     */
    public boolean checkMessage(String message) {

        List<String> resultt = new ArrayList<>();
        String[] info = message.split("\"");
        String[] message1 = message.split("[\\s+?]");
        if (message1[0].equalsIgnoreCase(prefix + "add")) {
            Communication.addInfo(message1[1], info[1]);
            resultt.add("Done!");
            result = resultt;
            return true;
        }
        if (message1[0].equalsIgnoreCase(prefix + "news")) {
            resultt = feedReader.getNewFeed();
            int size = resultt.size();
            for (int i = 1; i < size; i++) {
                resultt.remove(1);
            }
            resultt.add("Done!");
            result = resultt;
            return true;
        }
        if (message1[0].equalsIgnoreCase(prefix + "readAllNews")) {


            feedReader.markAsRead(1, 0);

            resultt.add("Done!");
            result = resultt;
            return true;
        }
        if (message1[0].equalsIgnoreCase(prefix + "readAllNewsFrom")) {


            feedReader.markAsRead(0, Integer.parseInt(message1[1]));

            resultt.add("Done!");
            result = resultt;
            return true;
        }
        if (message1[0].equalsIgnoreCase(prefix + "addRss")) {
            try {
                feedReader.addFeed(message1[1]);
            } catch (Exception e) {
                resultt.add("Bad url for request!");
            }

            if (resultt.isEmpty())
                resultt.add("Done!");
            result = resultt;
            return true;
        }
        if (message1[0].equalsIgnoreCase(prefix + "removeRss")) {
            try {
                resultt.add(feedReader.removeFeed(message1[1]));
            } catch (Exception e) {
                resultt.add("Feed not found! Be more specific");
            }

            if (resultt.isEmpty())
                resultt.add("Done!");
            result = resultt;
            return true;
        }

        if (message1[0].equalsIgnoreCase(prefix)) {
            resultt = Communication.searchKey(message1);
            if (resultt.isEmpty()) {
                resultt = Collections.singletonList(this.results.executeSearch(message));
            } else {
                resultt.set(0, formatList(resultt));
                int size = resultt.size();
                if (size > 1) {
                    resultt.subList(1, size).clear();
                }
            }
            System.out.println(resultt);
            result = resultt;
            return true;
        }
        resultt.add("Unknown command!!!");
        result = resultt;
        return false;
    }

    public List<String> getResult() {
        return result;
    }


    @Override
    public String toString() {

        List<String> resultt = ResultParser.parse(result);
        String ans = "";
        for (var s : resultt) {
            ans += s + " ";
        }
        return ans;
    }

    /**
     * Formats a list of strings.
     * @param list type List<String>
     * @return String formatted
     */
    private String formatList(List<String> list) {
        String result = "";
        for (var current : list) {
            result += " ```fix\n" + current + " ```";
        }
        return result;
    }
}
