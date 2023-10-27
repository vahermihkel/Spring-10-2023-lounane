package ee.vr.cardgame.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card {
    private String rank;
    private String suit;
    private int value;

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    public boolean isHigherThan(Card card) {
        return value > card.getValue();
    }

    public boolean isLowerThan(Card card) {
        return value < card.getValue();
    }

    public boolean isEqualThan(Card card) {
        return value == card.getValue();
    }
}