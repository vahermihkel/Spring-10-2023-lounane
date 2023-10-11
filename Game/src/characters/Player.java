package characters;


import items.Item;
import world.World;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character {
    private List<Item> items; // {xCoord: 2, yCoord: 1, symbol: 'x', lives: 0, items: [{name: "",.. }, {}]}

    public Player(World world) {
        super('x', world);
        this.lives = 10;
        this.items = new ArrayList<>();
    }

                    // @Sword31312ad
    public void addItem(Item item) {
        if (items.contains(item)) {
            item.increaseDurability();
//            items[i] = item; POLE VAJA, SEST UUENDATAKSE MÄLUKOHA JÄRG
        } else {
            // @Sword31312ad
            items.add(item);
        }
    }

    public void move(String input, World world) {
        switch (input) {
            case "w" -> {
                if (yCoordinate > 1) {
                    yCoordinate--;
                }
            }
            case "s" -> {
                if (yCoordinate < world.getHeight() - 2) {
                    yCoordinate++;
                }
            }
            case "a" -> {
                if (xCoordinate > 1) {
                    xCoordinate--;
                }
            }
            case "d" -> {
                if (xCoordinate < world.getWidth() - 2) {
                    xCoordinate++;
                }
            }
        }
    }
}
