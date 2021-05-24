package rss;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import utils.MessageChecker;

import java.util.List;


/**
 * This thread auto-send news from 30-30 minutes
 */
public class AutoFetchRSS extends Thread {
    public static MessageChecker messageChecker;
    public static GatewayDiscordClient client;

    public AutoFetchRSS() {
    }

    @Override
    public void run() {
        try {
            JDABuilder builder = JDABuilder.createDefault("ODM5NzQwNjQ0Mzg1MjI2NzUy.YJODaw.Mtuehr3AT2aCtEhO-kpkifrNzXY");
            JDA jda = builder.build();
            jda.awaitReady();

            while (true) {

                try {
                    sleep(3600000/2);

                    List<TextChannel> channels = jda.getTextChannelsByName("general", true);
                    for (TextChannel ch : channels) {
                        messageChecker.checkMessage("~news");
                        if (!messageChecker.toString().equals("Done! "))
                            sendMessage(ch, messageChecker.toString());
                        else
                            sendMessage(ch, "```fix\n(AutoFetch) - Nothing new!\n```");

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    static void sendMessage(TextChannel ch, String msg) {
        ch.sendMessage(msg).queue();
    }
}
