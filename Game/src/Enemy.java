// pärilus / inheritance
public class Enemy extends Character {
    double strength;

    public Enemy(World world) {
        super('z', world);
        this.strength = 4.4;
    }
}
