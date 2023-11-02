package ee.vr.cardgame.repository;

import ee.vr.cardgame.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findAllByOrderByHighScoreDesc();

    Player findFirstByOrderByHighScoreDesc();
}
