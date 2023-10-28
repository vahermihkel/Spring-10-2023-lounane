package ee.rebecca.hilowgame.service;

import ee.rebecca.hilowgame.entity.Card;
import ee.rebecca.hilowgame.entity.Suits;
import ee.rebecca.hilowgame.model.GuessResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardServiceTest {

    @Autowired
    CardService cardService;

    @Test
    void testIfSuitIsCorrectWhenStartingTheGame() {
        Card card = cardService.startGame();
        Suits[] suits = Suits.values();
        boolean isCorrectSuit = Arrays.stream(suits).anyMatch(e -> e.getSuit().equals(card.getSuit()));
//        for (Suits s: suits) {
//            if (s.getSuit().equals(card.getSuit())) {
//                isCorrectSuit = true;
//                break;
//            }
//        }
        assertTrue(isCorrectSuit);
    }

    // SAMA ASI RANKILE

    @Test
    void checkIfTimeout() throws InterruptedException {
        cardService.startGame();
        Thread.sleep(11000);
        GuessResponse response = cardService.checkIfTimeout();
        assertEquals("TIME_OUT", response.getMessage());
    }

    @Test
    void userGuess() {
        // TESTIGE INCORRECTI, EHK PANGE FUNKTSIOONI SISENDIKS MIDAGI MUUD KUI ÜKS KOLMEST
        cardService.userGuess("INVALID");
    }
}
