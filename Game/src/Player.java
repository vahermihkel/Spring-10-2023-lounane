import java.util.ArrayList;
import java.util.List;

public class Player extends Character {
    int lives;
    List<Item> items; // {xCoord: 2, yCoord: 1, symbol: 'x', lives: 0, items: [{name: "",.. }, {}]}

    public Player(World world) {
        super('x', world);
        this.lives = 3;
        items = new ArrayList<>();
    }

                    // @Sword31312ad
    public void addItem(Item item) {
        if (items.contains(item)) {
            item.durability++;
//            System.out.println("INCREASED DURABILITY");
//            System.out.println(item);
//            System.out.println(items);
//            items[i] = item; POLE VAJA, SEST UUENDATAKSE MÄLUKOHA JÄRG
        } else {
            // @Sword31312ad
            items.add(item);
//            System.out.println("ADDED");
//            System.out.println(item);
//            System.out.println(items);
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
                if (yCoordinate < world.height - 2) {
                    yCoordinate++;
                }
            }
            case "a" -> {
                if (xCoordinate > 1) {
                    xCoordinate--;
                }
            }
            case "d" -> {
                if (xCoordinate < world.width - 2) {
                    xCoordinate++;
                }
            }
        }
    }
}
