import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Bot {

    static String prefix = "!";
    private static Dotenv config = null;

    public Bot(Dotenv config) {
        Bot.config = config;
    }

    public static void main(String[] args) throws LoginException, IOException {


        config=Dotenv.configure().load();
        String token=config.get("TOKEN");

        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("!play to play Sokoban!"));
        builder.addEventListeners(new Commands());
        builder.build();
    }
}