public class Position {
    private int x;
    private int y;

    /**
     * Skapar en position
     * @param x X-position (1-5)
     * @param y Y-position (1-10)
     */
    public Position(int x, int y) throws IllegalArgumentException{
        if(x < 1 || x > 5){
            throw new IllegalArgumentException("Invalid position: x");
        }
        else if(y < 1 || y > 10){
            throw new IllegalArgumentException("Invalid position: y");
        }

        this.x = x;
        this.y = y;
    }

    /**
     * Minskar x-position med 1
     */
    public void moveLeft(){
        if(x != 1)
            x -= 1;
    }

    /**
     * Ökar x-position med 1
     */
    public void moveRight(){
        if(x != 5)
            x += 1;
    }

    /**
     * Ökar y-position med 1
     */
    public void moveDown(){
        if(y != 10)
            y += 1;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
