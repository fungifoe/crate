package com.crategame.src;

public class Position {

    private int x;
    private int y;

    private int s = Game.SCALE;
    private int maxY = 320 - (320/11);
    private int maxX = 160 - (160/5);

    /**
     * Skapar en position
     * @param x X-position (1-5)
     * @param y Y-position (1-10)
     */
    public Position(int x, int y) throws IllegalArgumentException{
        if(x < 1 || x > 5){
            throw new IllegalArgumentException("Invalid position: x");
        }
        else if(y < 0 || y > 11){
            throw new IllegalArgumentException("Invalid position: y");
        }

        this.x = (x-1) * s;
        this.y = (y-1) * s;
    }

    /**
     * Minskar x-position med 1
     */
    public void moveLeft(){
        if(x != 0)
            x -= s;
    }

    /**
     * Ökar x-position med 1
     */
    public void moveRight(){
        if(x != maxX)
            x += s;
    }

    /**
     * Ökar y-position med 1
     */
    public void moveDown(){
        if(y != maxY)
            y += s;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}