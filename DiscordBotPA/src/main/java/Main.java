import Events.Communication;
import Events.RSSFeed;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import net.dv8tion.jda.api.JDA;
import discord4j.core.DiscordClientBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static JDA jda;
    public static String prefix = "~";
    public static String request;
    public static String response;
    public static List<String> result=null;


    public static boolean checkMessage(String message){

        result=new ArrayList<>();
        String[] message1 = message.split("[\\s+?]");
        if(message1[0].equalsIgnoreCase(prefix + "add")) {
            Communication.addInfo(message1[1],message1[2]);
            result.add("Done!");
            return true;
        }
        if(message1[0].equalsIgnoreCase(prefix)) {
            result = Communication.searchKey(message1);
            return true;
        }
        result.add("Unknow comment!!!");
        return false;
    }



    public static void main(String[] args) {

        //DiscordClientBuilder builder = new DiscordClientBuilder();

        GatewayDiscordClient client = DiscordClientBuilder.create("ODM5NzQwNjQ0Mzg1MjI2NzUy.YJODaw.Mtuehr3AT2aCtEhO-kpkifrNzXY").build().login().block();

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .map(MessageCreateEvent::getMessage)
                .filter(message -> checkMessage(message.getContent()))
                .flatMap(Message::getChannel)
                .flatMap(messageChannel -> messageChannel.createMessage(result.toString()))
                .subscribe();
        RSSFeed feed1 = new RSSFeed("https://www.reddit.com/r/java/.rss");
        client.onDisconnect().block();

    }

}
