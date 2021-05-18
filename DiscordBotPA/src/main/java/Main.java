import rss.AutoFetchRSS;
import utils.MessageChecker;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.DiscordClientBuilder;

/**
 * Main Class
 *
 * @author Dinco Ionut
 * @author Paladi Claudiu
 * @version 1.0
 * @since 2021-05-18
 */

public class Main {
    public static String prefix = "~";

    public static void main(String[] args) {


        GatewayDiscordClient client = DiscordClientBuilder.create("ODM5NzQwNjQ0Mzg1MjI2NzUy.YJODaw.Mtuehr3AT2aCtEhO-kpkifrNzXY").build().login().block();

        MessageChecker messageChecker = new MessageChecker(prefix);

        AutoFetchRSS.messageChecker = messageChecker;
        AutoFetchRSS.client = client;
        AutoFetchRSS autoFetchRSS = new AutoFetchRSS();
        autoFetchRSS.setDaemon(true);
        autoFetchRSS.start();

        client.getEventDispatcher().on(MessageCreateEvent.class)
                .map(MessageCreateEvent::getMessage)
                .filter(message -> messageChecker.checkMessage(message.getContent()))
                .flatMap(Message::getChannel).flatMap(messageChannel -> messageChannel.createMessage(messageChecker.toString()))
                .subscribe();

        client.onDisconnect().block();

    }

}
