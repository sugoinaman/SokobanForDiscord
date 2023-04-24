@@ -0,0 +1,98 @@
public class Player
{
    int x = 0;
    int y = 0;
    Grid currentGrid;
    public Player(int x, int y, Grid currentGrid)
    {
        this.x = x;
        this.y = y;
        this.currentGrid = currentGrid;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public boolean moveUp()
    {
        if (!currentGrid.isWall(x, y - 1))
        {
            if (currentGrid.isBox(x, y - 1))
            {
                if (currentGrid.getBox(x, y - 1).moveUp())
                {
                    y -= 1;
                    return true;
                }
                return false;
            }
            y -= 1;
            return true;
        }
        return false;
    }
    public boolean moveDown()
    {
        if (!currentGrid.isWall(x, y + 1))
        {
            if (currentGrid.isBox(x, y + 1))
            {
                if (currentGrid.getBox(x, y + 1).moveDown())
                {
                    y += 1;
                    return true;
                }
                return false;
            }
            y += 1;
            return true;
        }
        return false;
    }
    public boolean moveLeft()
    {
        if (!currentGrid.isWall(x - 1, y))
        {
            if (currentGrid.isBox(x - 1, y))
            {
                if (currentGrid.getBox(x - 1, y).moveLeft())
                {
                    x -= 1;
                    return true;
                }
                return false;
            }
            x -= 1;
            return true;
        }
        return false;
    }
    public boolean moveRight()
    {
        if (!currentGrid.isWall(x + 1, y))
        {
            if (currentGrid.isBox(x + 1, y))
            {
                if (currentGrid.getBox(x + 1, y).moveRight())
                {
                    x += 1;
                    return true;
                }
                return false;
            }
            x += 1;
            return true;
        }
        return false;
    }

}