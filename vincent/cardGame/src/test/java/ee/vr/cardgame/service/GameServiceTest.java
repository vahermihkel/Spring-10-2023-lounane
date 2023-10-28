package ee.vr.cardgame.service;

import ee.vr.cardgame.entity.Card;
import ee.vr.cardgame.entity.GuessType;
import ee.vr.cardgame.model.GuessResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameServiceTest {

    @Autowired
    GameService gameService;

    String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

    @Test
    void cardSuitIsCorrectWhenGameStarts() {
        Card card = gameService.getBaseCard();
        boolean isCorrectSuit = Arrays.stream(suits).anyMatch(e -> e.equals(card.getSuit()));
        assertTrue(isCorrectSuit);
    }

    // SAMA ASI RANKILE

    @Test
    void checkIfTimeout() throws InterruptedException {
        gameService.getBaseCard();
        Thread.sleep(11000);
        GuessResponse response = gameService.checkIfTimeout();
        assertEquals("TIME_OUT", response.getMessage());
    }


    @Test
    void userGuess() {
        // TESTIGE INCORRECTI, EHK PANGE FUNKTSIOONI SISENDIKS MIDAGI MUUD KUI ÜKS KOLMEST
        gameService.baseCard = new Card("Ace", "Hearts",10 );
        gameService.userGuess(GuessType.HIGHER);
    }
}
