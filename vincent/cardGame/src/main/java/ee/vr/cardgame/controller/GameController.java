package ee.vr.cardgame.controller;


import ee.vr.cardgame.entity.Card;
import ee.vr.cardgame.service.GameService;
import ee.vr.cardgame.entity.GuessType;
import ee.vr.cardgame.model.GuessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    private GameService gameService;

    @GetMapping("start") // http://localhost:8080/start
    public Card startGame() {
        gameService = new GameService();
        return gameService.getBaseCard();
    }

    @GetMapping("guess") // localhost:8080/guess?guess=higher  guess=lower  guess=equal
    public GuessResponse guess(@RequestParam String guess){

        GuessResponse response = gameService.checkIfTimeout();
        if (response.getMessage().equals("TIME_OUT")) {
            return response;
        }

        GuessType guessType = GuessType.valueOf(guess.toUpperCase());
        return gameService.userGuess(guessType);
    }
}
