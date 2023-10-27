package ee.vr.cardgame.service;

import ee.vr.cardgame.entity.Card;
import ee.vr.cardgame.entity.GuessType;
import ee.vr.cardgame.model.GuessResponse;
import lombok.Getter;

import java.util.*;

public class GameService {
    List<Card> deck = new ArrayList<>();
    private Card baseCard;

    private long startTime;

    public GameService() {
        makeADeck();
        shuffleDeck();
        startDrawing();
    }

    public Card getBaseCard() {
        startTime = System.currentTimeMillis();
        return baseCard;
    }

    private int score = 0;

    public GuessResponse userGuess(GuessType guess) {

        Card nextCard = drawCard();

        if (nextCard.isHigherThan(baseCard) && GuessType.HIGHER.equals(guess)) {
            return markAsCorrectAnswer(nextCard);
        } else if (nextCard.isLowerThan(baseCard) && GuessType.LOWER.equals(guess)) {
            return markAsCorrectAnswer(nextCard);
        } else if (nextCard.isEqualThan(baseCard) && GuessType.EQUAL.equals(guess)) {
            return markAsCorrectAnswer(nextCard);
        } else {
            return markAsIncorrectAnswer(nextCard);
        }
    }

    private GuessResponse markAsCorrectAnswer(Card card) {
        GuessResponse response = new GuessResponse();
        baseCard = card;
        score++;
        // {message: "Correct", score: 2, card: {rank:2,suit:H,value:2}}
        // if (response.split("Score")[0])
        // response.message
        // response.score
        // response.card
        response.setMessage("Correct");
        response.setScore(score);
        response.setCard(baseCard);
        return response;
    }

    private GuessResponse markAsIncorrectAnswer(Card card) {
        GuessResponse response = new GuessResponse();
        baseCard = card;
        response.setMessage("Incorrect");
        response.setScore(score);
        response.setCard(baseCard);
        return response;
    }

    private void makeADeck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        for (String rank : ranks) {
            for (String suit : suits) {
                int value;
                if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King") || rank.equals("Ace")) {
                    value = 10;
                } else {
                    value = Integer.parseInt(rank);
                }
                deck.add(new Card(rank, suit, value));
            }
        }
    }

    public void startDrawing() {
        baseCard = drawCard();
    }

    public Card drawCard() {
        if (!deck.isEmpty()) {
            return deck.remove(0);
        } else {
            makeADeck();
        }
        return baseCard;
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public GuessResponse checkIfTimeout() {
        GuessResponse response = new GuessResponse();
        long guessTime = System.currentTimeMillis();
        if (guessTime > startTime + 10000) {
            response.setMessage("TIME_OUT");
            response.setCard(baseCard);
            response.setScore(score);
        }
        startTime = guessTime;
        return response;
    }
}

