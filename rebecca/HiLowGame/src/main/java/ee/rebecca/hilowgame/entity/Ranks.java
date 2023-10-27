package ee.rebecca.hilowgame.entity;

import lombok.Getter;

@Getter
public enum Ranks {
    ACE(10, "ACE"),TWO(2,"TWO"), THREE(3,"THREE"), FOUR(4,"FOUR"),
    FIVE(5, "FIVE"), SIX(6,"SIX"), SEVEN(7,"SEVEN"), EIGHT(8,"EIGHT"),
    NINE(9,"NINE"), TEN(10, "TEN"), JACK(10, "JACK"), QUEEN(10, "QUEEN"), KING(10, "KING");

    private int value;
    private String name;

    Ranks(final int value, final String name){
        this.value = value;
        this.name = name;
    }

}
