package ee.vr.cardgame.service;

import ee.vr.cardgame.entity.Card;
import ee.vr.cardgame.entity.Game;
import ee.vr.cardgame.entity.GuessType;
import ee.vr.cardgame.entity.Player;
import ee.vr.cardgame.model.GuessResponse;
import ee.vr.cardgame.repository.GameRepository;
import ee.vr.cardgame.repository.PlayerRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    private int lives = 3;
    List<Card> deck = new ArrayList<>();
    Card baseCard;
    private long startTime;
    private int score = 0;

    private Player player;

    public GameService() {
        makeADeck();
        shuffleDeck();
        startDrawing();
    }

    public Card getBaseCard(String playerName) {
        lives = 3;
        score = 0;
        makeADeck();
        shuffleDeck();
        startDrawing();
        player = playerRepository.findById(playerName).orElseGet(() -> {
                    Player newPlayer = new Player(
                            playerName, new Date(), 0);
                    playerRepository.save(newPlayer);
                    return newPlayer;
                }
        );

//        Optional<Player> playerOptional = playerRepository.findById(playerName);
//        if (playerOptional.isPresent()) {
//            player = playerOptional.get();
//        } else {
//            Player newPlayer = new Player(
//                    playerName, new Date(), 0);
//            playerRepository.save(newPlayer);
//            player = newPlayer;
//        }

        startTime = System.currentTimeMillis();
        return baseCard;
    }
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

    private GuessResponse markAsCorrectAnswer(Card card) {
        GuessResponse response = new GuessResponse();
        GuessResponse gameOverResponse = getGuessResponse(response);
        if (gameOverResponse != null) return gameOverResponse;
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

        lives--;
        GuessResponse gameOverResponse = getGuessResponse(response);
        if (gameOverResponse != null) return gameOverResponse;
        baseCard = card;
        response.setMessage("Incorrect");
        response.setScore(score);
        response.setCard(baseCard);
        return response;
    }

    private GuessResponse getGuessResponse(GuessResponse response) {
        if (lives <= 0) {
            saveToDatabase();
            response.setMessage("GAME_OVER");
            response.setScore(score);
            response.setCard(null);
            return response;
        }
        return null;
    }

    private void saveToDatabase() {
        if (score > player.getHighScore()) {
            player.setHighScore(score);
            playerRepository.save(player);
        }
        Game game = Game.builder()
                .correctAnswers(score)
                .creationDate(new Date())
                .player(player)
                .build();
        gameRepository.save(game);
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

    private void startDrawing() {
        baseCard = drawCard();
    }

    private Card drawCard() {
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
}

