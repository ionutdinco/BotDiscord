package Events;

import RSS.Feed;
import RSS.FeedMessage;
import RSS.RSSFeedParser;

public class ReadTest {
    public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser(
                                          "https://www.vogella.com/article.rss");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);

        }

    }
}
