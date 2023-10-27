package ee.rebecca.hilowgame.entity;

public class Card {
    private String suit;
    private String rank_name;
    private int rank_value;

    public Card (Ranks rank, Suits suit) {
        this.suit = suit.getSuit();
        rank_name = rank.getName();
        rank_value = rank.getValue();
    }

    @Override
    public String toString(){
        return String.format(rank_name + " of " + suit);
    }
    public int getValue(){
        return rank_value;
    }

//    public static void main(String[] args) {
//        String[] suit = {"CLUBS", "DIAMONDS", "HEARTS", "SPADES"};
//        String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9",
//                "10", "JACK", "QUEEN", "KING", "ACE"};
//        String[] deck = new String[52];
//
//
////    String cardName = ranks[this.rank] + "of" + suits[this.suit]
//
////Building deck of cards
//        for (int i = 0; i < deck.length; i++) {
//            deck[i] = rank[i % 13] + suit[i / 13];
//        }
//
////    Shuffle deck to random order
//        String tempDeck = null;
//        for (int i = 0; i < deck.length; i++) {
//            int index = (int) (Math.random() * deck.length);
//
//            tempDeck = deck[i];
//            deck[i] = deck[index];
//            deck[index] = tempDeck;
//        }
//
//
//    }
}
