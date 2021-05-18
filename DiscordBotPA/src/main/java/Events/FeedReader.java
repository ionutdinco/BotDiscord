package Events;

import RSS.Feed;
import RSS.FeedMessage;
import RSS.RSSFeedParser;

import java.util.ArrayList;
import java.util.List;

public class FeedReader {

    public static List<FeedMessage> oldEntries;
    private List<RSSFeedParser> parsers;

    public FeedReader() {
        parsers = new ArrayList<>();
        oldEntries = new ArrayList<>();
        parsers.add(new RSSFeedParser(
                        "https://gadgets.ndtv.com/rss.xml")
                //"https://developer-tech.com/feed/", oldEntries);
//                                          "https:https://www.reddit.com/r/java.rss"
//                                          "https://www.vogella.com/article.rss");
        );
    }

    public List<String> getNewFeed() {


        List<String> result = new ArrayList<>();
        Feed feed;

        for (var currentFeedParser : parsers) {

            feed = currentFeedParser.readFeed(0);
            if (feed != null)
                if (!feed.getMessages().isEmpty()) {
                    for (FeedMessage message : feed.getMessages()) {
                        result.add(message.toString());
                        oldEntries.add(message);
                    }
                    break;
                }
        }


        return result;
    }

    public void addFeed(String url) throws Exception {
        parsers.add(new RSSFeedParser(url));
    }

    public void markAsRead(int allFeeds, int index) {
        Feed feed;

        if (allFeeds == 1)
            for (var currentFeedParser : parsers) {
                feed = currentFeedParser.readFeed(1);
                if (feed != null) {
                    if (!feed.getMessages().isEmpty()) {
                        oldEntries.addAll(feed.getMessages());
                    }
                }
            }
        else if(allFeeds == 0){
            feed = parsers.get(index).readFeed(1);
            if (feed != null) {
                if (!feed.getMessages().isEmpty()) {
                    oldEntries.addAll(feed.getMessages());
                }
            }
        }

    }


    public String removeFeed(String s) {
        for(var parser: parsers){
            if(parser.getUrl().contains(s)) {
                parsers.remove(parser);
                return "Feed with url : `" + parser.getUrl() + "` removed!";
            }
        }
        return "Feed url not found!";
    }
}


//    RSSFeedParser parser = parsers.get(0);
//    Feed feed = parser.readFeed();
//
//    List<String> result = new ArrayList<>();
//
//        for (FeedMessage message : feed.getMessages()) {
//                result.add(message.toString());
//                }