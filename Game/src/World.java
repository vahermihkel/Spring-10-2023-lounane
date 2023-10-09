import java.util.List;

public class World {
    int height = 5;
    int width = 10;

    public void printMap(List<Character> characters) {
        char symbol;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // if (hasRights() || isAdult) <--- liiga palju tööd võib-olla
                // if (isAdult || hasRights())
                // if (hasRights() || hasAllOk()) <--- tõesem enne
                if (y == 0 || y == height -1) {
                    symbol = '-';
                } else if (x == 0 || x == width -1) {
                    symbol = '|';
                } else {
                    symbol = ' ';
                }
                // if (hasRights() || isAdult) <--- liiga palju tööd võib-olla
                // if (isAdult || hasRights())
                // if (hasAllOk() && hasRights()) <--- vääram enne
//                if (playerXCoordinate == x && playerYCoordinate == y) {
//                    symbol = playerSymbol;
//                }
//                if (enemyXCoordinate == x && enemyYCoordinate == y) {
//                    symbol = enemySymbol;
//                }
                for (Character c: characters) {
                    if (c.xCoordinate == x && c.yCoordinate == y) {
                        symbol = c.symbol;
                        break;
                    }
                }
//                for (int i = 0; i < characters.size(); i++) {
//                    characters.get(i)
//                }
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
