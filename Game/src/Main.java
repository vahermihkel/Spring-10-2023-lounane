import characters.Character;
import characters.Enemy;
import characters.Player;
import items.Item;
import items.ItemType;
import world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        World world = new World();


        // {xCoordinate: 2, yCoordinate: 2, symbol: 'x'}
        Player player = new Player(world);

        // Primitiivid:

//        double komakohaga;
        // float komakohaga2; <-- lühem, mälu säästmiseks
        // long andmebaasiId; <--
        // int kõikeTavalisem; 2.1miljardit
        // short väike; 32 000
        // byte pilte/0/1
//        boolean kahendväärtus;
//        String sõna;
        // KLass:
//        String name = "";
//        Integer playerX = 2;

        Enemy enemy = new Enemy(world);

        List<Character> characters = new ArrayList<>();
        characters.add(player);
        characters.add(enemy);

        // @Sword31312ad
        Item sword = new Item(world, ItemType.SWORD);
        Item hammer = new Item(world, ItemType.HAMMER);
        Item dagger = new Item(world, ItemType.DAGGER);
        List<Item> items = new ArrayList<>();
        items.add(sword);
        items.add(hammer);
        items.add(dagger);

//        characters.Character character = new characters.Character(); ei saa kui on abstract


        // boolean isAdult;  <--- deklareerin
        // isAdult = true; <--- initsialiseerin
        // boolean isAdult = true; deklareerin ja initsialiseerin;
        // hasRights() <--- andmebaasi küsima      TÕENÄOLISEMALT TRUE
        // hasAllOk() <--- küsib välisest rakendusest

        Scanner scanner = new Scanner(System.in);
        // ObjectMapper objectMapper = new ObjectMapper();
        // RestTemplate restTemplate = new RestTemplate();
        // items.Item sword = new items.Item();
        // items.Item hammer = new items.Item();
        // characters.Character player = new characters.Player();
        // characters.Character characters.Enemy = new characters.Enemy();

        world.printMap(characters, items);

        String input = scanner.nextLine();
//        System.out.println(input);
        // 's' == 's'
        // primitiive võrdleme võrdusmärkidega
        // klasse võrdleme .equals abil
        while (!input.equals("end")) {
            player.move(input, world);

            world.printMap(characters, items);

            for (Item i: items) {
                if (i.getxCoordinate() == player.getxCoordinate() &&
                    i.getyCoordinate() == player.getyCoordinate()) {
                    player.addItem(i);
                }
            }

//            sword.hit(enemy);
//            enemy.hit(player);

            input = scanner.nextLine();
        }
    }


}
