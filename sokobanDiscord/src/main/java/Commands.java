import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;


public class Commands extends ListenerAdapter {
    HashMap<User, Game> games = new HashMap<User, Game>();
    ArrayList<String> commands = new ArrayList<String>(Arrays.asList("w", "a", "s", "d", "up", "left", "down", "right", "r", Bot.prefix + "play", Bot.prefix + "continue", Bot.prefix + "stop"));
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        if (event.getAuthor().isBot() && Objects.requireNonNull(event.getMessage().getEmbeds().get(0).getTitle()).charAt(0) == 'L')
        {
            event.getMessage().addReaction("U+2B05").queue();
            event.getMessage().addReaction("U+27A1").queue();
            event.getMessage().addReaction("U+2B06").queue();
            event.getMessage().addReaction("U+2B07").queue();
            event.getMessage().addReaction("U+1F504").queue();
            return;
        }
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].toLowerCase().equals(Bot.prefix + "info"))
        {
            event.getChannel().sendMessage(info().build()).queue();
        }
        else if (commands.contains(args[0].toLowerCase()))
        {
            if (!games.containsKey(event.getAuthor()))
            {
                games.put(event.getAuthor(), new Game());
            }
            String userInput = args[0].toLowerCase();
            if (Character.toString(userInput.charAt(0)).equals(Bot.prefix))
            {
                userInput = userInput.substring(1);
            }
            games.get(event.getAuthor()).run(event.getChannel(), userInput);
        }
    }
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        if (event.getMember().getUser().isBot())
        {
            return;
        }
        if (!games.containsKey(event.getMember().getUser()))
        {
            games.put(event.getMember().getUser(), new Game());
        }
        String userInput = "";
        switch (event.getReactionEmote().toString())
        {
            case "RE:U+2b05":
                userInput = "left";
                break;
            case "RE:U+27a1":
                userInput = "right";
                break;
            case "RE:U+2b06":
                userInput = "up";
                break;
            case "RE:U+2b07":
                userInput = "down";
                break;
            case "RE:U+1f504":
                userInput = "r";
                break;
        }
        games.get(event.getMember().getUser()).run(event.getChannel(), userInput);
    }
    EmbedBuilder info()
    {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("Sokobot");
        info.setThumbnail("https://imgur.com/t/cats/Fr3UQ8J");
        info.setDescription("Sokoban is a pushing a box game");
        info.setColor(0x000080);
        info.addField("How to Play", "Move the :sob:.\nYour job is to push **boxes** :brown_square: on top of their **destinations** :smiling_face_with_tear:.", false);
        info.addField("Commands", ("``" + Bot.prefix + "play`` can be used to start a game if you are not currently in one.\n``" + Bot.prefix + "stop`` can be used to stop your active game at any time.\n``" + Bot.prefix + "info`` provides some useful details about the bot."), false);
        info.addField("Source code", "https://github.com/sugoinamana", false);
        info.setFooter("Recreated by sugoi", "https://imgur.com/t/doom_slayer/3quMI5q");
        return info;
    }
    public static void sendGameEmbed(MessageChannel channel, String level, String game)
    {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Level " + level);
        embed.setDescription(game);
        embed.setFooter("Enter direction (up, down, left, right, or wasd) or r to reset");
        channel.sendMessage(embed.build()).queue();
    }
    public static void sendWinEmbed(MessageChannel channel, String level)
    {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("You win!");
        embed.setDescription("Type ``" + Bot.prefix + "continue`` to continue to Level " + level + " or ``" + Bot.prefix + "stop`` to quit ");
        channel.sendMessage(embed.build()).queue();
    }
}