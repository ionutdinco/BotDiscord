package events;

import rss.Feed;
import rss.FeedMessage;
import rss.RSSFeedParser;

import java.util.ArrayList;
import java.util.List;


/**
 * `FeedReader` class is responsible for managing all feeds
 */
public class FeedReader {

    /**
     * Feeds that has been readed
     */
    public static List<FeedMessage> oldEntries;
    /**
     * List of RSSFeedParser that contains the urls
     */
    private List<RSSFeedParser> parsers;

    /**
     * Constructor without parameters - initialize atributes
     */
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

    /**
     * Creates a object of type `Feed` and call method `readFeed` from `RSSFeedParse` with parameter
     * of `0` meanning not all messages, and we get only 1 new feed. It is added to result list, as
     * a string using method `toString` and added to `oldEntries` (marked as read)
     *
     * @return a List of String  that represent some feeds
     */
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


    /**
     * Try to add the url to the listened feeds and throws an exception if can't
     *
     * @param url a String that represent the url to the feed, that is get through a "GET" request to the url
     * @throws Exception - can't load url
     */
    public void addFeed(String url) throws Exception {
        parsers.add(new RSSFeedParser(url));
    }


    /**
     * Or parse all feeds and add them to `oldEntries`(mark as read)
     * Or get only a feed, and add to `oldEntries` olny that entire feed
     *
     * @param allFeeds type int, represent that we add to `oldEntries` all the feeds or not
     * @param index    type int, represent the position in `parsers` of feed
     */
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
        else if (allFeeds == 0) {
            feed = parsers.get(index).readFeed(1);
            if (feed != null) {
                if (!feed.getMessages().isEmpty()) {
                    oldEntries.addAll(feed.getMessages());
                }
            }
        }

    }


    /**
     * Remove a link from listened feeds by a string as general as possible,
     * and return what link has been removed
     *
     * @param s type String, `value`
     * @return returns the url's feed in succes case, and if not, the message
     */
    public String removeFeed(String s) {
        for (var parser : parsers) {
            if (parser.getUrl().contains(s)) {
                parsers.remove(parser);
                return "Feed with url : `" + parser.getUrl() + "` removed!";
            }
        }
        return "Feed url not found!";
    }
}
