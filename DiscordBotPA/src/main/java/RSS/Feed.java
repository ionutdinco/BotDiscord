package rss;

import events.FeedReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Feed class represent a whole feed, with header and all items
 */
public class Feed {

    final String title;
    final String link;
    final String description;
    final String language;
    final String copyright;
    final String pubDate;

    /**
     * entries represent all items
     */
    final List<FeedMessage> entries = new ArrayList<FeedMessage>();


    /**
     * parameterized constructor
     * @param title type String
     * @param link type String
     * @param description type String
     * @param language type String
     * @param copyright type String
     * @param pubDate type String
     */
    public Feed(String title, String link, String description, String language,
                String copyright, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
        this.pubDate = pubDate;
    }

    public Feed(String title, String link) {
        this.title = title;
        this.link = link;
        this.description = "";
        this.language = "";
        this.copyright = "";
        this.pubDate = "";
    }


    /**
     * Verifies if a feed message has been already read
     * @param feedMessage type FeedMessage, represent the actual message
     * @return true if has been read false if hasn't
     */
    public boolean feedExistMessage(FeedMessage feedMessage){
        if(FeedReader.oldEntries == null)
            return false;
        for(var feedMsg : FeedReader.oldEntries){
            if(feedMsg.title.equals(feedMessage.title) && feedMsg.description.equals(feedMessage.description) && feedMsg.link.equals(feedMessage.link) && feedMsg.author.equals(feedMessage.author) && feedMsg.guid.equals(feedMessage.guid)){
                return true;
            }
        }
        return false;
    }

    public void addOldEntries(FeedMessage feedMessage){
        FeedReader.oldEntries.add(feedMessage);
    }

    /**
     * getter
     * @return the list of messages from this feed
     */
    public List<FeedMessage> getMessages() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getPubDate() {
        return pubDate;
    }

    @Override
    public String toString() {
        return "Feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + "]";
    }

}
