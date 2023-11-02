package ee.vr.cardgame.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int correctAnswers;
    private Date creationDate;
    private long duration;

    @ManyToOne
    private Player player;

    // List<Game> findAllByPlayer(Player player);
}
