public class Tile {

    //CSS box style
    final int GROUND = 0;
    final int WALL = 1;
    final int BOX = 2;
    final int DESTINATION = 3;
    final int PLAYER = 4;
    int color = 0;
    int status = 0;

    //Adding constructors
    public Tile(int status) {
        this.status = status;
    }

    public Tile(int status, int color) {
        this.status = status;
        this.color = color;
    }

    //Setters
    public void setStatus(int status) {
        this.status = status;
    }

    //can set color from here
    public void setStatus(int status, int color) {
        this.status = status;
        this.color = color;
    }

    //getters here
    public int getStatus() {
        return this.status;
    }
    //to String method


    @Override
    public String toString() {
        if(status==GROUND){
            return ":black_small_square"; //discord emoji
        }
        if(status==WALL){
            switch(color){
                case 0:
                    return ":red_square:";
                case 1:
                    return ":orange_square:";
                case 2:
                    return ":yellow_square:";
                case 3:
                    return ":green_square:";
                case 4:
                    return ":blue_square:";
                default:
                    return ":purple_square:";
            }
        }
        if (status == BOX)
        {
            return ":brown_square:";
        }
        if (status == DESTINATION)
        {
            return ":negative_squared_cross_mark:";
        }
        return ":weary";
    }
}
