package characters;

import interfaces.WorldObject;
import world.World;

public abstract class Character implements WorldObject {
    protected int xCoordinate;
    protected int yCoordinate;
    protected char symbol;
    protected double lives;

    public Character(char symbol, World world) {
        this.xCoordinate = generateRandomCoordinate(world.getWidth());
        this.yCoordinate = generateRandomCoordinate(world.getHeight());
        this.symbol = symbol;
//        world.width = 99; nüüd ei saa, enne kui oli public siis oleks saanud
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public char getSymbol() {
        return symbol;
    }

    public double getLives() {
        return lives;
    }

    public void setLives(double lives) throws Exception {}
//    public abstract void setLives(double lives);

    @Override
    public int generateRandomCoordinate(int dimension) {
        return (1+(int)(Math.random()*((dimension-2))));
    }
}
