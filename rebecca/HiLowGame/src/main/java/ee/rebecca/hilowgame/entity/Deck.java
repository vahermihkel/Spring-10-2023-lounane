package ee.rebecca.hilowgame.entity;

public class Deck {

    //          List<Card>
    private Card[] deck;
    private int cardsUsed;


    public Deck(){
        deck = makeDeck();
    }

    private Card[] makeDeck() {
        final Card[] deck;
        deck = new Card[52];
        int i = 0;
        for (Suits suit : Suits.values()){
            for (Ranks rank: Ranks.values()){
                    deck[i] = new Card(rank, suit);
                    i++;
            }
        }
        cardsUsed = 0;
        return deck;
    }

    //SHUFFLE



    public void shuffle(){
        Card tempDeck;
        for (int i = 0; i < deck.length; i++) {
            int index = (int) (Math.random() * deck.length);

            tempDeck = deck[i];
            deck[i] = deck[index];
            deck[index] = tempDeck;
        }
//        List<Card> shuffledDeck = new ArrayList<>(List.of(deck));
//        Collections.shuffle(shuffledDeck);
    }

    public Card dealCard(){
        if (cardsUsed == 51) {
            deck = makeDeck();
        }
        return deck[cardsUsed++];
    }
}
