package ee.vr.cardgame.controller;


import ee.vr.cardgame.entity.Card;
import ee.vr.cardgame.service.GameService;
import ee.vr.cardgame.entity.GuessType;
import ee.vr.cardgame.model.GuessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("start") // http://localhost:8080/start?playerName=Mihkel
    public Card startGame(
            @RequestParam String playerName
    ) {
//        gameService = new GameService();
        return gameService.getBaseCard(playerName);
    }

    @GetMapping("guess") // localhost:8080/guess?guess=higher  guess=lower  guess=equal
    public GuessResponse guess(@RequestParam String guess){

        GuessResponse response = gameService.checkIfTimeout();

        // response.getMessage().equals("TIME_OUT"); <-- kontrollin, kas tagastuse sõnum on TIME_OUT
        // ma saan seda kontrolli teha kui ta on vähemasti olemas
        // ma ei saa mitte midagi teha kui tegemist on TÜHJUSEGA     .equals()    .isEmpty()
        if (response.getMessage() != null) {
            return response;
        }

        GuessType guessType = GuessType.valueOf(guess.toUpperCase());
        return gameService.userGuess(guessType);
    }
}
