package characters;

import interfaces.Fight;
import world.World;

// pärilus / inheritance
public class Enemy extends Character implements Fight {
    private double strength;

    public Enemy(World world) {
        super('z', world);
        this.strength = 4.4;
        this.lives = 5;
    }

    @Override
    public void hit(Character character) {
        // Saadetakse characters.Player sisse
        character.lives = character.lives - this.strength;
    }

    // polymorphism
    @Override
    public void setLives(double lives) throws Exception {
        if (lives > 10.0) {
            throw new Exception("Viga! Elusid ei saa nii palju olla!!");
        }
        this.lives = lives;
    }
}
