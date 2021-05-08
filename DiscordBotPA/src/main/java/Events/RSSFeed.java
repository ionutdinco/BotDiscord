package Events;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.sql.RowSetInternal;
import javax.sql.rowset.WebRowSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

public class RSSFeed {


    public RSSFeed(String args) {


            try {
                URL feedUrl = new URL(args);
                URLConnection request = feedUrl.openConnection();
                request.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedUrl));

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                StringWriter writer = new StringWriter();
                new SyndFeedOutput().output(feed, writer);


                FileOutputStream fop = null;
                File file;
                                file = new File("D:/Java-PA/DiscordBotPA/src/main/resources/META-INF/input.xml");
                try {


                    fop = new FileOutputStream(file);

                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    String xmlString = writer.toString();
                    byte[] contentInBytes = xmlString.getBytes();

                    fop.write(contentInBytes);
                    fop.flush();
                    fop.close();
                    System.out.println("DoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fop != null) {
                            fop.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("DoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeDoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

                Document document = db.parse(file);


                NodeList nodeList = document.getElementsByTagName("item");



//                for(int x=0,size= nodeList.getLength(); x<size; x++) {
//                    System.out.println(nodeList.item(x).getAttributes().getNamedItem("title").getNodeValue());
//                }


            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("ERROR: " + ex.getMessage());
            }
        }


}
