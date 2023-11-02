package ee.vr.cardgame.controller;

import ee.vr.cardgame.entity.Game;
import ee.vr.cardgame.entity.Player;
import ee.vr.cardgame.repository.GameRepository;
import ee.vr.cardgame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DatabaseController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    @GetMapping("all-players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("all-games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("all-games-by-points")
    public List<Game> getAllGamesByPoints() {
        return gameRepository.findAllByOrderByCorrectAnswersDesc();
    }

    @GetMapping("all-players-by-points")
    public List<Player> getAllPlayersByPoints() {
        return playerRepository.findAllByOrderByHighScoreDesc();
    }

    // http://localhost:8080/all-games-by-player?playerName=Nimi
    @GetMapping("all-games-by-player")
    public List<Game> getAllGamesByPlayer(@RequestParam String playerName) {
        Player player = playerRepository.findById(playerName).orElseThrow();
        return gameRepository.findAllByPlayer(player);
    }

    @GetMapping("all-games-by-player-order")
    public List<Game> getAllGamesByPlayerOrder(@RequestParam String playerName) {
        Player player = playerRepository.findById(playerName).orElseThrow();
        return gameRepository.findAllByPlayerOrderByCorrectAnswersDesc(player);
    }

    @GetMapping("all-games-by-score")
    public List<Game> getAllGamesByScore() {
        return gameRepository.findAllByCorrectAnswersGreaterThan(2);
    }

    @GetMapping("all-games-by-score2")
    public List<Game> getAllGamesByScore2(@RequestParam int score) {
        return gameRepository.findAllByCorrectAnswersGreaterThan(score);
    }

    @GetMapping("top-game")
    public Game getTopGame() {
        return gameRepository.findFirstByOrderByCorrectAnswersDesc();
    }
}

// 1. Tagastatakse kõik mängijad
// 2. Tagastatakse kõik mängud
// 3. Tagatatakse kõik mängud points järjekorras
// 4. Tagatatakse kõik mängijad high-score järjekorras
// 5. Tagastataske kõik selle mängija mängud
// 6. Tagastataske kõik selle mängija mängud points järjekorras
// 7. Tagasta kõik mängud millel on vähemalt 2 õiget vastust
// 8. Kõige suurema pointsidega mäng
// 9. Kõige suurema highscore-ga mängija
// 10. Top3 points mängud
