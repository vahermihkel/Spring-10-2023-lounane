package ee.rebecca.hilowgame.service;

import ee.rebecca.hilowgame.entity.Card;
import ee.rebecca.hilowgame.entity.Deck;
import ee.rebecca.hilowgame.model.GuessResponse;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private Card card;
    private Deck deck;
    private int score;
    private long startTime;


    public GuessResponse userGuess(String guess) {
        Card nextCard = deck.dealCard();
        GuessResponse response = new GuessResponse();
        if (nextCard.getValue() < card.getValue() && guess.equals("lower") ||
                nextCard.getValue() == card.getValue() && guess.equals("equal") ||
                nextCard.getValue() > card.getValue() && guess.equals("higher")
        ) {
            score++;
            card = nextCard;
            response.setMessage("Correct");
            response.setScore(score);
            response.setCard(card);
            return response;
        } else {
            card = nextCard;
            response.setMessage("Incorrect");
            response.setScore(score);
            response.setCard(card);
            return response;
        }
    }

    public Card startGame() {
        startTime = System.currentTimeMillis();
        deck = new Deck();
        deck.shuffle();
        card = deck.dealCard();
        return card;
    }

    public GuessResponse checkIfTimeout() {
        GuessResponse response = new GuessResponse();
        long guessTime = System.currentTimeMillis();
        if (guessTime > startTime + 10000) {
            response.setMessage("TIME_OUT");
            response.setCard(card);
            response.setScore(score);
        }
        startTime = guessTime;
        return response;
    }
}
