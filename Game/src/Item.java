public class Item implements WorldObject {
    int xCoordinate;
    int yCoordinate;
    char symbol;
    ItemType name;
    double strength;
    int durability; // kasutuskord

    public Item(World world, ItemType name) {
        this.xCoordinate = generateRandomCoordinate(world.width);
        this.yCoordinate = generateRandomCoordinate(world.height);
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

    @Override
    public int generateRandomCoordinate(int dimension) {
        return (1+(int)(Math.random()*((dimension-2))));
    }
}
