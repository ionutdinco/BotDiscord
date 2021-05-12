package Utils;

import Events.Communication;
import Events.FeedReader;

import java.util.ArrayList;
import java.util.List;

public class MessageChecker {

    public String prefix;
    public String request;
    public String response;
    public List<String> result = null;
    public FeedReader feedReader = new FeedReader();


    public MessageChecker(String prefix) {
        this.prefix = prefix;
    }


    public boolean checkMessage(String message) {

        result = new ArrayList<>();
        String[] message1 = message.split("[\\s+?]");
        if (message1[0].equalsIgnoreCase(prefix + "add")) {
            Communication.addInfo(message1[1], message1[2]);
            result.add("Done!");
            return true;
        }
        if (message1[0].equalsIgnoreCase(prefix + "news")) {
            result = feedReader.getNewFeed();
            int size = result.size();
            for (int i = 1; i < size; i++) {
                result.remove(1);
            }
            result.add("Done!");
            return true;
        }
        if (message1[0].equalsIgnoreCase(prefix)) {
            result = Communication.searchKey(message1);
            return true;
        }
        result.add("Unknow comment!!!");
        return false;
    }

    public List<String> getResult() {
        return result;
    }

}
