import Events.Communication;
import Events.FeedReader;
import Events.RSSFeed;
import RSS.FeedMessage;
import Utils.MessageChecker;
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



    public static void main(String[] args) {

//        DiscordClientBuilder builder = new DiscordClientBuilder();

        GatewayDiscordClient client = DiscordClientBuilder.create("ODM5NzQwNjQ0Mzg1MjI2NzUy.YJODaw.Mtuehr3AT2aCtEhO-kpkifrNzXY").build().login().block();

        MessageChecker messageChecker = new MessageChecker(prefix);

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .map(MessageCreateEvent::getMessage)
                .filter(message -> messageChecker.checkMessage(message.getContent()))
                .flatMap(Message::getChannel)
                .flatMap(messageChannel -> messageChannel.createMessage(messageChecker.getResult().toString()))
                .subscribe();

//        client.getEventDispatcher().on(MessageCreateEvent.class)
//                .map(MessageCreateEvent::getMessage)
//                .filter(message -> true)
//                .flatMap(Message::getChannel)
//                .flatMap(messageChannel -> messageChannel.createMessage("hai lasa-ma"))
//                .subscribe();



        client.onDisconnect().block();

    }

}
