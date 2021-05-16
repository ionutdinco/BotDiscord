package Events;

import RSS.Feed;
import RSS.FeedMessage;
import RSS.RSSFeedParser;

import java.util.ArrayList;
import java.util.List;

public class FeedReader {

    static List<FeedMessage> oldEntries = new ArrayList<>();

    public List<String> getNewFeed() {
        int i = 1;
        RSSFeedParser parser = new RSSFeedParser(
//                                          "https://www.reddit.com/r/java.rss");
                "https://developer-tech.com/feed/", oldEntries);
//                                          "https:https://www.reddit.com/r/java.rss                                          "https://www.vogella.com/article.rss");
        Feed feed = parser.readFeed();
        System.out.println(feed);

        List<String> result = new ArrayList<>();

        for (FeedMessage message : feed.getMessages()) {
            result.add(message.toString());
        }

        return result;
    }
}