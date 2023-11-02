package ee.vr.cardgame.repository;

import ee.vr.cardgame.entity.Game;
import ee.vr.cardgame.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    // List<Game>   või    Game
    List<Game> findAllByOrderByCorrectAnswersDesc();

    List<Game> findAllByPlayer(Player player);

    List<Game> findAllByPlayerOrderByCorrectAnswersDesc(Player player);

    List<Game> findAllByCorrectAnswersGreaterThan(int correctAnswers);

    Game findFirstByOrderByCorrectAnswersDesc();

//    @Query("SELECT * FROM Game e ORDER BY e.correctAnswers LIMIT 3", nativeQuery=true)
//    List<Game> findTopByOrderByCorrectAnswersDesc();
}
