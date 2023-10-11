public abstract class Character implements WorldObject {
    int xCoordinate;
    int yCoordinate;
    char symbol;

    public Character(char symbol, World world) {
        this.xCoordinate = generateRandomCoordinate(world.width);
        this.yCoordinate = generateRandomCoordinate(world.height);
        this.symbol = symbol;
    }

    @Override
    public int generateRandomCoordinate(int dimension) {
        return (1+(int)(Math.random()*((dimension-2))));
    }
}
