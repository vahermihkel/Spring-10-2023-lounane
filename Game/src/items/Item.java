package items;

import characters.Character;
import interfaces.Fight;
import interfaces.WorldObject;
import world.World;

public class Item implements WorldObject, Fight {
    private int xCoordinate;
    private int yCoordinate;
    private char symbol;
    private ItemType name;
    private double strength;
    private int durability; // kasutuskord

    public Item(World world, ItemType name) {
        this.xCoordinate = generateRandomCoordinate(world.getWidth());
        this.yCoordinate = generateRandomCoordinate(world.getHeight());
        this.symbol = 'I';
        this.name = name;
        switch (name) {
            case SWORD -> {
                this.strength = 5.0;
                this.durability = 1;
            }
            case HAMMER -> {
                this.strength = 3.0;
                this.durability = 3;
            }
            case DAGGER -> {
                this.strength = 1.0;
                this.durability = 5;
            }
        }
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

    public void increaseDurability() {
        this.durability++;
    }

    @Override
    public int generateRandomCoordinate(int dimension) {
        return (1+(int)(Math.random()*((dimension-2))));
    }

    @Override
    public void hit(Character character) throws Exception {
        // Saadetakse characters.Enemy sisse
//         character.lives = character.getLives() - this.strength;
        character.setLives(character.getLives() - this.strength);
    }
}
