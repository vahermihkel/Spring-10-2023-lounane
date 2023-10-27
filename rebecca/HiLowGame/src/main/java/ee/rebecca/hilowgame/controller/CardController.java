package ee.rebecca.hilowgame.controller;
import ee.rebecca.hilowgame.entity.Card;
import ee.rebecca.hilowgame.entity.Deck;
import ee.rebecca.hilowgame.model.GuessResponse;
import ee.rebecca.hilowgame.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping("/start")
    public Card getCard(){
        return cardService.startGame();
   }

    @GetMapping("lower")
    public GuessResponse guessLower(){
        GuessResponse response = cardService.checkIfTimeout();
        if (response.getMessage().equals("TIME_OUT")) {
            return response;
        }
        return cardService.userGuess("lower");
    }

    @GetMapping("equal")
    public GuessResponse guessEqual(){
        GuessResponse response = cardService.checkIfTimeout();
        if (response.getMessage().equals("TIME_OUT")) {
            return response;
        }
        return cardService.userGuess("equal");
    }

    @GetMapping("higher")
    public GuessResponse guessHigher(){
        GuessResponse response = cardService.checkIfTimeout();
        if (response.getMessage().equals("TIME_OUT")) {
            return response;
        }
        return cardService.userGuess("higher");
    }
}

