public abstract class Character {
    int xCoordinate;
    int yCoordinate;
    char symbol;

    public Character(char symbol) {
        this.xCoordinate = 3; // Random number generator, et tekiks suvalise koha peal: KAARDIL
        this.yCoordinate = 3; // 1st Heightini-2
        this.symbol = symbol;
    }
}
