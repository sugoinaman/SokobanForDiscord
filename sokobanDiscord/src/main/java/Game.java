import net.dv8tion.jda.api.entities.MessageChannel;
public class Game {
    boolean gameActive = false;
    public int level = 1;
    int width = 9;
    int height = 6;
    Grid grid = new Grid(width, height, level);
    public void newGame(MessageChannel channel)
    {
        if (!gameActive)
        {
            level = 1;
            width = 9;
            height = 6;
            grid = new Grid(width, height, level);

            gameActive = true;
            Commands.sendGameEmbed(channel, String.valueOf(level), grid.toString());
        }
    }
    public void run(MessageChannel channel, String userInput)
    {
        if (userInput.equals("stop") && gameActive)
        {
            channel.sendMessage("Thanks for playing!").queue();
            gameActive = false;
        }

        if (userInput.equals("play") && !gameActive)
        {
            newGame(channel);
        }
        else if (gameActive)
        {
            if (!grid.hasWon()) {

                String direction = userInput;
                if (direction.equals("up") || direction.equals("w"))
                {
                    grid.getPlayer().moveUp();
                } else if (direction.equals("down") || direction.equals("s"))
                {
                    grid.getPlayer().moveDown();
                } else if (direction.equals("left") || direction.equals("a"))
                {
                    grid.getPlayer().moveLeft();
                } else if (direction.equals("right") || direction.equals("d"))
                {
                    grid.getPlayer().moveRight();
                } else if (direction.equals("r"))
                {
                    grid.reset();
                }
                if (!grid.hasWon()) {
                    Commands.sendGameEmbed(channel, String.valueOf(level), grid.toString());
                }
            }
            if (grid.hasWon()) {
                level += 1;
                if (width < 13) {
                    width += 2;
                }
                if (height < 8)
                {
                    height += 1;
                }
                Commands.sendWinEmbed(channel, String.valueOf(level));
                grid = new Grid(width, height, level);
            }
        }
    }
}